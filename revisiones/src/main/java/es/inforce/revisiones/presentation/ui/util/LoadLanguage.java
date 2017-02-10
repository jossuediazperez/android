package es.inforce.revisiones.presentation.ui.util;

import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jossue on 06/02/2017.
 */
public class LoadLanguage {

    private static ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup) || (v instanceof ListView) || (v instanceof Toolbar)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }
        ArrayList<View> result = new ArrayList<View>();
        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));
            result.addAll(viewArrayList);
        }
        return result;
    }

    public static void load(ViewGroup parentView, HashMap hashMap) {
        ArrayList<View> allViewsWithinMyTopView = getAllChildren(parentView);
        for (View child : allViewsWithinMyTopView) {
            if (child instanceof TextView) {
                TextView childTextView = (TextView) child;
                if (childTextView.getTag() != null && hashMap.containsKey(childTextView.getTag())) {
                    childTextView.setText((String) hashMap.get(childTextView.getTag()));
                }
            } else if (child instanceof EditText) {
                EditText childEditText = (EditText) child;
                if (childEditText.getTag() != null && hashMap.containsKey(childEditText.getTag())) {
                    childEditText.setHint((String) hashMap.get(childEditText.getTag()));
                }
            } else if (child instanceof Button) {
                Button childButton = (Button) child;
                if (childButton.getTag() != null && hashMap.containsKey(childButton.getTag())) {
                    childButton.setText((String) hashMap.get(childButton.getTag()));
                }
            } else if (child instanceof Toolbar) {
                Toolbar childToolbar = (Toolbar) child;
                if (childToolbar.getTag() != null && hashMap.containsKey(childToolbar.getTag())) {
                    childToolbar.setTitle((String) hashMap.get(childToolbar.getTag()));
                }
                for (int i = 0; i < childToolbar.getMenu().size(); i++) {
                    String titleCondensed = String.valueOf(childToolbar.getMenu().getItem(i).getTitleCondensed());
                    if (titleCondensed != null && hashMap.containsKey(titleCondensed)) {
                        childToolbar.getMenu().getItem(i).setTitle(hashMap.get(titleCondensed).toString());
                    }
                }
            }
        }
    }
}
