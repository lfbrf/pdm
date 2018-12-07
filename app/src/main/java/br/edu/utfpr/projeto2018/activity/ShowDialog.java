package br.edu.utfpr.projeto2018.activity;

import android.app.Activity;
import android.app.FragmentManager;
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

import br.edu.utfpr.projeto2018.ExitDialog;
import br.edu.utfpr.projeto2018.adapters.ListAdapterConsulta;
import br.edu.utfpr.projeto2018.adapters.ListAdapterMedico;
import br.edu.utfpr.projeto2018.banco.ConsultaDao;
import br.edu.utfpr.projeto2018.banco.Medicodao;
import br.edu.utfpr.projeto2018.banco.UsuarioDAO;
import br.edu.utfpr.projeto2018.model.Consulta;
import br.edu.utfpr.projeto2018.model.Medico;
import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.model.Usuario;

public class ShowDialog extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);
        FragmentManager manager = getFragmentManager();
        ExitDialog alertDialogFragment = new ExitDialog();
        alertDialogFragment.show(manager, "fragment_edit_name");
    }
}


