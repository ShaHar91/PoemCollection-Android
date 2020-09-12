package com.shahar91.poems.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.shahar91.poems.Constants;
import com.shahar91.poems.R;
import com.shahar91.poems.ui.add.AddPoemActivity;
import com.shahar91.poems.ui.base.normal.BaseGoogleMobileActivity;
import com.shahar91.poems.ui.entry.EntryActivity;
import com.shahar91.poems.ui.home.categories.CategoryFragment;

import be.appwise.core.extensions.logging.LoggingExtensionsKt;
import be.appwise.core.networking.Networking;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseGoogleMobileActivity<HomeViewModel, HomeComponent> {
    private static final String TAG_CATEGORIES = "TagCategories";
    CategoryFragment categoryFragment = CategoryFragment.newInstance(false);

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.fabAddPoem)
    void fabAddPoemClicked() {
        if (Networking.isLoggedIn()) {
            startAddPoem();
        } else {
            // start the EntryActivity to make sure the user gets logged in
            startActivityForResult(EntryActivity.startWithIntent(this), Constants.REQUEST_CODE_NEW_USER);
        }
    }

    /**
     * This may only be called when the user successfully logged in or already was logged in
     */
    private void startAddPoem() {
        startActivityForResult(AddPoemActivity.startWithIntent(this), Constants.REQUEST_CODE_ADD_POEM);
    }

    @Override
    protected HomeComponent createComponent() {
        return DaggerHomeComponent.builder()
                .applicationComponent(getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        component.inject(this);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);

        showCategoryFragment(savedInstanceState);
    }

    private void showCategoryFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            replaceFragment(R.id.flHomeContainer, categoryFragment, TAG_CATEGORIES, false);
        } else {
            LoggingExtensionsKt.logd("FragmentByTag", "Find Category Fragment By Tag");
            categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentByTag(TAG_CATEGORIES);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case Constants.REQUEST_CODE_NEW_USER: {
                if (resultCode == RESULT_OK) {
                    // A user has been logged in successfully
                    startAddPoem();
                }
                break;
            }
            case Constants.REQUEST_CODE_ADD_POEM: {
                if (resultCode == RESULT_OK) {
                    // TODO: a new poem has been added successfully, work with a listener on the BaseActivity or BaseFragment to update the "active" pages
                }
                break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}