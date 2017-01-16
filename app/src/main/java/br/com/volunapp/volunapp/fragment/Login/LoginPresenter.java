package br.com.volunapp.volunapp.fragment.Login;

import android.app.Activity;

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
