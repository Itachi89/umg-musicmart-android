package com.preludesys.umg.musicmart.userinterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CustomAlert extends Activity {

	public static AlertDialog alertDialog;

	@SuppressWarnings("deprecation")
	public static void alertDisplay(Activity activity, String message) {

		alertDialog = new AlertDialog.Builder(activity).create();

		alertDialog.setMessage(message);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// dialog.cancel();
			}
		});
		alertDialog.setCancelable(false);
		alertDialog.show();

	}

}
