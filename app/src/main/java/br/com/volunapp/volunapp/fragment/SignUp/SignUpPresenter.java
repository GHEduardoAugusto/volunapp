package br.com.volunapp.volunapp.fragment.SignUp;

public interface SignUpPresenter {
    void SignUpCredentials(String username, String password);

    void onDestroy();
}
