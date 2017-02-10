package es.inforce.revisiones.presentation.presenters.impl;

import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.BootFoldersInteractor;
import es.inforce.revisiones.domain.interactors.impl.BootFoldersInteractorImpl;
import es.inforce.revisiones.presentation.presenters.AbstractPresenter;
import es.inforce.revisiones.presentation.presenters.ActivitySplashPresenter;

/**
 * Created by david on 2/7/2017.
 */

public class ActivitySplashPresenterImpl extends AbstractPresenter implements ActivitySplashPresenter, BootFoldersInteractor.Callback {

    private ActivitySplashPresenter.View callback;

    public ActivitySplashPresenterImpl(Executor executor, MainThread mainThread, ActivitySplashPresenter.View callback) {
        super(executor, mainThread);
        this.callback = callback;
    }

    @Override
    public void bootFolders() {
        BootFoldersInteractor bootFoldersInteractor = new BootFoldersInteractorImpl(mExecutor, mMainThread, this);
        bootFoldersInteractor.execute();
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
    public void onExecutingBootFolders(){
        callback.onExecutingBootFolders();
    }

    @Override
    public void onBootFoldersSucces() {
        callback.onBootFoldersSuccess();
    }

    @Override
    public void onBootFoldersFail() {
        callback.onBootFoldersFail();
    }
}