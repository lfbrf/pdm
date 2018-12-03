package br.edu.utfpr.projeto2018;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class CadastroMedicoActivity extends AppCompatActivity {
    private int id = -1;
    Button btnEnviar, btnCancelar;
    EditText mEdit, endereco ,especialidade;
    ListView listMedico;
    ListView listUser;
    ArrayList<Medico> medicos;
    RadioButton fem, masc;
    CheckBox unimed, santander, cassi, itau, bradesco, outro, clinipam;

    public void toHome(View view){
        Intent menuUsuario = new Intent(this, MainActivity.class);
        startActivity(menuUsuario);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Deletar");

    }

    public void atualizaListaMedicos(){
        Medicodao medicoDAO = new Medicodao(this);
        medicos = medicoDAO.getMedicos();

        listMedico.setAdapter(
                new ListAdapterMedico(this, medicos)
        );
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int index = menuInfo.position;
        long id = menuInfo.id;
        int cont= 0;
        int i = (int) id;
        int z = 0;
        int ind = 0;
        String x = "" + id;
        Medicodao medicoDAO = new Medicodao(getApplicationContext());
        Medico m = new Medico();


        ArrayList<Medico> arrayList = medicoDAO.getMedicos();



        for (Medico medic:arrayList){
           if (i == cont)
               medicoDAO.deleteMedico(medic);
            cont ++ ;
        }


        if (medicoDAO.deleteMedico(m))
            Log.v("Usuarios", item.getTitle().toString());
        Log.v("UsuariosID", x);
        atualizaListaMedicos();
        return true;
    }

    public void notifyThis(String title, String message) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this);
        b.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setTicker("{your tiny message}")
                .setContentTitle(title)
                .setContentText(message)
                .setContentInfo("INFO");

        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, b.build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(CadastroMedicoActivity.this,
                "AAAAA", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("USER_NAME");
            Log.d("MEU_USER_NAME", j);
            TextView textView = (TextView) findViewById(R.id.valordinamico);
            textView.setText(j);
        }


        listMedico = (ListView) findViewById(R.id.lvmedicos);

        listUser = (ListView) findViewById(R.id.listViewUsuarios);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        mEdit = (EditText) findViewById(R.id.txtName);
        endereco = (EditText) findViewById(R.id.txtEndereco);
        especialidade = (EditText) findViewById(R.id.txtEspecialidade);
        String x;
        x = mEdit.getText().toString();
         unimed = (CheckBox) findViewById(R.id.checkBox1);
         cassi = (CheckBox) findViewById(R.id.checkBox2);
         itau = (CheckBox) findViewById(R.id.checkBox4);
         bradesco = (CheckBox) findViewById(R.id.checkBox5);
         outro = (CheckBox) findViewById(R.id.checkBox6);
         santander = (CheckBox) findViewById(R.id.checkBox7);
         clinipam = (CheckBox) findViewById(R.id.checkBox8);
         masc = (RadioButton) findViewById(R.id.radioMasc);
         fem = (RadioButton) findViewById(R.id.radioFem);

        Medicodao medicoDAO = new Medicodao(getApplicationContext());
         medicos = medicoDAO.getMedicos();

        Toast.makeText(CadastroMedicoActivity.this,
                "BBB", Toast.LENGTH_LONG).show();

        listMedico.setAdapter(
                new ListAdapterMedico(this, medicos)
        );

        listMedico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                registerForContextMenu(listMedico);

            }
        });



    }

    public void enviarCad(View view)
    {

        Log.v("Usuarios", "ini");
        //pegando os valores
        String nome = mEdit.getText().toString();

        //salvando os dados
        Medico medico = new Medico();

        String name = mEdit.getText().toString();
        String end = endereco.getText().toString();
        String esp = especialidade.getText().toString();

        medico.setEndereco(end);
        medico.setNome(name);
        medico.setEspecialidade("LALAL");
        medico.setConvenioUnimed(unimed.isChecked());
        medico.setConvenioSantander(santander.isChecked());
        medico.setConvenioOutro(outro.isChecked());
        medico.setConvenioItau(itau.isChecked());
        medico.setConvenioClinipam(clinipam.isChecked());
        medico.setConvenioCassi(cassi.isChecked());
        medico.setConvenioBradesco(bradesco.isChecked());
        medico.setSexoFeminino(fem.isChecked());
        medico.setSexoMasculino(masc.isChecked());
        Medicodao medicoDAO = new Medicodao(getApplicationContext());
        if (medicoDAO.insertMedico(medico)){
            notifyThis("Medico Cadastrado", "Nome:"+ medico.getNome());
        }
        Log.v("Usuarios", "OLA2");
/*      CONTINUAR DAQUI, PRECISO CADASTRAR TODOS ELEM NO BANCO
        ArrayList<Medico> arrayList = medicoDAO.getMedicos();//
        for (Medico medic:arrayList){
            String valor = " senha:"+medic.getNome();
            Log.v("Usuarios", valor);
        }
*/
        ArrayList<Medico> arrayList = medicoDAO.getMedicos();
        for (Medico medic:arrayList){
            String valor = " senha:"+medic.getNome();
            Log.v("Usuarios?", valor);
        }
        atualizaListaMedicos();

        Log.v("Usuarios", "OLA");

    }


}
