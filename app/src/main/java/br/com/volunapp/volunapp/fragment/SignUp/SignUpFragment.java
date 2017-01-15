package br.com.volunapp.volunapp.fragment.SignUp;


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

import br.com.volunapp.volunapp.MainActivity;
import br.com.volunapp.volunapp.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpFragment extends Fragment implements SignUpView {


    SignUpPresenter signUpPresenter;

    @BindView(R.id.fragment_login_email)
    EditText EdtEmail;

    @BindView(R.id.fragment_login_password)
    EditText EdtPassword;

    ProgressDialog progress;

    public SignUpFragment() {
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        ButterKnife.bind(this, view);

        signUpPresenter = new SignUpPresenterImpl(this,this.getActivity());

        return view;
    }

    @OnClick(R.id.btn_signup) public void signup()
    {

        signUpPresenter.SignUpCredentials(EdtEmail.getText().toString(), EdtPassword.getText().toString());


    }

    @Override
    public void showProgress() {

        progress = ProgressDialog.show(this.getContext(), getString(R.string.SignUp_title),
                getString(R.string.signUp_registering), true);
    }

    @Override
    public void hideProgress() {

        progress.dismiss();
    }

    @Override
    public void setSignUpError(String msg) {
        Toast.makeText(SignUpFragment.this.getContext(), msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
