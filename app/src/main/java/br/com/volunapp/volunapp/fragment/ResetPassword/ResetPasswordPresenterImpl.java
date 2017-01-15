package br.com.volunapp.volunapp.fragment.ResetPassword;

import android.app.Activity;

import br.com.volunapp.volunapp.FireBaseApi.FirebaseApi;
import br.com.volunapp.volunapp.FireBaseApi.FirebaseApiListener;

public class ResetPasswordPresenterImpl implements ResetPasswordPresenter,FirebaseApiListener {

    private ResetPasswordView resetPasswordView;
    private Activity activity;


    public ResetPasswordPresenterImpl(ResetPasswordView resetPasswordView, Activity activity) {
        this.resetPasswordView = resetPasswordView;
        this.activity = activity;


    }



    @Override
    public void ResetPassword(String password) {
        resetPasswordView.showProgress();

        FirebaseApi firebaseApi = new FirebaseApi(activity,this);

        firebaseApi.ResetPassword(password);
    }

    @Override public void onDestroy() {
        resetPasswordView = null;
    }


    @Override
    public void Success() {

        resetPasswordView.hideProgress();
        resetPasswordView.navigateToLogin();
    }

    @Override
    public void Error() {
        resetPasswordView.hideProgress();
        resetPasswordView.ResetPasswordError("Erro");

    }
}
