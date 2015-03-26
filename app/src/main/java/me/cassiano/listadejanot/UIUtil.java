package me.cassiano.listadejanot;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by matheus on 3/26/15.
 */
public class UIUtil {

    public static Toast makeToast(Context context, String message) {

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);

        return toast;

    }
}
