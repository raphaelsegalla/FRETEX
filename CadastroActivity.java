package com.example.dev.fretex;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CadastroActivity extends BaseActivity implements View.OnClickListener {

    private EditText caixaNome;
    private Spinner spinnerEstado;
    private EditText caixaCpf;
    private EditText caixaCep;
    private EditText caixaCidade;
    private EditText caixaBairro;
    private EditText caixaLogradouro;
    private EditText caixa_numero;
    private EditText caixa_email;
    private EditText caixa_senha;
    private EditText caixaTelefone;
    private List CEPs;
    private  Util util;
    private  Button botao_enviar;
    private String valor_spinner;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        caixaNome = findViewById(R.id.caixa_nome_id);
        caixa_email = findViewById(R.id.caixa_email_id);
        caixaBairro = findViewById(R.id.caixa_bairro_id);
        caixa_senha = findViewById(R.id.caixa_senha_id);
        caixa_numero = findViewById(R.id.caixa_numero_id);
        spinnerEstado =  findViewById(R.id.spinner_estado_id);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.listaEstado,android.R.layout.simple_spinner_item);
        spinnerEstado.setAdapter(arrayAdapter);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        caixaCpf =  findViewById(R.id.caixa_cpf_id);
        caixaCep =  findViewById(R.id.caixa_cep_id);
        caixaLogradouro =  findViewById(R.id.caixa_rua_id);
        caixaTelefone =  findViewById(R.id.caixa_telefone_id);
        String cpf = caixaCpf.toString().trim();
        //ValidaCPF validarCpf = new ValidaCPF(cpf);
        caixaCidade =  findViewById(R.id.caixa_cidade_id);
        botao_enviar = findViewById(R.id.btn_enviar_id);
        caixaCep.addTextChangedListener(new CepListener(this));
        
        eventoClicks();

        caixaCpf.addTextChangedListener(MaskEditUtil.mask(caixaCpf,MaskEditUtil.FORMAT_CPF));
        //caixaCep.addTextChangedListener(MaskEditUtil.mask(caixaCep,MaskEditUtil.FORMAT_CEP));
        caixaTelefone.addTextChangedListener(MaskEditUtil.mask(caixaTelefone,MaskEditUtil.FORMAT_FONE));

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                valor_spinner = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),valor_spinner,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Toast.makeText(getApplicationContext(),valor_spinner,Toast.LENGTH_SHORT).show();

        botao_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),caixaCpf.getText().toString(),Toast.LENGTH_SHORT).show();
                Usuario novo_usuario = new Usuario(caixaNome.getText().toString(),caixaCpf.getText().toString(),caixa_senha.getText().toString(),new Cadastro(valor_spinner,caixaCidade.getText().toString(),caixaBairro.getText().toString(),
                        caixaLogradouro.getText().toString(),caixa_numero.getText().toString(),caixaCep.getText().toString(),caixa_email.getText().toString(),caixaTelefone.getText().toString()));
                mDatabase.child("usuario").child(caixaNome.getText().toString()).setValue(novo_usuario);

            }
        });

        util = new Util(this,
                R.id.caixa_cep_id,
                R.id.caixa_cidade_id,
                R.id.caixa_bairro_id,
                R.id.caixa_rua_id,
                R.id.spinner_estado_id);




    }

    public  void CriarUsuario(){
        showProgressive();
        caixa_email.getText().toString();
        caixa_numero.getText().toString();
        caixa_senha.getText().toString();
        caixaBairro.getText().toString();
        caixaNome.getText().toString();
        caixaTelefone.getText().toString();
        caixaLogradouro.getText().toString();
        caixaCep.getText().toString();
        caixaCidade.getText().toString();
        caixaCpf.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(caixa_email,caixa_senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        task.isSuccessful();
                        hideProgressive();
                        if(task.isSuccessful()){
                            authOk(task.getResult().getUser());
                        }else{
                            //Toast para erro
                        }
                    }
                });

    }

    private void authOk(FirebaseUser user) {

        escreverNovoUsuario(user.getUid(),caixaCpf.getText().toString(),caixa_senha.getText().toString()
                ,"*",caixaLogradouro.getText().toString(),caixa_numero.getText().toString(),caixaCep.getText().toString(),user.getEmail(),caixaTelefone.getText().toString());

        startActivity(new Intent(getApplicationContext(),PrincipalActivity.class));
        finish();
    }

    private void escreverNovoUsuario(String uid, String s, String s1, String s2, String s3, String s4, String s5, String email, String s6) {
        Usuario usuario = new Usuario(uid,s,s1,s2,s3,s4,s5,email,s6);

        mDatabase.child("usuario").child(userId).setValue(usuario);
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = Conexao.getFirebaseAuth();
    }

    private void eventoClicks() {
        botao_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = caixa_email.getText().toString().trim();
                String senha = caixa_senha.getText().toString().trim();
                createUser(email,senha);
            }
        });
    }

    private void createUser(String email, String senha) {
        firebaseAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso!");
                            Intent i = new Intent(CadastroActivity.this,PrincipalActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            alert("Erro ao cadastrar usuário");
                        }
                    }
                });
    }

    private void  alert(String msg){
        Toast.makeText(CadastroActivity.this,msg,Toast.LENGTH_SHORT).show();
    }


    public  String getUriCep(){
        return "https://viacep.com.br/ws/"+caixaCep.getText()+"/json/";
    }

    public void lockFields(boolean isToLock){
        util.lockFields(isToLock);
    }

    public void setDataView(Cadastro cadastro){
        setFields(R.id.caixa_cidade_id,cadastro.getLocalidade());
        setFields(R.id.caixa_bairro_id,cadastro.getBairro());
        setFields(R.id.caixa_rua_id,cadastro.getLogradouro());
        setSpinner(R.id.spinner_estado_id,R.array.listaEstado,cadastro.getUf());
    }

    public  void setFields(int id,String data){
        EditText caixa = ((EditText) findViewById(id));
        caixa.setText(data);
    }

    public  void setSpinner(int id,int arrayId,String data){
        String[] itens = getResources().getStringArray(R.array.listaEstado);
        Spinner state = ((Spinner) findViewById(id));

        for(int i = 0; i < itens.length; i++){

            if(itens[i].endsWith("("+data+")")){
                state.setSelection(i);
                return;
            }
            state.setSelection(0);

        }
    }


    @Override
    public void onClick(View view) {

    }
}




