package com.shahar91.poemcollection.ui.base.normal;

import android.os.Bundle;

import com.shahar91.poemcollection.ui.base.BaseActivity;

/**
 * Default BaseActivity that will be used for most of the Activities.
 *
 * @param <VM> The ViewModel working with the Activity
 * @param <C> The Component working and linked with the Activity
 */
public abstract class BaseGoogleMobileActivity<VM extends BaseGoogleViewModel, C extends BaseGoogleComponent<VM>>
        extends BaseActivity {

    protected VM viewModel;
    protected C component;

    protected abstract C createComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = createComponent();
    }
}