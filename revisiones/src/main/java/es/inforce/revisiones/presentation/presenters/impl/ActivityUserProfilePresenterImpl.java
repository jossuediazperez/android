package es.inforce.revisiones.presentation.presenters.impl;

import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.SaveUserProfileInteractor;
import es.inforce.revisiones.domain.interactors.impl.SaveUserProfileInteractorImpl;
import es.inforce.revisiones.presentation.presenters.AbstractPresenter;
import es.inforce.revisiones.presentation.presenters.ActivityUserProfilePresenter;

/**
 * Created by david on 2/9/2017.
 */

public class ActivityUserProfilePresenterImpl extends AbstractPresenter implements ActivityUserProfilePresenter, SaveUserProfileInteractor.Callback {

    private ActivityUserProfilePresenter.View callback;

    public ActivityUserProfilePresenterImpl(Executor executor, MainThread mainThread, ActivityUserProfilePresenter.View callback) {
        super(executor, mainThread);
        this.callback = callback;
    }

    @Override
    public void onExecutingSaveUserProfile() {
        callback.onExecutingSaveUserProfile();
    }

    @Override
    public void onSaveUserProfileSucces() {
        callback.onSaveUserProfileSuccess();
    }

    @Override
    public void onSaveUserProfileFail() {
        callback.onSaveUserProfileFail();
    }

    @Override
    public void saveUserProfile(String parameter, String value) {
        SaveUserProfileInteractor saveUserProfileInteractor = new SaveUserProfileInteractorImpl(mExecutor, mMainThread, this, parameter, value);
        saveUserProfileInteractor.execute();
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
}
