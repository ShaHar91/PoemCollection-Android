package com.shahar91.poems.ui.home.poem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shahar91.poems.R;
import com.shahar91.poems.data.models.Poem;
import com.shahar91.poems.data.models.Review;
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment;
import com.shahar91.poems.ui.home.poem.adapter.PoemDetailAdapterController;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PoemFragment extends BaseGoogleFragment<PoemViewModel, PoemComponent> {
    private static final String POEM_ID = "POEM_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvPoemDetails)
    RecyclerView rvPoemDetails;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private PoemDetailAdapterController controller;
    private final PoemDetailAdapterController.Listener poemDetailAdapterControllerListener = new PoemDetailAdapterController.Listener() {
        @Override
        public void onRatingBarTouched(float rating) {
            //TODO: show review dialog!!
            LoggingExtensionsKt.logd(null, "onRatingBarTouched %f, %s", rating, controller.getCurrentData());
        }

        @Override
        public void onOwnReviewClicked(Review review) {
            //TODO: show review dialog to edit!!
            LoggingExtensionsKt.logd(null, "onOwnReviewClicked %s", review);
        }
    };

    public static PoemFragment newInstance(boolean showBackIcon, int poemId) {
        PoemFragment fragment = new PoemFragment();
        Bundle args = new Bundle();
        args.putBoolean(SHOW_BACK_ICON, showBackIcon);
        args.putInt(POEM_ID, poemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected PoemComponent createComponent() {
        return DaggerPoemComponent.builder()
                .applicationComponent(appComponent())
                .build();
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
        viewModel = new ViewModelProvider(this, viewModelFactory).get(PoemViewModel.class);

        initViews();

        addDisposable(viewModel.getPoem().subscribe(this::showPoem, throwable -> LoggingExtensionsKt.loge(null, throwable, "")));
    }

    private void initViews() {
        //toolbar
        configureToolbar(toolbar, ContextCompat.getColor(requireContext(), R.color.colorWhite));

        controller = new PoemDetailAdapterController(requireContext(), poemDetailAdapterControllerListener);
        rvPoemDetails.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvPoemDetails.setAdapter(controller.getAdapter());
    }

    private void showPoem(Poem poem) {
        controller.setData(poem);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.registerPoemQuery(requireArguments().getInt(POEM_ID, -1));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        viewModel.stopListeningForChangesInBackend();
        viewModel.resetPoem();
    }
}