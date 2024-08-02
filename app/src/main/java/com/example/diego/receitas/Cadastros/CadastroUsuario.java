package com.example.diego.receitas.Cadastros;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diego.receitas.Login.Autenticador.Base64Custom;
import com.example.diego.receitas.Login.Autenticador.ConfiguracaoFirebase;
import com.example.diego.receitas.Login.Login;
import com.example.diego.receitas.Login.Autenticador.Preferencias;
import com.example.diego.receitas.Entidades.Usuario;
import com.example.diego.receitas.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class CadastroUsuario extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadSenha;
    private EditText edtCadConfirmaSenha;
    private Button btnCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtCadConfirmaSenha = (EditText) findViewById(R.id.edtCadConfirmaSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CamposEmBranco();


            }
        });


    }

    protected void SenhasIguais(){
        if (edtCadSenha.getText().toString().equals(edtCadConfirmaSenha.getText().toString())) {

            usuario = new Usuario();
            usuario.setEmail(edtCadEmail.getText().toString());
            usuario.setSenha(edtCadSenha.getText().toString());

            cadastrarUsuario();
        }

        else {
            Toast.makeText(CadastroUsuario.this, "As senhas não são correspondentes", Toast.LENGTH_LONG).show();
        }
    }

    protected void CamposEmBranco(){
        if(edtCadEmail.toString().isEmpty()){
            Toast.makeText(this, "E-mail em branco", Toast.LENGTH_LONG).show();
        }

        if(edtCadSenha.toString().isEmpty()){
            Toast.makeText(this, "Senha em branco", Toast.LENGTH_LONG).show();
        }

        if(edtCadEmail.toString().isEmpty() && edtCadSenha.toString().isEmpty()){
            Toast.makeText(this, "Campos em branco", Toast.LENGTH_LONG).show();
        }

        if(edtCadEmail.toString().isEmpty() && edtCadSenha.toString().isEmpty() && edtCadConfirmaSenha.toString().isEmpty()){
            Toast.makeText(this, "Campos em branco", Toast.LENGTH_LONG).show();
        } else{
            SenhasIguais();


    }
        }


    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFireBaseAuttenticaca();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CadastroUsuario.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                    String idenficadorUsuario = Base64Custom.codificarBase64(usuario.getEmail());
                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setEmail(idenficadorUsuario);
                    usuario.salvar();

                    Preferencias preferenciasAndroid = new Preferencias(CadastroUsuario.this);
                    preferenciasAndroid.salvarusuarioPreferencias(idenficadorUsuario, usuario.getNome());

                    abrirLoginUsuario();

                } else {
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = " Digite uma senha mais forte, contendo no mínimo 8 caracteres de letras e números";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = " O e-mail digitado é inválido, digite um novo e-mail";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Esse e-mail já está cadastrado no sistema";
                    } catch (Exception e) {
                        erroExcecao = " ao efetuar o cadastro!";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuario.this, "Erro " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirLoginUsuario() {
        Intent intent = new Intent(CadastroUsuario.this, Login.class);
        startActivity(intent);
       // finish();
    }
}