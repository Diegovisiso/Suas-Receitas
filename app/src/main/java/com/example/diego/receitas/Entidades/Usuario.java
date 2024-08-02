package com.example.diego.receitas.Entidades;

import com.example.diego.receitas.Login.Autenticador.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LordTech on 09/10/2017.
 */

public class Usuario {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public String getNome() {

        return nome;
    }
    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFireBase();
        referenciaFirebase.child("usuario").child(String.valueOf(getEmail())).setValue(this);
    }
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUsuario = new HashMap<>();

        hashMapUsuario.put("email", getEmail());
        hashMapUsuario.put("senha", getSenha());
        hashMapUsuario.put("nome", getNome());
        hashMapUsuario.put("sobrenome", getSobrenome());

        return hashMapUsuario;

    }



    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getSobrenome() {

        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {

        return senha;
    }

    public void setSenha(String senha) {

        this.senha = senha;
    }
}
