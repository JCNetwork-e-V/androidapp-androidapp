package com.jcnetwork.android.jctestapp1.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.jcnetwork.android.jctestapp1.ui.MainActivity;

/**
 * A dialog to allow the user to control to login or stay to logoff in webview on Login Activity
 */

public class LoginSuccessFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Context context = getContext();
        builder.setMessage("Login Success!")
                .setPositiveButton("Go to app", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Go to main screen
                        Intent openHome = new Intent(context, MainActivity.class);
                        startActivity(openHome);
                    }
                })
                .setNegativeButton("Stay here", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do nothing
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
