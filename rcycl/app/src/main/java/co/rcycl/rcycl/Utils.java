package co.rcycl.rcycl;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// Created by Whit on 2/28/2016.
// A class containing static utility methods to make dev *that much* easier.
public class Utils {

    // Searches the specified view for a resource identified by fieldId.
    public static String getTextFromViewField(View view, int fieldId) {
        return ((TextView)view.findViewById(fieldId)).getText().toString();
    }

    // Raises a toast in the specified context with the message msg.
    public static void cheers(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    // Outputs a debug log message for the specified class.
    public static void log(Object obj, String msg) {
        Log.d(obj.getClass().getName(), msg);
    }

}
