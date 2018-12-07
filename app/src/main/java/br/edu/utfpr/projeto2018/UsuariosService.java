package br.edu.utfpr.projeto2018;


import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.activity.CadastroLoginActivity;
import br.edu.utfpr.projeto2018.activity.MainActivity;
import br.edu.utfpr.projeto2018.activity.ShowDialog;
import br.edu.utfpr.projeto2018.banco.ConsultaDao;
import br.edu.utfpr.projeto2018.banco.Medicodao;
import br.edu.utfpr.projeto2018.banco.UsuarioDAO;
import br.edu.utfpr.projeto2018.model.Consulta;
import br.edu.utfpr.projeto2018.model.Medico;
import br.edu.utfpr.projeto2018.model.Usuario;


public class UsuariosService extends Service {

    private static final String TAG = "UsuariosService";

    private boolean isRunning  = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");

        isRunning = true;
    }

    ArrayList<Usuario> usuarios;
    ArrayList<Medico> medicos;
    ArrayList<Consulta> consultas;
    int u,c,m;
    public int usuarios (){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.getUsuarios();
        return usuarios.size();
    }

    public int medicos (){
        Medicodao medicoDao = new Medicodao(this);
        medicos = medicoDao.getMedicos();
        return medicos.size();
    }

    public int consultas (){
        ConsultaDao consultaDao = new ConsultaDao(this);
        consultas = consultaDao.getConsulta();
        return consultas.size();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");

         new Thread(new Runnable() {
            @Override
            public void run() {


                for (int i = 0; i < 3; i++) {
                    try {
                        Thread.sleep(4000);
                    } catch (Exception e) {
                    }

                    if(isRunning){
                        if (i==0) {
                            u = usuarios();
                            notifyThis("Total de Usuarios", u + "");
                        }
                        if (i ==1) {
                            m = medicos();
                            notifyThis("Total de Medicos", m + "");
                        }
                        if (i == 2) {
                            c = consultas();
                            notifyThis("Total de Consultas", c + "");
                        }
                    }

                }

                //Stop service once it finishes its task
                stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    private Context context;



    @Override
    public void onDestroy() {
        isRunning = false;
        Toast.makeText(this,"Resultados de dados, vindo do servico =D",Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Usuarios" + u ,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Medicos" + m,Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Consultas" + c,Toast.LENGTH_LONG).show();
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
}