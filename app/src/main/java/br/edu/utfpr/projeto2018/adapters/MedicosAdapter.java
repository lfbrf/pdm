package br.edu.utfpr.projeto2018.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.model.Medico;

public class MedicosAdapter extends BaseAdapter {

    private ArrayList<Medico> medicos;
    private LayoutInflater inflater;

    public MedicosAdapter(Context context, ArrayList<Medico> medicos) {
        this.medicos = medicos;
        this.context= context;
    }


    public void add(Medico m){
        medicos.add(m);
        notifyDataSetChanged();
    }
    public void remove(Medico m){
        medicos.remove(m);
        notifyDataSetChanged();
    }

    private Context context;


    @Override
    public int getCount() {
        return medicos.size();
    }
    @Override
    public Object getItem(int i) {
        return medicos.get(i);
    }
    @Override
    public long getItemId(int i) {
        return medicos.get(i).getId();
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Medico ptemp = medicos.get(i);

        View v = inflater.inflate(R.layout.lista_medicos, null);

        TextView nome = (TextView)v.findViewById(R.id.listamedicoNome);
        TextView endereco = (TextView)v.findViewById(R.id.listamedicoEndereco);
        nome.setText(ptemp.getNome());
        endereco.setText(ptemp.getEndereco());

        return v;
    }
}