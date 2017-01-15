package br.com.volunapp.volunapp.fragment.ResetPassword;


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

import br.com.volunapp.volunapp.CredentialsActivity;
import br.com.volunapp.volunapp.MainActivity;
import br.com.volunapp.volunapp.R;
import br.com.volunapp.volunapp.fragment.Login.LoginFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordFragment extends Fragment implements ResetPasswordView {


    ResetPasswordPresenter signUpPresenter;

    @BindView(R.id.fragment_login_email)
    EditText EdtEmail;


    ProgressDialog progress;

    public ResetPasswordFragment() {
    }

    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        ButterKnife.bind(this, view);

        signUpPresenter = new ResetPasswordPresenterImpl(this,this.getActivity());

        return view;
    }

    @OnClick(R.id.btn_send) public void signup()
    {

        signUpPresenter.ResetPassword(EdtEmail.getText().toString());


    }

    @Override
    public void showProgress() {

        progress = ProgressDialog.show(this.getContext(), getString(R.string.ResetPassword_title),
                getString(R.string.ResetPassword_sending), true);
    }

    @Override
    public void hideProgress() {

        progress.dismiss();
    }

    @Override
    public void ResetPasswordError(String msg) {
        Toast.makeText(ResetPasswordFragment.this.getContext(), msg, Toast.LENGTH_LONG).show();
    }


    @Override
    public void navigateToLogin() {
        CredentialsActivity.getInstance().switchFragment(new LoginFragment());
    }
}
