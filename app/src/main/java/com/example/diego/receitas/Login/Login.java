package com.example.diego.receitas.Login;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diego.receitas.Cadastros.CadastroUsuario;
import com.example.diego.receitas.Login.Autenticador.ConfiguracaoFirebase;
import com.example.diego.receitas.Main.Main;
import com.example.diego.receitas.R;
import com.example.diego.receitas.Entidades.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private Button btnEntrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    private AppCompatTextView txtcadastrausuario;

    private AppCompatButton btlogin;
    private AppCompatButton btncancelar;

    private AppCompatEditText newEditTextEmail;
    private AppCompatEditText newEditTextSenha;

    private TextInputLayout textLayoutEmail;
    private TextInputLayout textLayoutSenha;


    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar tool = (Toolbar) findViewById(R.id.tool_login);
        setSupportActionBar(tool);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        newEditTextEmail = (AppCompatEditText) findViewById(R.id.ed_email);
        textLayoutEmail = (TextInputLayout) findViewById(R.id.textLayout_email);
        newEditTextSenha = (AppCompatEditText) findViewById(R.id.ed_senha);
        textLayoutSenha = (TextInputLayout) findViewById(R.id.textLayout_senha);
        newEditTextEmail.setBackgroundColor(Color.TRANSPARENT);
        textLayoutEmail.setBackgroundColor(Color.TRANSPARENT);
        newEditTextSenha.setBackgroundColor(Color.TRANSPARENT);
        textLayoutSenha.setBackgroundColor(Color.TRANSPARENT);
        btlogin = (AppCompatButton) findViewById(R.id.btn_login);
        btncancelar = (AppCompatButton) findViewById(R.id.btn_login_cancel);




        txtcadastrausuario = (AppCompatTextView) findViewById(R.id.txtcadastrarusuariosmaterial);

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                Intent intentmenu = new Intent(Login.this, Main.class);
            }
        });

        btlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if (!newEditTextEmail.getText().toString().equals("") && !newEditTextSenha.getText().toString().equals("")){

                    textLayoutEmail.setErrorEnabled(false);
                    textLayoutSenha.setErrorEnabled(false);

                    usuario = new Usuario();

                    usuario.setEmail(newEditTextEmail.getText().toString());
                    usuario.setSenha(newEditTextSenha.getText().toString());

                    validarLoginFirebase();


                }else{
                    textLayoutEmail.setErrorEnabled(true);
                    textLayoutEmail.setError("campo em branco!");

                    textLayoutSenha.setErrorEnabled(true);
                    textLayoutSenha.setError("Campo em branco!");


                    //Toast.makeText(Login.this, "Um ou mais campos estão vazios.", Toast.LENGTH_SHORT).show();
                }

            }



        }


        );


        txtcadastrausuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreCadastroUsuario();
            }
        });

        buttoneffect();
    }



    private void buttoneffect(){

        btlogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        v.getBackground().setColorFilter(0xe0fbc02d, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        btncancelar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        v.getBackground().setColorFilter(0xe0CC0909, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

    }


    private void validarLoginFirebase() {

        autenticacao = ConfiguracaoFirebase.getFireBaseAuttenticaca();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),
                usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Menu();
                    Toast.makeText(Login.this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(Login.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


        public void Menu(){
            Intent intentMenu = new Intent(Login.this, Main.class);
            startActivity(intentMenu);
        }


        public  void  abreCadastroUsuario(){
            Intent intentCad = new Intent(Login.this, CadastroUsuario.class);
            startActivity(intentCad);

        }


}

