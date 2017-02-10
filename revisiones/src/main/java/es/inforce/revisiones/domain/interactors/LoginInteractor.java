package es.inforce.revisiones.domain.interactors;

import es.inforce.revisiones.domain.interactors.base.Interactor;

/**
 * Created by jossue on 04/02/2017.
 */
public interface LoginInteractor extends Interactor {
    interface Callback {
        void onExecutingLogin();
        void onLoginSucces();
        void onLoginFail();
        void onLoginError(Exception e);
    }
}
