package com.ShaHar91.poemcollection.ui.base;

import android.os.Bundle;

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