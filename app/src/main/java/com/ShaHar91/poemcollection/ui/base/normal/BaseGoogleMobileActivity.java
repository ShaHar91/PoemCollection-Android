package com.shahar91.poemcollection.ui.base.normal;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poemcollection.MyApp;
import com.shahar91.poemcollection.injection.ApplicationComponent;
import com.shahar91.poemcollection.ui.base.BaseActivity;

import javax.inject.Inject;

/**
 * Default BaseActivity that will be used for most of the Activities.
 *
 * @param <VM> The ViewModel working with the Activity
 * @param <C> The Component working and linked with the Activity
 */
public abstract class BaseGoogleMobileActivity<VM extends BaseGoogleViewModel, C extends BaseGoogleComponent<VM>>
        extends BaseActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    protected VM viewModel;
    protected C component;

    protected abstract C createComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = createComponent();
    }

    public ApplicationComponent getAppComponent(){
        return ((MyApp) getApplication()).getAppComponent();
    }
}