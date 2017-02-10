package es.inforce.revisiones.domain.interactors.impl;

import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.LoginInteractor;
import es.inforce.revisiones.domain.interactors.base.AbstractInteractor;
import es.inforce.revisiones.domain.repository.FtpRepository;
import es.inforce.revisiones.network.ftp.model.Server;

/**
 * Created by jossue on 04/02/2017.
 */
public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private Callback callback;
    private FtpRepository ftpRepository;
    private Server server;

    public LoginInteractorImpl(Executor threadExecutor, MainThread mainThread, FtpRepository ftpRepository, Callback callback, Server server) {
        super(threadExecutor, mainThread);
        this.ftpRepository = ftpRepository;
        this.callback = callback;
        this.server = server;
    }


    @Override
    public void run() {
        try {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onExecutingLogin();
                }
            });
            if (ftpRepository.login(server.getIp(), server.getUser(), server.getPass())) {
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLoginSucces();
                    }
                });
            } else {
                mMainThread.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onLoginFail();
                    }
                });
            }

        } catch (final Exception e) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onLoginError(e);
                }
            });
        }

    }
}
