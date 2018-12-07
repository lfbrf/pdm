package br.edu.utfpr.projeto2018.activity;


import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.projeto2018.adapters.ListAdapterConsulta;
import br.edu.utfpr.projeto2018.adapters.ListAdapterMedico;
import br.edu.utfpr.projeto2018.banco.ConsultaDao;
import br.edu.utfpr.projeto2018.banco.Medicodao;
import br.edu.utfpr.projeto2018.banco.UsuarioDAO;
import br.edu.utfpr.projeto2018.model.Consulta;
import br.edu.utfpr.projeto2018.model.Medico;
import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.model.Usuario;


public class ConsultaActivity extends Activity {
    Button mButton;
    EditText mEdit;
    ListView lViewMedicos;
    ArrayList<Consulta> md;


    Spinner spinnerUser, spinnerMedico;
    ListView listConsulta;

    EditText txtConsultaData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consultas);
        spinnerUser = (Spinner) findViewById(R.id.spinnerConsultas);
        spinnerMedico = (Spinner) findViewById(R.id.spinnerMedicos);
        listConsulta = (ListView) findViewById(R.id.lvconsultas);
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        Medicodao  medicoDao = new Medicodao(this);
        List<Medico> medicos = medicoDao.getMedicosSpinner();
        List<Usuario> labels = usuarioDAO.getusuariosSpinner();
        ArrayAdapter userAdpt = new ArrayAdapter (this, R.layout.support_simple_spinner_dropdown_item, labels);

        ArrayAdapter medicoAdpt = new ArrayAdapter (this, R.layout.support_simple_spinner_dropdown_item, medicos);
        ConsultaDao cd = new ConsultaDao(this);
         md = cd.getConsulta();

         /*
        listConsulta.setAdapter(
                new ListAdapterConsulta(this, md)
        );

        listConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerForContextMenu(listConsulta);

            }
        });
        */

        spinnerUser.setAdapter(userAdpt);
        spinnerMedico.setAdapter(medicoAdpt);
    }



    public void cadastrarConsulta(View view){
        Usuario usuario = (Usuario) spinnerUser.getSelectedItem();
        Medico medico = (Medico) spinnerMedico.getSelectedItem();
        txtConsultaData = (EditText) findViewById(R.id.txtConsultaData);
        Consulta c = new Consulta();
        Log.i("ANTES","aa" + medico.getId());
        Log.i("ANTES","aa" + usuario.getId());
        c.setMedico(medico.getId());
        c.setUsuario(usuario.getId());
        Log.i("ANTES","aa" + usuario.getId());
        c.setData(txtConsultaData.getText().toString());
        Log.i("INICIO","aa");
        ConsultaDao consultaDao = new ConsultaDao(getApplicationContext());
        Log.i("INICIO","BBB");
        consultaDao.insertConsulta(c);
    }

}


