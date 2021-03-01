package com.example.btvn_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.btvn_3.Dialog_Alarm.CustomDialog;

public class MainActivity extends AppCompatActivity
        implements CustomDialog.YesNoDialogFragmentListener {

    private Button buttonShow;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.buttonShow = (Button) this.findViewById(R.id.button_show);
        this.textView = (TextView) this.findViewById(R.id.textView);

        this.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonShowClicked();
            }
        });
    }

    // User click on "Show Dialog" button.
    private void buttonShowClicked()  {
        this.textView.setText("---");

        // Create YesNoDialogFragment
        DialogFragment dialogFragment = new CustomDialog();

        // Arguments:
        Bundle args = new Bundle();
        args.putString(CustomDialog.ARG_TITLE, "Confirmation");
        args.putString(CustomDialog.ARG_MESSAGE, "Do you like this example?");
        dialogFragment.setArguments(args);

        FragmentManager fragmentManager = this.getSupportFragmentManager();

        // Show:
        dialogFragment.show(fragmentManager, "Dialog");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CustomDialog) {
            CustomDialog yesNoDialogFragment = (CustomDialog) fragment;
            yesNoDialogFragment.setOnYesNoDialogFragmentListener(this);
        }
    }

    // Implement method of YesNoDialogFragment.YesNoDialogFragmentListener
    @Override
    public void onYesNoResultDialog(int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            String value1 = data.getStringExtra("key1"); // ...
            this.textView.setText("You select YES");
        } else if(resultCode == Activity.RESULT_CANCELED) {
            this.textView.setText("You select NO");
        } else {
            this.textView.setText("You don't select");
        }
    }

}