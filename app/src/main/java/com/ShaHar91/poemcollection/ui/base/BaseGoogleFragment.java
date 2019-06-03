package com.ShaHar91.poemcollection.ui.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import com.ShaHar91.poemcollection.MyApp;
import com.ShaHar91.poemcollection.R;
import com.ShaHar91.poemcollection.injection.ApplicationComponent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseGoogleFragment<VM extends BaseGoogleViewModel, C extends BaseGoogleComponent<VM>> extends BaseFragment {
    protected VM viewModel;
    protected C component;

    public static final String SHOW_BACK_ICON = "SHOW_BACK_ICON";
    private CompositeDisposable compositeDisposable;

    protected abstract C createComponent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        component = createComponent();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }


    protected void configureToolbar(Toolbar toolbar, View toolbarIcon) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        boolean showBackIcon = getArguments() != null && getArguments().getBoolean(SHOW_BACK_ICON);
        if (showBackIcon) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_back);
            toolbar.setNavigationOnClickListener(v -> getActivity().finish());
        }

        if (toolbarIcon != null) {
            toolbarIcon.setVisibility(showBackIcon ? View.GONE : View.VISIBLE);
        }
    }

    public void tintMenuIcons(Menu menu, int color) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);

            Drawable normalDrawable = item.getIcon();

            if (normalDrawable != null) {
                Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
                DrawableCompat.setTint(wrapDrawable, color);
                item.setIcon(wrapDrawable);
//            } else if (item.getActionView() != null && item.getActionView() instanceof MediaRouteButton) {
//                // Google Cast Button
//                CastUtils.setCastButtonColor(getContext(), (MediaRouteButton) item.getActionView(), color);
            }
        }
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
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

    protected ApplicationComponent appComponent() {
        return ((MyApp) getActivity().getApplication()).getAppComponent();
    }

}
