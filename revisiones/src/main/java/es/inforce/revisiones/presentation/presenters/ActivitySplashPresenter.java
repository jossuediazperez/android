package es.inforce.revisiones.presentation.presenters;

import es.inforce.revisiones.presentation.ui.BaseView;

/**
 * Created by david on 2/7/2017.
 */

public interface ActivitySplashPresenter extends BasePresenter{

    interface View extends BaseView {

        void onExecutingBootFolders();
        void onBootFoldersSuccess();
        void onBootFoldersFail();
    }

    void bootFolders();
}
