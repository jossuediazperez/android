package es.inforce.revisiones.presentation.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import es.inforce.revisiones.R;
import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.presentation.ui.customviews.CustomDialog;
import es.inforce.revisiones.presentation.ui.customviews.listeners.SelectLanguage;
import es.inforce.revisiones.presentation.ui.util.LoadLanguage;

public class ConfigurationActivity extends AppCompatActivity implements SelectLanguage {

    private LinearLayout cambiarLenguage;
    private boolean isChangeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        isChangeLanguage = false;
        loadViews();
        init();
        setLanguages();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (isChangeLanguage) {
            setResult(Activity.RESULT_OK);
        }
        super.onBackPressed();
    }

    private void loadViews() {
        cambiarLenguage = (LinearLayout) findViewById(R.id.cambiarLenguage);
    }

    private void setLanguages() {
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        LoadLanguage.load(view, RevisionsAplication.getInstance().getCurrentLanguage().getTexts());
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cambiarLenguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.showDialog(ConfigurationActivity.this, RevisionsAplication.getInstance().getLanguageList(), RevisionsAplication.getInstance().getLanguageSelected(), ConfigurationActivity.this);
            }
        });
    }

    @Override
    public void onSelectedLanguage(int position) {
        RevisionsAplication.getInstance().setLanguageSelected(position);
        setLanguages();
        isChangeLanguage = true;
    }
}
