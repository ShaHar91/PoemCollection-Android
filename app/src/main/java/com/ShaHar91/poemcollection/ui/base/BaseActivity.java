package com.ShaHar91.poemcollection.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.ShaHar91.poemcollection.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    /**
     * return true if the Search Menu Icon should be shown on this page,
     * without checking if the Search feature is enabled. This is done separately.
     */
    protected abstract boolean showSearchMenuItem();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        compositeDisposable = new CompositeDisposable();
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon) {
        setSupportActionBar(toolbar);
        if (showBackIcon && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_navigation_back);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon, @StringRes int toolbarTitleRes) {
        configureToolbar(toolbar, showBackIcon);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(toolbarTitleRes);
        }
    }

    protected void onToolbarNavigationClicked() {
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
