package com.shahar91.poemcollection.ui.base.customView;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.shahar91.poemcollection.ui.base.BaseActivity;
//TODO: check if there is a 'javax' variant

public abstract class BaseMobileActivity<VM extends BaseViewModel, C extends BaseComponent<VM>>
        extends BaseActivity {

    protected VM viewModel;
    protected C component;

    protected abstract C createComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        component = createComponent();
        viewModel = component.viewModel();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        viewModel.viewCreated();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            // closing Activity
            viewModel.resetState();
        } else {
            // It's an orientation change.
        }
        viewModel.viewDestroyed();
        super.onDestroy();
    }
}