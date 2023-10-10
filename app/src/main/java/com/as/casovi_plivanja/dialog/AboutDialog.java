package com.as.casovi_plivanja.dialog;

import android.app.AlertDialog;
import android.content.Context;

public class AboutDialog extends AlertDialog.Builder {

    public AboutDialog(Context context) {
        super( context );
        setTitle( "About" );
        setMessage( "App name:\nAuthor: Adrijana Savic\nadrijana.work@gmail.com" );
        setPositiveButton( "OK", (dialog, which) -> dialog.dismiss());
    }
}
