package br.com.volunapp.volunapp.fragment.SignUp;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.volunapp.volunapp.FireBaseApi.FirebaseApi;
import br.com.volunapp.volunapp.FireBaseApi.FirebaseApiListener;
import br.com.volunapp.volunapp.R;

public class SignUpPresenterImpl implements SignUpPresenter,FirebaseApiListener {

    private SignUpView signUpView;
    private Activity activity;


    public SignUpPresenterImpl(SignUpView signUpView, Activity activity) {
        this.signUpView = signUpView;
        this.activity = activity;


    }


    @Override
    public void SignUpCredentials(String username, String password) {

        signUpView.showProgress();

        FirebaseApi firebaseApi = new FirebaseApi(activity,this);

        firebaseApi.SignUp(username,password);

    }

    @Override public void onDestroy() {
        signUpView = null;
    }


    @Override
    public void Success() {

        signUpView.hideProgress();
        signUpView.navigateToHome();
    }

    @Override
    public void Error() {
        signUpView.hideProgress();
        signUpView.setSignUpError("Erro");

    }
}
