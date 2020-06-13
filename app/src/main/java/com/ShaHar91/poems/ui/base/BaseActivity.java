package com.shahar91.poems.ui.base;

import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputLayout;
import com.shahar91.poems.R;

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
        configureToolbar(toolbar, showBackIcon, toolbarTitleRes, R.drawable.ic_navigation_back);
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon, @StringRes int toolbarTitleRes, @DrawableRes int drawableRes) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(toolbarTitleRes);
        if (showBackIcon) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(drawableRes);
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

    public void replaceFragment(@IdRes int containerViewId, Fragment fragment, boolean addToBackstack) {
        replaceFragment(containerViewId, fragment, null, addToBackstack);
    }

    public void replaceFragment(@IdRes int containerViewId, Fragment fragment, String tag, boolean addToBackstack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, tag);

        if (addToBackstack) {
            transaction.addToBackStack(tag);
        }

        transaction.commit();
    }

    /**
     * Set an error on the inputLayout,
     * in case the #error is null the layout should be reset
     *
     * @param inputLayout The inputLayout on which the error will be set
     * @param error       The error message which will be set on the inputLayout
     */
    protected void setError(TextInputLayout inputLayout, @javax.annotation.Nullable String error) {
        inputLayout.setError(error);
        inputLayout.setErrorEnabled(error != null);
    }
}
