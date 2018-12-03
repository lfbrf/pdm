package br.edu.utfpr.projeto2018;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.widget.Toast;
public class ListAdapterMedico extends ArrayAdapter<Medico> {

    private Context context;
    private ArrayList<Medico> listaMedico;

    public ListAdapterMedico(@NonNull Context context, ArrayList<Medico> lista) {
        super(context, 0, lista);
        this.context = context;
        this.listaMedico = lista;
        Log.i("PDMLog","1111");

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Log.i("PDMLog","222");
        Medico itemPosicao = this.getItem(position);

        convertView = LayoutInflater.from(this.context).inflate(R.layout.item_lista_medicos, null);



        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNomeListaMedico);
        txtNome.setText(itemPosicao.getNome());
        Log.i("PDMLog",txtNome.getText() + "");
        TextView txtUsuario = (TextView) convertView.findViewById(R.id.txtEnderecoListaMedico);
        txtUsuario.setText(itemPosicao.getEndereco());

        TextView txtSenha = (TextView) convertView.findViewById(R.id.txtEspecialidadeMedico);
        txtSenha.setText(itemPosicao.getEspecialidade());


        return convertView;
    }


}