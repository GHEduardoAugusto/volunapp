package br.com.volunapp.volunapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.volunapp.volunapp.CredentialsActivity;
import br.com.volunapp.volunapp.FireBaseApi.FirebaseApi;
import br.com.volunapp.volunapp.FireBaseApi.FirebaseApiListener;
import br.com.volunapp.volunapp.MainActivity;
import br.com.volunapp.volunapp.R;
import br.com.volunapp.volunapp.fragment.Login.LoginFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountFragment extends Fragment {
    View view;

    public AccountFragment() {
    }

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.link_signOut) public void signOut()
    {
        new FirebaseApi(this.getActivity(), new FirebaseApiListener() {
            @Override
            public void Success() {

                Intent intent = new Intent(getActivity(), CredentialsActivity.class);
                startActivity(intent);

            }

            @Override
            public void Error() {

            }
        }).SignOut();
    }
}
