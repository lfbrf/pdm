package br.edu.utfpr.projeto2018;



import android.content.Intent;
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

public class UsuarioInicio extends AppCompatActivity {

    ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarioinicio);


    }


    public void toHome(View view){

        Log.v("AIII", "AIII");
        Intent menuUsuario = new Intent(this, MainActivity.class);
        startActivity(menuUsuario);

    }

}
