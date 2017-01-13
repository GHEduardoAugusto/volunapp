package br.com.volunapp.volunapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import br.com.volunapp.volunapp.fragment.AccountFragment;
import br.com.volunapp.volunapp.fragment.AssignmentFragment;
import br.com.volunapp.volunapp.fragment.HomeFragment;
import br.com.volunapp.volunapp.fragment.LoginFragment;
import br.com.volunapp.volunapp.fragment.SettingsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    //
    @BindView(R.id.activity_main)
    RelativeLayout rl;
    //



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //
        inicializarVariavel();
        inicializarAcao();

    }

    private void inicializarVariavel() {


    }

    private void inicializarAcao() {
        //deixando o fundo com a mesma cor devido ao padding
        rl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        //tirando o botão, mas quando sair do login ele volta
        bottomNavigation.setVisibility(View.GONE);
        //
        setNavigationBottom();

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:
                HomeFragment homeFragment = HomeFragment.newInstance();
                switchFragment(homeFragment);
                break;

            case R.id.action_assignment:
                AssignmentFragment assignmentFragment = AssignmentFragment.newInstance();
                switchFragment(assignmentFragment);
                break;

            case R.id.action_account:
                AccountFragment accountFragment = AccountFragment.newInstance();
                switchFragment(accountFragment);
                break;

            case R.id.action_settings:
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                switchFragment(settingsFragment);
                break;
        }

        return true;
    }

    //
//    private void setNavigationBottom() {
//        bottomNavigation.setOnNavigationItemSelectedListener(this);
//        HomeFragment homeFragment = HomeFragment.newInstance();
//        switchFragment(homeFragment);
//    }
//
    private void switchFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment).commit();
    }


    private void setNavigationBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        LoginFragment loginFragment = LoginFragment.newInstance();
        switchFragment(loginFragment);
    }

}