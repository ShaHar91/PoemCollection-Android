package com.shahar91.poems.ui.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.shahar91.poems.R;
import com.shahar91.poems.utils.ViewUtils;

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

        if (!isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (!isTablet()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    protected boolean isTablet() {
        return ViewUtils.isTablet(this);
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon, @StringRes int toolbarTitleRes) {
        configureToolbar(toolbar, showBackIcon, toolbarTitleRes, R.drawable.ic_navigation_back);
    }

    protected void configureToolbar(Toolbar toolbar, boolean showBackIcon, @StringRes int toolbarTitleRes, @DrawableRes int drawableRes) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(toolbarTitleRes);
            if (showBackIcon) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeAsUpIndicator(drawableRes);
                toolbar.setNavigationOnClickListener(v -> onToolbarNavigationIconClicked());
            }
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

    protected void finishThisActivity(int resultOk) {
        setResult(resultOk);
        finish();
    }
}
