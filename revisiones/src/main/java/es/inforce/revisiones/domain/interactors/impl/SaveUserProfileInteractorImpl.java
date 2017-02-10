package es.inforce.revisiones.domain.interactors.impl;

import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.BootFoldersInteractor;
import es.inforce.revisiones.domain.interactors.SaveUserProfileInteractor;
import es.inforce.revisiones.domain.interactors.base.AbstractInteractor;

/**
 * Created by david on 2/9/2017.
 */

public class SaveUserProfileInteractorImpl extends AbstractInteractor implements SaveUserProfileInteractor {

    private SaveUserProfileInteractor.Callback callback;
    String parameter;
    String value;

    public SaveUserProfileInteractorImpl(Executor threadExecutor, MainThread mainThread, SaveUserProfileInteractor.Callback callback, String parameter, String value) {
        super(threadExecutor, mainThread);
        this.callback = callback;
        this.parameter = parameter;
        this.value = value;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onExecutingSaveUserProfile();
            }
        });

        if(RevisionsAplication.getInstance().setControlValue(this.parameter, this.value)){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSaveUserProfileSucces();
                }
            });
        } else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSaveUserProfileFail();
                }
            });
        }
    }
}
