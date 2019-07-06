package com.shahar91.poems.ui.home.poemsPerCategoryList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.shahar91.poems.MyApp;
import com.shahar91.poems.R;
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoemsPerCategoryListFragment extends BaseGoogleFragment<PoemsPerCategoryListViewModel, PoemsPerCategoryListComponent> {
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

    //TODO: try to set the category as an object (Serializable)
    public static PoemsPerCategoryListFragment newInstance(boolean showBackIcon, int categoryId) {
        PoemsPerCategoryListFragment fragment = new PoemsPerCategoryListFragment();
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

//        addDisposable(viewModel.get);
    }

    private void initViews() {
        //toolbar
        toolbar.setTitle("Beauty");
        configureToolbar(toolbar, null);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.registerPoemsPerCategoryQuery(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.stopListeningForChangesInBackend();
    }
}
