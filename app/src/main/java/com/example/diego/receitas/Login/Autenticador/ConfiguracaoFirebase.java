package com.example.diego.receitas.Login.Autenticador;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by LordTech on 09/10/2017.
 */

public class ConfiguracaoFirebase {
    private static DatabaseReference referenceFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFireBase(){
        if (referenceFirebase == null){

        }return referenceFirebase;
    }
    public static FirebaseAuth getFireBaseAuttenticaca(){
        if (autenticacao == null){
            autenticacao = FirebaseAuth.getInstance().getInstance();
        }
        return autenticacao;
    }

}
