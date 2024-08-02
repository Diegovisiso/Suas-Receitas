package com.example.diego.receitas.database;

/**
 * Created by diego on 20/11/2017.
 */

public class ScriptDLL {


    public static String getCreateTableReceita(){
        StringBuilder sql = new StringBuilder();

        sql.append(" CREATE TABLE IF NOT EXISTS RECEITA ( ");
        sql.append("        CODIGO        INTEGER         PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sql.append("        NOMERECEITA   VARCHAR (250)   NOT NULL DEFAULT (''),");
        sql.append("        SERVEQUANTAS  VARCHAR (250)  NOT NULL DEFAULT (''),");
        sql.append("        TEMPOPREPARO  VARCHAR (250)    NOT NULL DEFAULT (''),");
        sql.append("        INGREDIENTES  VARCHAR (250)   NOT NULL DEFAULT (''),");
        sql.append("        MODOPREPARO   VARCHAR (250)  NOT NULL DEFAULT ('') ) " );

        return sql.toString();


    }
}
