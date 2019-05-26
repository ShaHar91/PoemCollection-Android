package com.ShaHar91.poemcollection.ui.error;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ShaHar91.poemcollection.R;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

        if (config ==  null){
            //This should never happen - Just finish the activity to avoid a recursive crash.
            finish();
            return;
        }

        findViewById(R.id.error_activity_restart_button).setOnClickListener(v -> CustomActivityOnCrash.restartApplication(this, config));

        Button moreInfoButton = findViewById(R.id.error_activity_more_info_button);
        if (config.isShowErrorDetails()){
            moreInfoButton.setOnClickListener(v -> showErrorDetails());
        }else{
            moreInfoButton.setVisibility(View.GONE);
        }
    }

    private void showErrorDetails() {
        AlertDialog dialog = new AlertDialog.Builder(ErrorActivity.this)
                .setTitle(R.string.customactivityoncrash_error_activity_error_details_title)
                .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(ErrorActivity.this, getIntent()))
                .setPositiveButton(R.string.customactivityoncrash_error_activity_error_details_close, null)
                .setNeutralButton(R.string.customactivityoncrash_error_activity_error_details_copy, (dialog1, which) -> copyErrorToClipboard())
                .show();
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.customactivityoncrash_error_activity_error_details_text_size));
        }
    }

    /**
     * Copy error details to clipboard
     */
    private void copyErrorToClipboard() {
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(ErrorActivity.this, getIntent());

        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Are there any devices without clipboard...?
        if (clipboard != null) {
            ClipData clip = ClipData.newPlainText(getString(R.string.customactivityoncrash_error_activity_error_details_clipboard_label), errorInformation);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(ErrorActivity.this, R.string.customactivityoncrash_error_activity_error_details_copied, Toast.LENGTH_SHORT).show();
        }
    }
}
