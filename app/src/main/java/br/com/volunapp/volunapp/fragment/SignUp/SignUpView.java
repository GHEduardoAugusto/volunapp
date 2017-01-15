package br.com.volunapp.volunapp.fragment.SignUp;

public interface SignUpView {
    void showProgress();

    void hideProgress();

    void setSignUpError(String msg);

    void navigateToHome();
}
