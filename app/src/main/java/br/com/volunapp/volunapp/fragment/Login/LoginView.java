package br.com.volunapp.volunapp.fragment.Login;

public interface LoginView {
    void showProgress();

    void hideProgress();

    void setLoginError(String msg);

    void navigateToHome();
}
