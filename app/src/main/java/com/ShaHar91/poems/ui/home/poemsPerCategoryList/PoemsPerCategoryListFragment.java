package com.shahar91.poems.ui.home.poemsPerCategoryList;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahar91.poems.MyApp;
import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Category;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.ui.base.BaseActivity;
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment;
import com.shahar91.poems.ui.home.poem.PoemFragment;
import com.shahar91.poems.ui.home.poemsPerCategoryList.adapter.PoemsPerCategoryListAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoemsPerCategoryListFragment extends BaseGoogleFragment<PoemsPerCategoryListViewModel, PoemsPerCategoryListComponent> {
    private static final String CATEGORY = "CATEGORY";

    private Category category;
    private PoemsPerCategoryListAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvPoemsPerCategory)
    RecyclerView rvPoemsPerCategory;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected PoemsPerCategoryListComponent createComponent() {
        return DaggerPoemsPerCategoryListComponent.builder()
                .applicationComponent(((MyApp) getActivity().getApplication()).getAppComponent())
                .build();
    }

    public static PoemsPerCategoryListFragment newInstance(boolean showBackIcon, Category category) {
        PoemsPerCategoryListFragment fragment = new PoemsPerCategoryListFragment();
        Bundle args = new Bundle();
        args.putBoolean(SHOW_BACK_ICON, showBackIcon);
        args.putSerializable(CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_poems_per_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PoemsPerCategoryListViewModel.class);

        initViews();

        addDisposable(viewModel.getPoemsPerCategory().subscribe(this::showPoemsPerCategory));
    }

    private void showPoemsPerCategory(List<Poem> poemsPerCategories) {
        adapter.setItems(poemsPerCategories);
    }

    private void initViews() {
        category = (Category) getArguments().getSerializable(CATEGORY);

        //toolbar
        toolbar.setTitle(category.getName());
        configureToolbar(toolbar, null, ContextCompat.getColor(getActivity(), R.color.colorWhite));

        adapter = new PoemsPerCategoryListAdapter(getActivity(), this::handleClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvPoemsPerCategory.setLayoutManager(linearLayoutManager);
//        rvPoemsPerCategory.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        rvPoemsPerCategory.setAdapter(adapter);
    }

    private void handleClick(String poemId) {
        PoemFragment poemFragment = PoemFragment.newInstance(true, poemId);

        ((BaseActivity) getActivity()).replaceFragment(R.id.flHomeContainer, poemFragment, "someTag", true);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.registerPoemsPerCategoryQuery(category.getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.stopListeningForChangesInBackend();
        viewModel.resetPoemsPerCategoryList();
    }
}
