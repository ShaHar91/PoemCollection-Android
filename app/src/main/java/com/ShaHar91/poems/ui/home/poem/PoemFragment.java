package com.shahar91.poems.ui.home.poem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.shahar91.poems.MyApp;
import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoemFragment extends BaseGoogleFragment<PoemViewModel, PoemComponent> {
    private static final String POEM_ID = "POEM_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.poemTitleTv)
    TextView poemTitleTv;
    @BindView(R.id.poemTv)
    TextView poemTv;
    @BindView(R.id.writerTv)
    TextView writerTv;
    @BindView(R.id.categoryTv)
    TextView categoryTv;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    @Override
    protected PoemComponent createComponent() {
        return DaggerPoemComponent.builder()
                .applicationComponent(((MyApp) getActivity().getApplication()).getAppComponent())
                .build();
    }

    public static PoemFragment newInstance(boolean showBackIcon, String poemId) {
        PoemFragment fragment = new PoemFragment();
        Bundle args = new Bundle();
        args.putBoolean(SHOW_BACK_ICON, showBackIcon);
        args.putString(POEM_ID, poemId);
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
        return inflater.inflate(R.layout.fragment_poem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PoemViewModel.class);

        initViews();

        addDisposable(viewModel.getPoem().subscribe(this::showPoem));
    }

    private void initViews() {
        //toolbar
        configureToolbar(toolbar, null, ContextCompat.getColor(getActivity(), R.color.colorWhite));
    }

    private void showPoem(Poem poem) {
        toolbar.setTitle(poem.getTitle());
        poemTitleTv.setText(poem.getTitle());
        poemTv.setText(poem.getPoem());
        writerTv.setText(poem.getWriter());
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.registerPoemQuery(getArguments().getString(POEM_ID, ""));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.stopListeningForChangesInBackend();
        viewModel.resetPoem();
    }
}