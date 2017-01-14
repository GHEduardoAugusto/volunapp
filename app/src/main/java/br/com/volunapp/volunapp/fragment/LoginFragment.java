package br.com.volunapp.volunapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;

import br.com.volunapp.volunapp.R;

public class LoginFragment extends Fragment {

    CallbackManager callbackManager;
    private LoginButton loginButton;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) view.findViewById(R.id.fragment_login_btn_login);
        loginButton.setFragment(this);


        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}
