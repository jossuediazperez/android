package es.inforce.revisiones.presentation.presenters;

import es.inforce.revisiones.presentation.ui.BaseView;

/**
 * Created by jossue on 04/02/2017.
 */
public interface ActivityMainPresenter extends BasePresenter {
    interface View extends BaseView {
        void onExecutingLogin();

        void onLoginSuccess();

        void onLoginFail();

        void onLoginError();

        void onSynchronizing();

        void onSynchronizingFail();

        void onSynchronizingSuccess();

        void onSynchronizingError();
    }

    void sync();

    void disconnect();

    void connect();
}
