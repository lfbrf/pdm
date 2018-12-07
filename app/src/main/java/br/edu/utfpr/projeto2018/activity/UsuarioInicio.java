package br.edu.utfpr.projeto2018.activity;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.activity.MainActivity;

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
