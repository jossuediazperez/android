package es.inforce.revisiones.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.inforce.revisiones.R;
import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.domain.executor.impl.ThreadExecutor;
import es.inforce.revisiones.presentation.presenters.ActivityUserProfilePresenter;
import es.inforce.revisiones.presentation.presenters.impl.ActivityUserProfilePresenterImpl;
import es.inforce.revisiones.presentation.ui.customviews.TextDialog;
import es.inforce.revisiones.presentation.ui.customviews.listeners.TextChange;
import es.inforce.revisiones.presentation.ui.util.LoadLanguage;
import es.inforce.revisiones.threading.MainThreadImpl;
import es.inforce.revisiones.util.Constant;

public class UserProfileActivity extends AppCompatActivity implements ActivityUserProfilePresenter.View, TextChange {

    private TextView txtUserName;
    private TextView txtUserEmail;
    private TextView changeUserName;
    private TextView changeUserEmail;
    private ActivityUserProfilePresenter activityUserProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        loadViews();

        setLanguages();
        init();
    }

    private void loadViews() {
        txtUserName = (TextView) findViewById(R.id.tv_user_name);
        txtUserEmail = (TextView) findViewById(R.id.tv_user_email);
        changeUserName = (TextView) findViewById(R.id.tv_user_name_change);
        changeUserEmail = (TextView) findViewById(R.id.tv_user_email_change);
    }

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarUser);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activityUserProfilePresenter = new ActivityUserProfilePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);

        setUserProfileValues();

        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("tituloDialogoEditarNombreUsuario");
                String positiveButtonText = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("txtPositiveButtonEditarDatosUsuario");
                String negativeButtonText = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("txtNegativeButtonEditarDatosUsuario");
                TextDialog.showDialog(UserProfileActivity.this, title, positiveButtonText,
                        negativeButtonText,txtUserName.getText().toString(), InputType.TYPE_TEXT_VARIATION_PERSON_NAME,
                        Constant.CONTROL.NOMBRE, UserProfileActivity.this);
            }
        });

        changeUserEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("tituloDialogoEditarCorreoUsuario");
                String positiveButtonText = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("txtPositiveButtonEditarDatosUsuario");
                String negativeButtonText = RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get("txtNegativeButtonEditarDatosUsuario");
                TextDialog.showDialog(UserProfileActivity.this, title, positiveButtonText,
                        negativeButtonText, txtUserEmail.getText().toString(), InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                        Constant.CONTROL.CORREO, UserProfileActivity.this);
            }
        });
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
    public void onExecutingSaveUserProfile() {

    }

    @Override
    public void onSaveUserProfileSuccess() {
        setUserProfileValues();
    }

    @Override
    public void onSaveUserProfileFail() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Exception e) {

    }

    @Override
    public void onTextChange(String key, String value) {
        activityUserProfilePresenter.saveUserProfile(key, value);
    }

    private void setLanguages() {
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        LoadLanguage.load(view, RevisionsAplication.getInstance().getCurrentLanguage().getTexts());
    }

    private void setUserProfileValues(){
        txtUserName.setText(RevisionsAplication.getInstance().getControlValue(Constant.CONTROL.NOMBRE));
        txtUserEmail.setText(RevisionsAplication.getInstance().getControlValue(Constant.CONTROL.CORREO));
    }
}
