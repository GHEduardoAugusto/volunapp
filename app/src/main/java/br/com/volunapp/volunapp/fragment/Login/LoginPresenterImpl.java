package br.com.volunapp.volunapp.fragment.Login;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.volunapp.volunapp.FireBaseApi.FirebaseApi;
import br.com.volunapp.volunapp.FireBaseApi.FirebaseApiListener;
import br.com.volunapp.volunapp.R;

public class LoginPresenterImpl implements LoginPresenter,FirebaseApiListener {

    private LoginView loginView;
    private FirebaseApi firebaseApi;
    private Activity activity;



    public LoginPresenterImpl(LoginView loginView, Activity activity) {
        this.loginView = loginView;
        this.activity = activity;

        firebaseApi = new FirebaseApi(activity,this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            loginView.navigateToHome();
        }

    }

    @Override public void validateCredentials(String username, final String password) {

        if(password.length() == 0 || username.length() == 0 )
        {

            loginView.setLoginError(activity.getString(R.string.login_empty_user_password));
            return;
        }

        if(password.length() < 6)
        {
            loginView.setLoginError(activity.getString(R.string.login_password_too_short));
            return;
        }

        if (loginView != null) {
            loginView.showProgress();
        }
        else
        return;



        firebaseApi.Login(username,password);



    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override
    public void LoginSuccess() {

        loginView.hideProgress();
        loginView.navigateToHome();


    }

    @Override
    public void LoginError() {

        loginView.hideProgress();
        loginView.setLoginError(activity.getString(R.string.login_invalid_credential));
    }
}
