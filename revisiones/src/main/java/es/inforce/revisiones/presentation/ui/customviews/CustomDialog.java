package es.inforce.revisiones.presentation.ui.customviews;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ContextThemeWrapper;

import java.util.List;

import es.inforce.revisiones.R;
import es.inforce.revisiones.domain.model.Language;
import es.inforce.revisiones.presentation.ui.customviews.listeners.SelectLanguage;

public class CustomDialog {
    public static void showDialog(Context context, final List<Language> languages, final int positionSelect, final SelectLanguage selectLanguage) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AppTheme));
        String[] list = new String[languages.size()];
        for (int i = 0; i < languages.size(); i++) {
            list[i] = languages.get(i).getCaption();
        }
        mBuilder.setSingleChoiceItems(list, positionSelect, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (positionSelect != which) {
                    selectLanguage.onSelectedLanguage(which);
                }
                dialog.dismiss();
            }
        });
        mBuilder.show();
    }
}
