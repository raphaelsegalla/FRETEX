package com.example.dev.fretex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText email;
    private EditText senha;
    private TextView botaoCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botaoCadastrar = findViewById(R.id.texto_cadastrar_id);

        eventoClicks();
    }

    private void eventoClicks() {
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),CadastroActivity.class));

            }
        });
    }


}
