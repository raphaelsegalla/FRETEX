package com.example.dev.fretex;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dev on 31/05/2018.
 */

public class Conexao {
    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    private Conexao(){

    }

    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            inicializaFirebaseAuth();
        }
        return firebaseAuth;
    }

    private static void inicializaFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }

    private static void logout(){
        firebaseAuth.signOut();
    }


}
