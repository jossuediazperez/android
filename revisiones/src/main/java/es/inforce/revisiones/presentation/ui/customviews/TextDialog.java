package es.inforce.revisiones.presentation.ui.customviews;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;
import android.widget.EditText;

import es.inforce.revisiones.R;
import es.inforce.revisiones.presentation.ui.customviews.listeners.TextChange;

/**
 * Created by david on 2/9/2017.
 */

public class TextDialog {
    public static void showDialog(Context context, String title, String positiveButtonText, String negativeButtonText, String value, final int textType, final String key, final TextChange textChange) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme));
        builder.setTitle(title);
        final EditText input = new EditText(context);
        input.setInputType(textType);
        input.setText(value);
        builder.setView(input);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textChange.onTextChange(key, input.getText().toString());
            }
        });
        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
