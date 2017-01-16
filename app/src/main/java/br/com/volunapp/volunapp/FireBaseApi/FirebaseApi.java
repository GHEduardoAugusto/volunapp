package br.com.volunapp.volunapp.FireBaseApi;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

                            firebaseApiListener.LoginError();

                        } else {

                            firebaseApiListener.LoginSuccess();

                        }
                    }
                });
    }
}
