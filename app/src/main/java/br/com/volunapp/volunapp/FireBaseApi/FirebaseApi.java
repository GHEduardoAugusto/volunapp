package br.com.volunapp.volunapp.FireBaseApi;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by rafael on 15/01/17.
 */

public class FirebaseApi {

    private Activity activity;
    private FirebaseAuth auth;
    private FirebaseApiListener firebaseApiListener;

    public FirebaseApi(Activity activity,FirebaseApiListener firebaseApiListener)
    {
        this.activity = activity;
        auth = FirebaseAuth.getInstance();
        this.firebaseApiListener = firebaseApiListener;


    }


    public void Login(String username, final String password)
    {



        auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            firebaseApiListener.Error();

                        } else {

                            firebaseApiListener.Success();

                        }
                    }
                });
    }

    public void SignUp(String username, final String password)
    {
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {

                            firebaseApiListener.Error();
                        }
                        else
                        {
                            firebaseApiListener.Success();
                        }

                    }
                });
    }

    public void SignOut()
    {
        auth.signOut();
        firebaseApiListener.Success();

    }

    public void ResetPassword(String username) {

        auth.sendPasswordResetEmail(username)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {

                            firebaseApiListener.Error();
                        }
                        else
                        {
                            firebaseApiListener.Success();
                        }
                    }
                });

    }


    public void getCurrentUser()
    {
        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                } else {
                    // User is signed out

                }
                // ...
            }
        };
    }


}
