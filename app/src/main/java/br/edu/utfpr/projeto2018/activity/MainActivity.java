package br.edu.utfpr.projeto2018.activity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


import br.edu.utfpr.projeto2018.ExitDialog;
import br.edu.utfpr.projeto2018.ExitDialog;
import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.banco.UsuarioDAO;
import br.edu.utfpr.projeto2018.model.Consulta;
import br.edu.utfpr.projeto2018.model.Usuario;
import br.edu.utfpr.projeto2018.UsuariosService;


public class MainActivity extends AppCompatActivity {
    ListView listUser;
    List<Usuario> list = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listUser = (ListView) findViewById(R.id.listViewUsuarios);
    }




    public void consultarUsuario(View view){
        Intent consulta = new Intent(this, ConsultaActivity.class);
        startActivity(consulta);
    }

    public void cadastrarUsuario(View view)
    {


        Intent intent = new Intent(this, UsuariosService.class);
        startService(intent);



        Intent menuUsuario = new Intent(this, CadastroLoginActivity.class);
        startActivity(menuUsuario);

    }












    public void listarUsuarios(View view){
        Intent menuUsuario = new Intent(MainActivity.this, UsuariosActivity.class);
        startActivity(menuUsuario);
    }

    public void cadastrarMedico(View view)
    {

        String user_name = "Seja Bem vindo, Administrador";
        Intent intent = new Intent(this, CadastroMedicoActivity.class);

        intent.putExtra("USER_NAME", user_name);
        startActivity(intent);

    }



    public void sendMessage(View view)
    {
        String user_name = "Administrador";
        Intent intent = new Intent(this, MedicosActivity.class);

        intent.putExtra("USER_NAME", user_name);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mmedicos:
                String user_name = "Seja Bem vindo, Administrador";
                Intent medicos = new Intent(MainActivity.this, CadastroMedicoActivity.class);
                medicos.putExtra("USER_NAME", user_name);
                startActivity(medicos);
                return true;
            case R.id.musuarios:
                Intent menuUsuario = new Intent(MainActivity.this, UsuariosActivity.class);
                startActivity(menuUsuario);
                return true;

            case R.id.mconsultas:
                Intent consulta = new Intent(MainActivity.this, ConsultaActivity.class);
                startActivity(consulta);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void toHome(View view){
        FragmentManager manager = getFragmentManager();
        ExitDialog alertDialogFragment = new ExitDialog();
        alertDialogFragment.show(manager, "fragment_edit_name");

    }



}
