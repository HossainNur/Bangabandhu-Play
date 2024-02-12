package com.durbar.bangabandhuplay.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class NetworkDialog {

    public static void showNetworkDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Network Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

