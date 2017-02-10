package es.inforce.revisiones.presentation.presenters.impl;

import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.DownloadFileInteractor;
import es.inforce.revisiones.domain.interactors.LoginInteractor;
import es.inforce.revisiones.domain.interactors.SyncInteractor;

import es.inforce.revisiones.domain.interactors.impl.LoginInteractorImpl;
import es.inforce.revisiones.domain.interactors.impl.SyncInteractorImpl;
import es.inforce.revisiones.domain.model.ConfigurationSync;
import es.inforce.revisiones.domain.repository.FtpRepository;
import es.inforce.revisiones.network.ftp.model.Server;
import es.inforce.revisiones.presentation.presenters.AbstractPresenter;
import es.inforce.revisiones.presentation.presenters.ActivityMainPresenter;

/**
 * Created by jossue on 04/02/2017.
 */
public class ActivityMainPresenterImpl extends AbstractPresenter implements ActivityMainPresenter, LoginInteractor.Callback,SyncInteractor.Callback {
    private View callback;
    private FtpRepository ftpRepository;
    private Server server;
    private  ConfigurationSync configurationSync;



    public ActivityMainPresenterImpl(Executor executor, MainThread mainThread, View callback, FtpRepository ftpRepository, Server server, ConfigurationSync configurationSync) {
        super(executor, mainThread);
        this.callback = callback;
        this.ftpRepository = ftpRepository;
        this.server = server;
        this.configurationSync=configurationSync;
    }

    @Override
    public void sync() {
        LoginInteractor loginInteractor = new LoginInteractorImpl(mExecutor, mMainThread, ftpRepository, this, server);
        loginInteractor.execute();
    }


    @Override
    public void disconnect() {

    }

    @Override
    public void connect() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onExecutingLogin() {
        callback.onExecutingLogin();
    }

    @Override
    public void onLoginSucces() {
        callback.onLoginSuccess();
        SyncInteractor syncInteractor = new SyncInteractorImpl(mExecutor, mMainThread, ftpRepository, this, server,configurationSync);
        syncInteractor.execute();
    }

    @Override
    public void onLoginFail() {
        callback.onLoginFail();
    }

    @Override
    public void onLoginError(Exception e) {
        callback.onLoginError();
    }

    @Override
    public void onSynchronizing() {
        callback.onSynchronizing();
    }

    @Override
    public void onSynchronizingFail() {
        callback.onSynchronizingFail();
    }

    @Override
    public void onSynchronizingSuccess() {
        callback.onSynchronizingSuccess();
    }

    @Override
    public void onSynchronizingError(Exception e) {
        callback.onSynchronizingError();
    }
}
