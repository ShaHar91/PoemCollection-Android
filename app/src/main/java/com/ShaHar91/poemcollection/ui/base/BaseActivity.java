package com.ShaHar91.poemcollection.ui.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.ShaHar91.poemcollection.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * The most basic use of the Activity which will extend from.
 */
@SuppressWarnings("unused")
public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon) {
        setSupportActionBar(toolbar);
        if (showBackIcon) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_back);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon, @StringRes int toolbarTitleRes) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(toolbarTitleRes);
        if (showBackIcon) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_back);
            toolbar.setNavigationOnClickListener(v -> onToolbarNavigationIconClicked());
        }
    }

    protected void onToolbarNavigationIconClicked() {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected void replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        replaceFragment(containerViewId, fragment, null);
    }

    protected void replaceFragment(@IdRes int containerViewId, Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, tag)
                .commit();
    }
}
