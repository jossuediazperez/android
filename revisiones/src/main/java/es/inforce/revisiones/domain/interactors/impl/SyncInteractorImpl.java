package es.inforce.revisiones.domain.interactors.impl;

import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;
import es.inforce.revisiones.domain.interactors.SyncInteractor;
import es.inforce.revisiones.domain.interactors.base.AbstractInteractor;
import es.inforce.revisiones.domain.model.ConfigurationSync;
import es.inforce.revisiones.domain.model.PendingFile;
import es.inforce.revisiones.domain.repository.FtpRepository;
import es.inforce.revisiones.network.ftp.model.Server;

/**
 * Created by jossue on 04/02/2017.
 */
public class SyncInteractorImpl extends AbstractInteractor implements SyncInteractor {

    private Callback callback;
    private FtpRepository ftpRepository;
    private Server server;
    private ConfigurationSync configurationSync;


    public SyncInteractorImpl(Executor threadExecutor, MainThread mainThread, FtpRepository ftpRepository, Callback callback, Server server, ConfigurationSync configurationSync) {
        super(threadExecutor, mainThread);
        this.ftpRepository = ftpRepository;
        this.callback = callback;
        this.server = server;
        this.configurationSync = configurationSync;
    }


    @Override
    public void run() {
        try {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSynchronizing();
                }
            });
            for (PendingFile pendingFile : configurationSync.getPendingFiles()) {
                if (!ftpRepository.downloadFile(server.getIp(), server.getUser(), server.getPass(), pendingFile.getPathRemote(), pendingFile.getPathLocal(), pendingFile.getNameFile())) {
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onSynchronizingFail();
                        }
                    });
                }
            }
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSynchronizingSuccess();
                }
            });
        } catch (final Exception e) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    callback.onSynchronizingError(e);
                }
            });
        }

    }
}
