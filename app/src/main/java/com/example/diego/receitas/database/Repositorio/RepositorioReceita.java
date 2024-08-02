package com.example.diego.receitas.database.Repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.diego.receitas.Entidades.Receita;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 21/11/2017.
 */

public class RepositorioReceita {

    private SQLiteDatabase conexao;



    public RepositorioReceita(SQLiteDatabase conexao){

        this.conexao = conexao;
    }


    public void inserir(Receita receita){
        ContentValues  contentValues = new ContentValues();
        contentValues.put("NOMERECEITA", receita.nomereceita);
        contentValues.put("SERVEQUANTAS", receita.servequantas);
        contentValues.put("TEMPOPREPARO", receita.tempopreparo);
        contentValues.put("INGREDIENTES", receita.ingredientes);
        contentValues.put("MODOPREPARO", receita.modopreparo);

        conexao.insertOrThrow("RECEITA", null, contentValues);
    }

    public void excluir(Receita codigo){

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        conexao.delete("RECEITA","CODIGO = ?", paramentros);

    }

    public void alterar(Receita receita){

        ContentValues  contentValues = new ContentValues();
        contentValues.put("NOMERECEITA", receita.nomereceita);
        contentValues.put("SERVEQUANTAS", receita.servequantas);
        contentValues.put("TEMPOPREPARO", receita.tempopreparo);
        contentValues.put("INGREDIENTES", receita.ingredientes);
        contentValues.put("MODOPREPARO", receita.modopreparo);

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(receita.codigo);

        conexao.update("RECEITA", contentValues, "CODIGO = ?", paramentros);

    }

    public List<Receita> buscartodos(){

        List<Receita> receita = new ArrayList<Receita>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, NOMERECEITA, SERVEQUANTAS, TEMPOPREPARO, INGREDIENTES, MODOPREPARO ");
        sql.append("  FROM RECEITA ");

        Cursor resultado = conexao.rawQuery(sql.toString(),null);

        if(resultado.getCount() > 0) {
            resultado.moveToFirst();

            do{

               Receita rec  = new Receita();

               rec.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
               rec.nomereceita = resultado.getString(resultado.getColumnIndexOrThrow("NOMERECEITA"));
               rec.servequantas = resultado.getString(resultado.getColumnIndexOrThrow("SERVEQUANTAS"));
               rec.tempopreparo = resultado.getString(resultado.getColumnIndexOrThrow("TEMPOPREPARO"));
               rec.ingredientes = resultado.getString(resultado.getColumnIndexOrThrow("INGREDIENTES"));
               rec.modopreparo = resultado.getString(resultado.getColumnIndexOrThrow("MODOPREPARO"));

               receita.add(rec);

            }while(resultado.moveToNext());
        }




        return receita;
    }

    public Receita buscarReceita(int codigo){

        Receita receita = new Receita();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT CODIGO, NOMERECEITA, SERVEQUANTAS, TEMPOPREPARO, INGREDIENTES, MODOPREPARO");
        sql.append("FROM RECEITA)");
        sql.append("WHERE CODIGO = ?");

        String[] paramentros = new String[1];
        paramentros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(),paramentros);

        if(resultado.getCount() > 0) {
            resultado.moveToFirst();



                receita.codigo = resultado.getInt(resultado.getColumnIndexOrThrow("CODIGO"));
                receita.nomereceita = resultado.getString(resultado.getColumnIndexOrThrow("NOMERRECEITA"));
                receita.servequantas = resultado.getString(resultado.getColumnIndexOrThrow("SERVEQUANTAS"));
                receita.tempopreparo = resultado.getString(resultado.getColumnIndexOrThrow("TEMPOPREPARO"));
                receita.ingredientes = resultado.getString(resultado.getColumnIndexOrThrow("INGREDIENTES"));
                receita.modopreparo = resultado.getString(resultado.getColumnIndexOrThrow("MODOPREPARO"));

                return receita;

        }



        return null;
    }


}
