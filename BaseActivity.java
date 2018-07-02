package com.example.dev.fretex;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by dev on 11/06/2018.
 */

class BaseActivity extends AppCompatActivity{
    private ProgressDialog progressDialog;

    public void showProgressive(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Carregando");
        }

        progressDialog.show();
    }

    public void hideProgressive(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public  String getUid(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
