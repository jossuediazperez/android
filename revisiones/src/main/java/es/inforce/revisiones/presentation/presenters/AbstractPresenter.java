package es.inforce.revisiones.presentation.presenters;


import es.inforce.revisiones.domain.executor.Executor;
import es.inforce.revisiones.domain.executor.MainThread;

public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}