package com.example.diego.receitas.Entidades;

/**
 * Created by diego on 21/11/2017.
 */

public class Receita {


    /* teste */

    public int codigo;
    public String nomereceita;
    public String servequantas;
    public String tempopreparo;
    public String ingredientes;
    public String modopreparo;


    public String getNomereceita() {
        return nomereceita;
    }

    public void setNomereceita(String nomereceita) {
        this.nomereceita = nomereceita;
    }

    public String getServequantas() {
        return servequantas;
    }

    public void setServequantas(String servequantas) {
        this.servequantas = servequantas;
    }

    public String getTempopreparo() {
        return tempopreparo;
    }

    public void setTempopreparo(String tempopreparo) {
        this.tempopreparo = tempopreparo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModopreparo() {
        return modopreparo;
    }

    public void setModopreparo(String modopreparo) {
        this.modopreparo = modopreparo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
