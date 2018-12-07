package br.edu.utfpr.projeto2018.activity;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.utfpr.projeto2018.model.Medico;
import br.edu.utfpr.projeto2018.R;


public class MedicosActivity extends Activity {
    Button mButton;
    EditText mEdit;
    ListView lViewMedicos;

    public void toHome(View view){
        Intent menuUsuario = new Intent(this, MainActivity.class);
        startActivity(menuUsuario);
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



        /*
        *
       Log.d("PDM1", "1");
        List<Medico> medicos = null;
        medicos = MedicosDatabase.getInstance(this).dao().all();
        Log.d("PDM2", "2");
        ArrayAdapter<Medico> adapter = new ArrayAdapter<Medico>(this,
                android.R.layout.simple_list_item_1, medicos);
        Log.d("PDM3", "3");
        lViewMedicos.setAdapter(adapter);
        Toast.makeText(this,
                "salvo", Toast.LENGTH_LONG).show();
       */
        // do stuff
    }


    //public int getCount() {
      //  return MedicosDatabase.getInstance(this).dao().all().size();
    //}


    public void sendForm(View view) {
        Toast.makeText(this,
                "OK", Toast.LENGTH_LONG).show();
        doAsync(this::run);

    }


    private void doAsync (Runnable runnable) {
        new Thread(runnable).start();
    }


    private void run() {
        mEdit = (EditText) findViewById(R.id.txtName);
        String x;
        x = mEdit.getText().toString();
        CheckBox unimed = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox cassi = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox itau = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox bradesco = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox outro = (CheckBox) findViewById(R.id.checkBox6);
        CheckBox santander = (CheckBox) findViewById(R.id.checkBox7);
        CheckBox clinipam = (CheckBox) findViewById(R.id.checkBox8);
        RadioButton masc = (RadioButton) findViewById(R.id.radioMasc);
        RadioButton fem = (RadioButton) findViewById(R.id.radioFem);


        Toast.makeText(this,
                "salvo", Toast.LENGTH_LONG).show();


        Log.d("PDM", x);
        Medico m = new Medico(0, x, "ENDERECO", "especialidade", bradesco.isChecked(), cassi.isChecked(),
                clinipam.isChecked(), itau.isChecked(), outro.isChecked(), santander.isChecked(),
                unimed.isChecked(), fem.isChecked(), masc.isChecked());
        //MedicosDatabase.getInstance(this).dao().insert(m);
        //for (Medico temp : MedicosDatabase.getInstance(this).dao().all())
          //  Log.d("PDM", temp.toString());
    }


}


