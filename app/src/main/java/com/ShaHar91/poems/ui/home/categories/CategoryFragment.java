package com.shahar91.poems.ui.home.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.ui.base.BaseActivity;
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment;
import com.shahar91.poems.ui.home.categories.adapter.CategoryAdapter;
import com.shahar91.poems.ui.home.poemsPerCategoryList.PoemsPerCategoryListFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends BaseGoogleFragment<CategoryViewModel, CategoryComponent> {
    private static final String TAG_POEMS_LIST = "TagPoemsList";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvCategories)
    RecyclerView rvCategories;

    private CategoryAdapter adapter;

    @Override
    protected CategoryComponent createComponent() {
        return DaggerCategoryComponent.builder()
                .applicationComponent(appComponent())
                .build();
    }

    public static CategoryFragment newInstance(boolean showBackIcon) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putBoolean(SHOW_BACK_ICON, showBackIcon);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component.inject(this);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CategoryViewModel.class);

        initViews();

        addDisposable(viewModel.getCategories().subscribe(this::showCategories, Throwable::printStackTrace));
    }

    private void showCategories(List<Category> categoryList) {
        adapter.setItems(categoryList);
    }

    private void initViews() {
        //toolbar
        toolbar.setTitle("Categories");
        configureToolbar(toolbar, null);

        adapter = new CategoryAdapter(getActivity(), this::handleClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCategories.setLayoutManager(linearLayoutManager);

        rvCategories.setAdapter(adapter);
    }

    private void handleClick(Category category) {
        PoemsPerCategoryListFragment poemsPerCategoryListFragment = PoemsPerCategoryListFragment.newInstance(true, category);

        ((BaseActivity) requireActivity()).replaceFragment(R.id.flHomeContainer, poemsPerCategoryListFragment, TAG_POEMS_LIST, true);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.registerCategoryQuery();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.stopListeningForChangesInBackend();
    }
}
