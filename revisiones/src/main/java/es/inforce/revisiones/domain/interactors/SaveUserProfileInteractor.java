package es.inforce.revisiones.domain.interactors;

import es.inforce.revisiones.domain.interactors.base.Interactor;

/**
 * Created by david on 2/9/2017.
 */

public interface SaveUserProfileInteractor extends Interactor {

    interface Callback {
        void onExecutingSaveUserProfile();
        void onSaveUserProfileSucces();
        void onSaveUserProfileFail();
    }
}
