package es.inforce.revisiones.presentation.ui;

/**
 * Created by Alberto on 23/01/2017.
 */

public interface BaseView {

    /**
     * This is a general method used for showing some kind of progress during a background task. For example, this
     * method should show a progress bar and/or disable buttons before some background work starts.
     */
    void showProgress();

    /**
     * This is a general method used for hiding progress information after a background task finishes.
     */
    void hideProgress();

    /**
     * This method is used for showing error messages on the UI.
     *
     * @param e The error message to be dislayed.
     */
    void showError(Exception e);
}