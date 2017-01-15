package br.com.volunapp.volunapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;


import br.com.volunapp.volunapp.fragment.Login.LoginFragment;
import butterknife.ButterKnife;

/**
 * Created by rafael on 14/01/17.
 */

public class CredentialsActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);

        switchFragment(LoginFragment.newInstance());

    }


    private void switchFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment).commit();
    }
}
