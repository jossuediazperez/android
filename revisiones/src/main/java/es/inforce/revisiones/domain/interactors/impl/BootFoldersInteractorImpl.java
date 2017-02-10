package es.inforce.revisiones.domain.interactors.impl;

import es.inforce.revisiones.RevisionsAplication;
import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.BootFoldersInteractor;
import es.inforce.revisiones.domain.interactors.LoginInteractor;
import es.inforce.revisiones.domain.interactors.base.AbstractInteractor;
import es.inforce.revisiones.domain.repository.FtpRepository;

/**
 * Created by david on 2/7/2017.
 */

public class BootFoldersInteractorImpl extends AbstractInteractor implements BootFoldersInteractor {

    private BootFoldersInteractor.Callback callback;

    public BootFoldersInteractorImpl(Executor threadExecutor, MainThread mainThread, BootFoldersInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        this.callback = callback;
    }

    @Override
    public void run() {
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onExecutingBootFolders();
            }
        });

        if(RevisionsAplication.getInstance().bootFolders()){
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onBootFoldersSucces();
                }
            });
        } else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onBootFoldersFail();
                }
            });
        }
    }
}
