package es.inforce.revisiones.presentation.presenters;

import es.inforce.revisiones.presentation.ui.BaseView;

/**
 * Created by david on 2/9/2017.
 */

public interface ActivityUserProfilePresenter extends BasePresenter{

    interface View extends BaseView {

        void onExecutingSaveUserProfile();
        void onSaveUserProfileSuccess();
        void onSaveUserProfileFail();
    }

    void saveUserProfile(String parameter, String value);
}
