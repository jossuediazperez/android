package es.inforce.revisiones.domain.interactors;

import es.inforce.revisiones.domain.interactors.base.Interactor;

/**
 * Created by jossue on 04/02/2017.
 */
public interface SyncInteractor extends Interactor {
    interface Callback {
        void onSynchronizing();

        void onSynchronizingFail();

        void onSynchronizingSuccess();

        void onSynchronizingError(Exception e);
    }
}
