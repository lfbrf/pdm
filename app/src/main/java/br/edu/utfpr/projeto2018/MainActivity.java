package br.edu.utfpr.projeto2018;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listUser = (ListView) findViewById(R.id.listViewUsuarios);


    }


    public void cadastrarUsuario(View view)
    {
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



    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("PDMLog","Criando um menu a partir de um XML.");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void toHome(View view){

        Intent menuUsuario = new Intent(MainActivity.this, UsuarioInicio.class);
        startActivity(menuUsuario);

    }

}
