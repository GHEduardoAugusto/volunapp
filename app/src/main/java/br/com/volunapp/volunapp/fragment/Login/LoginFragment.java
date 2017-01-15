package br.com.volunapp.volunapp.fragment.Login;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.CallbackManager;

import br.com.volunapp.volunapp.CredentialsActivity;
import br.com.volunapp.volunapp.MainActivity;
import br.com.volunapp.volunapp.R;
import br.com.volunapp.volunapp.fragment.ResetPassword.ResetPasswordFragment;
import br.com.volunapp.volunapp.fragment.SignUp.SignUpFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment implements LoginView{

    CallbackManager callbackManager;


    LoginPresenter loginPresenter;

    @BindView(R.id.fragment_login_email)
    EditText EdtEmail;

    @BindView(R.id.fragment_login_password)
    EditText EdtPassword;

    ProgressDialog progress;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        callbackManager = CallbackManager.Factory.create();

        loginPresenter = new LoginPresenterImpl(this,this.getActivity());

        return view;
    }

    @OnClick(R.id.btn_login) public void login()
    {

        loginPresenter.validateCredentials(EdtEmail.getText().toString(), EdtPassword.getText().toString());


    }

    @OnClick(R.id.link_resetPassord) public void link_resetPassord()
    {
        CredentialsActivity.getInstance().switchFragment(new ResetPasswordFragment());
    }


    @OnClick(R.id.link_signup) public void link_signup()
    {
        CredentialsActivity.getInstance().switchFragment(new SignUpFragment());
    }



    @Override
    public void showProgress() {

        progress = ProgressDialog.show(this.getContext(), getString(R.string.login_title),
                getString(R.string.login_verifing), true);
    }

    @Override
    public void hideProgress() {

        progress.dismiss();
    }

    @Override
    public void setLoginError(String msg) {
        Toast.makeText(LoginFragment.this.getContext(), msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
