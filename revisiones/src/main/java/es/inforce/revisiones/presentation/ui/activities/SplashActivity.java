package es.inforce.revisiones.presentation.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import es.inforce.revisiones.R;
import es.inforce.revisiones.domain.executor.impl.ThreadExecutor;
import es.inforce.revisiones.presentation.presenters.ActivitySplashPresenter;
import es.inforce.revisiones.presentation.presenters.impl.ActivitySplashPresenterImpl;
import es.inforce.revisiones.threading.MainThreadImpl;

public class SplashActivity extends AppCompatActivity implements ActivitySplashPresenter.View {

    private ActivitySplashPresenter activitySplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        activitySplashPresenter = new ActivitySplashPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        new Handler().postDelayed(new Runnable(){
            public void run(){
                activitySplashPresenter.bootFolders();
            }
        }, 2000);

    }

    @Override
    public void onExecutingBootFolders() {

    }

    @Override
    public void onBootFoldersSuccess() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBootFoldersFail() {
        //TODO Definir que se va a hacer cuando no se pueden crear las carpetas del sistema
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(Exception e) {
        //TODO Definir que se va a hacer cuando no se pueden crear las carpetas del sistema

    }
}
