package com.example.diego.receitas.ui.main.RecyclerRecipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diego.receitas.R;
import com.example.diego.receitas.Entidades.Receita;

import java.util.List;

/**
 * Created by diego on 22/11/2017.
 */

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.HolderReceita>  {

    private List<Receita> receitasCadastradas;

    public AdapterReceita(List<Receita> receitasCadastradas){

        this.receitasCadastradas = receitasCadastradas;
    }




    public HolderReceita onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.adapter_recipe, parent, false);


        return new HolderReceita(view);
    }




    public void onBindViewHolder(HolderReceita holder, int position) {

        if(receitasCadastradas != null && (receitasCadastradas.size() > 0) ) {

            holder.nomeReceita.setText(receitasCadastradas.get(position).nomereceita);

        }

    }



    @Override
    public int getItemCount() {

        return receitasCadastradas.size();
    }

    public class HolderReceita extends RecyclerView.ViewHolder {

        protected TextView nomeReceita;

        public HolderReceita(View itemView) {
            super(itemView);

            this.nomeReceita = itemView.findViewById(R.id.list_nome);

        }
    }


}
