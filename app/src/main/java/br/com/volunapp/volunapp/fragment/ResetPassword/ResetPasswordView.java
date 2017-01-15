package br.com.volunapp.volunapp.fragment.ResetPassword;

public interface ResetPasswordView {
    void showProgress();

    void hideProgress();

    void ResetPasswordError(String msg);

    void navigateToLogin();
}
