package com.example.diego.receitas.Main;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.diego.receitas.Entidades.Receita;
import com.example.diego.receitas.R;
import com.example.diego.receitas.database.DadosOpenHelper;
import com.example.diego.receitas.database.Repositorio.RepositorioReceita;
import com.example.diego.receitas.ui.main.RecyclerRecipes.AdapterReceita;
import com.example.diego.receitas.ui.main.RecyclerRecipes.RecyclerItemClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;



public class Tab1 extends Fragment {
    private LinearLayout LinearLayoutRV;
    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;
    private FirebaseAuth autenticacao;
    private FirebaseUser usuariologado;
    private RepositorioReceita repositorioReceita;
    private RecyclerView rv;
    private AdapterReceita adapterReceita;
    private boolean firstVisit;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        return view;



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);
        firstVisit = true;
        rv = (RecyclerView) view.findViewById(R.id.new_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //lstDados.setLayoutManager(new GridLayoutManager(this, 2));

        LinearLayoutRV = (LinearLayout) view.findViewById(R.id.new_linearlayout_rv);

        criarConexao();
        repositorioReceita = new RepositorioReceita(conexao);

        final List<Receita> receitasCadastradas = repositorioReceita.buscartodos();
        adapterReceita = new AdapterReceita(receitasCadastradas);
        rv.setAdapter(adapterReceita);
        rv.setHasFixedSize(true);

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(),
                        rv,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Receita receitapress = receitasCadastradas.get(position);
                                Toast.makeText(getContext(), "Card Pressionado: " + receitapress.getNomereceita(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Receita receitapress = receitasCadastradas.get(position);
                                Toast.makeText(getContext(), "Card Click Longo: " + receitapress.getNomereceita(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        })
        );

    }

    public void onResume(View view){
        super.onResume();

        if(firstVisit){
            rv = (RecyclerView) view.findViewById(R.id.new_rv);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            //lstDados.setLayoutManager(new GridLayoutManager(this, 2));

            LinearLayoutRV = (LinearLayout) view.findViewById(R.id.new_linearlayout_rv);

            criarConexao();
            repositorioReceita = new RepositorioReceita(conexao);

            List<Receita> receitasCadastradas = repositorioReceita.buscartodos();
            adapterReceita = new AdapterReceita(receitasCadastradas);
            rv.setAdapter(adapterReceita);

        }
    }

    private void criarConexao(){
        try{

            dadosOpenHelper = new DadosOpenHelper(getActivity());

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(LinearLayoutRV, "Bem Vindo!", Snackbar.LENGTH_SHORT)
                    .setAction("Ok", null).show();

        }catch (SQLException ex){

            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok",null);
            dlg.show();

        }
    }
}
