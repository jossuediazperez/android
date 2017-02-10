package es.inforce.revisiones.presentation.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import es.inforce.revisiones.R;
import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.domain.executor.impl.ThreadExecutor;
import es.inforce.revisiones.domain.model.ConfigurationSync;
import es.inforce.revisiones.network.ftp.repository.FtpRepositoryImpl;
import es.inforce.revisiones.presentation.presenters.ActivityMainPresenter;
import es.inforce.revisiones.presentation.presenters.impl.ActivityMainPresenterImpl;
import es.inforce.revisiones.presentation.ui.customviews.MaterialRippleLayout;
import es.inforce.revisiones.presentation.ui.util.GenericPendingFiles;
import es.inforce.revisiones.presentation.ui.util.LoadLanguage;
import es.inforce.revisiones.threading.MainThreadImpl;
import es.inforce.revisiones.util.Constant;

public class MainActivity extends AppCompatActivity implements ActivityMainPresenter.View {

    private ActivityMainPresenter activityMainPresenter;
    private TextView txtNotificacion;
    private MaterialRippleLayout optionConfiguration;
    private MaterialRippleLayout optionUserProfile;
    private static int CONFIGURATION_REQUEST = 5;
    private String lastNotificicationText;
    private boolean executingSync;

    private static final String[] INITIAL_PERMS = {
            Manifest.permission.READ_PHONE_STATE
    };
    private static final int PERMS_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executingSync = false;
        setContentView(R.layout.activity_main);
        loadViews();
        if (!hasPermissionReadPhoneStateStorage()) {
            ActivityCompat.requestPermissions(this, INITIAL_PERMS, PERMS_REQUEST);
        } else {
            setImei();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        setLanguages();
        return super.onPrepareOptionsMenu(menu);
    }

    private void loadViews() {
        txtNotificacion = (TextView) findViewById(R.id.txtNotificacion);
        optionConfiguration = (MaterialRippleLayout) findViewById(R.id.optionConfiguration);
        optionUserProfile = (MaterialRippleLayout) findViewById(R.id.optionUserProfile);

    }

    private void setLanguages() {
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);
        LoadLanguage.load(view, RevisionsAplication.getInstance().getCurrentLanguage().getTexts());
    }

    public boolean hasPermissionReadPhoneStateStorage() {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE));
    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConfigurationSync configurationSync = new ConfigurationSync();
        configurationSync.setPendingFiles(GenericPendingFiles.getFiles(this));

        activityMainPresenter = new ActivityMainPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new FtpRepositoryImpl(this), RevisionsAplication.getInstance().getServer(), configurationSync);
        activityMainPresenter.sync();

        optionConfiguration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivityForResult(intent, CONFIGURATION_REQUEST);
            }
        });

        optionUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_REQUEST) {
            boolean pass = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                    pass = false;
                    break;
                }
            }
            if (pass) {
                setImei();
            } else {
                Toast.makeText(this, RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant
                        .FINALIZAR), Toast.LENGTH_LONG).show();
                MainActivity.this.finish();
            }
        }
    }

    private void setImei() {
        TelephonyManager mngr = (TelephonyManager) this.getSystemService(this.TELEPHONY_SERVICE);
        String imei = mngr.getDeviceId();
        RevisionsAplication.getInstance().setImei(imei);
        init();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_refresh) {
            if (!executingSync) {
                activityMainPresenter.sync();

            }
        }
        return true;
    }

    @Override
    public void onExecutingLogin() {
        lastNotificicationText = Constant.AUTHENTICATION.AUTH;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.AUTH));
        executingSync = true;
    }

    @Override
    public void onLoginSuccess() {
        lastNotificicationText = Constant.AUTHENTICATION.AUTH_SUCCES;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.AUTH_SUCCES));
    }

    @Override
    public void onLoginFail() {
        lastNotificicationText = Constant.AUTHENTICATION.AUTH_FAIL;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.AUTH_FAIL));
        executingSync = false;
    }

    @Override
    public void onLoginError() {
        lastNotificicationText = Constant.AUTHENTICATION.AUTH_ERROR;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.AUTH_ERROR));
        executingSync = false;
    }

    @Override
    public void onSynchronizing() {
        lastNotificicationText = Constant.AUTHENTICATION.SYNC;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.SYNC));
    }

    @Override
    public void onSynchronizingFail() {
        lastNotificicationText = Constant.AUTHENTICATION.SYNC_FAIL;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.SYNC_FAIL));
        executingSync = false;
    }

    @Override
    public void onSynchronizingSuccess() {
        lastNotificicationText = Constant.AUTHENTICATION.SYNC_SUCCES;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.SYNC_SUCCES));
    }

    @Override
    public void onSynchronizingError() {
        lastNotificicationText = Constant.AUTHENTICATION.SYNC_EROR;
        txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(Constant.AUTHENTICATION.SYNC_EROR));
        executingSync = false;
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONFIGURATION_REQUEST && resultCode == Activity.RESULT_OK) {
            setLanguages();
            txtNotificacion.setText(RevisionsAplication.getInstance().getCurrentLanguage().getTexts().get(lastNotificicationText));
        }
    }
}
