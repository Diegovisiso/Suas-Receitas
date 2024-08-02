package com.example.diego.receitas.Cadastros;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diego.receitas.Main.Main;
import com.example.diego.receitas.R;
import com.example.diego.receitas.database.DadosOpenHelper;
import com.example.diego.receitas.Entidades.Receita;
import com.example.diego.receitas.database.Repositorio.RepositorioReceita;

public class CadastroReceita extends AppCompatActivity {

    private EditText editNome;
    private EditText editTempoPreparo;
    private EditText editServeQuantas;
    private EditText editIngredientes;
    private EditText editModoPreparo;

    private ConstraintLayout ContentCad;

    private RepositorioReceita repositorioReceita;
    private Receita receita;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcadastroreceita);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        editNome = (EditText)findViewById(R.id.editNome);
        editServeQuantas = (EditText)findViewById(R.id.editServeQuantas);
        editTempoPreparo = (EditText)findViewById(R.id.editTempoPreparo);
        editIngredientes = (EditText)findViewById(R.id.editIngredientes);
        editModoPreparo = (EditText)findViewById(R.id.editModoPreparo);

        ContentCad = (ConstraintLayout)findViewById(R.id.ContentCad);

        criarConexao();

    }




    private void criarConexao(){
        try{

            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(ContentCad, "Capricha na Receita!", Snackbar.LENGTH_SHORT)
                    .setAction("Ok", null).show();

            repositorioReceita = new RepositorioReceita(conexao);

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok",null);
            dlg.show();

        }
    }


    private void confirmar(){

        receita = new Receita();

        if (validaCampos() == false){
            try {
                repositorioReceita.inserir(receita);
                finish();
                Intent it = new Intent(CadastroReceita.this, Main.class);
                startActivity(it);
            }catch (SQLException ex){

                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Erro");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("Ok",null);
                dlg.show();
            }

        }

    }


    private boolean validaCampos(){

        boolean res = false;

        String Nome = editNome.getText().toString();
        String TempoPreparo = editTempoPreparo.getText().toString();
        String ServeQuantos = editServeQuantas.getText().toString();
        String Ingredientes = editIngredientes.getText().toString();
        String ModoPreparo = editModoPreparo.getText().toString();

        receita.nomereceita = Nome;
        receita.tempopreparo = TempoPreparo;
        receita.servequantas = ServeQuantos;
        receita.ingredientes = Ingredientes;
        receita.modopreparo = ModoPreparo;

        if (res = isCampoVazio(Nome)){
            editNome.requestFocus();
        }else
            if(res = isCampoVazio(TempoPreparo)){
            editTempoPreparo.requestFocus();
            }else
                if(res = isCampoVazio(ServeQuantos)){
                editServeQuantas.requestFocus();
                }else
                    if(res = isCampoVazio(Ingredientes)){
                    editIngredientes.requestFocus();
                    }else
                        if(res = isCampoVazio(ModoPreparo)){
                        editModoPreparo.requestFocus();
                        }

        if (res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.tittle_aviso);
            dlg.setMessage(R.string.message_campos_invalidos_branco);
            dlg.setNeutralButton(R.string.lbl_dialog_ok, null);
            dlg.show();
        }
        return res;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cad_receita, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        return resultado;
    } //testa se existe campo vazio

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_ok:
                confirmar();
                if( !validaCampos() ){
                    Toast.makeText(this, "Receita Salva", Toast.LENGTH_SHORT).show();

                } break;


            case R.id.action_cancelar:
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
