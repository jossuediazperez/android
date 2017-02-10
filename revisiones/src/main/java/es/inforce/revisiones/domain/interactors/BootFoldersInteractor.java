package es.inforce.revisiones.domain.interactors;

import es.inforce.revisiones.domain.interactors.base.Interactor;

/**
 * Created by david on 2/7/2017.
 */

public interface BootFoldersInteractor extends Interactor {

    interface Callback {
        void onExecutingBootFolders();
        void onBootFoldersSucces();
        void onBootFoldersFail();
    }
}
