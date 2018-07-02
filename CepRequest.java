package com.example.dev.fretex;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Created by dev on 15/04/2018.
 */

public class CepRequest extends AsyncTask<Void,Void,Cadastro> {
    private WeakReference<CadastroActivity> activity;

    public CepRequest(CadastroActivity activity){
        this.activity = new WeakReference<CadastroActivity>(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(activity.get() != null){
        activity.get().lockFields(true);
        }
    }

    @Override
    protected Cadastro doInBackground(Void... voids) {
        try {
            String jsonString = JsonRequest.request(activity.get().getUriCep());
            Gson gson = new Gson();
            return gson.fromJson(jsonString, Cadastro.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Cadastro cadastro) {
        super.onPostExecute(cadastro);
        if(activity.get() != null){
            activity.get().lockFields(false);
        }
        if (cadastro != null){
            activity.get().setDataView(cadastro);
        }
    }
}
