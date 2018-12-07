package br.edu.utfpr.projeto2018.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.model.Consulta;
import br.edu.utfpr.projeto2018.model.Medico;

public class ListAdapterConsulta extends ArrayAdapter<Consulta> {

    private Context context;
    private ArrayList<Consulta> listaConsulta;

    public ListAdapterConsulta(@NonNull Context context, ArrayList<Consulta> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaConsulta = lista;
        Log.i("PDMLog","1111");

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Consulta itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_consultas, null);



        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeListaConsultaM);
        txtNome.setText(itemPosicao.getMedico());
        TextView txtUsuario = (TextView) convertView.findViewById(R.id.txtNomeListaConsultaU);
        txtUsuario.setText(itemPosicao.getUsuario());

        TextView txtSenha = (TextView) convertView.findViewById(R.id.txtConsultaData);
        txtSenha.setText(itemPosicao.getData());


        return convertView;
    }


}