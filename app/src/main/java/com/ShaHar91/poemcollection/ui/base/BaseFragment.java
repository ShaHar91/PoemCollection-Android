package com.ShaHar91.poemcollection.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.ShaHar91.poemcollection.R;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment<VM extends BaseViewModel, C extends BaseComponent<VM>> extends Fragment {
    public static final String SHOW_BACK_ICON = "SHOW_BACK_ICON";

    protected VM viewModel;
    protected C component;

    protected abstract C createComponent();

    private CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        component = createComponent();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void configureToolbar(Toolbar toolbar, View toolbarIcon) {
        if (getActivity() != null) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            activity.setSupportActionBar(toolbar);

            boolean showBackIcon = getArguments() != null && getArguments().getBoolean(SHOW_BACK_ICON);
            if (showBackIcon) {
                if (activity.getSupportActionBar() != null) {
                    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    activity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_back);
                    toolbar.setNavigationOnClickListener(v -> getActivity().finish());
                }
            }

            if (toolbarIcon != null) {
                toolbarIcon.setVisibility(showBackIcon ? View.GONE : View.VISIBLE);
            }
        }
    }

    protected <T> T setListener() {
        T listener;

        if (getParentFragment() != null) {
            listener = (T) getParentFragment();
        } else {
            try {
                listener = (T) getActivity();
            } catch (ClassCastException e) {
                listener = null;
            }
        }

        if (listener != null) {
            return listener;
        }

        throw new IllegalStateException("parent must implement listener");
    }


//    protected ApplicationComponent appComponent() {
//        return ((Nexx4App) getActivity().getApplication()).getAppComponent();
//    }
}
