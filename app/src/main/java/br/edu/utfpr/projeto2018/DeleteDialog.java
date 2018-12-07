package br.edu.utfpr.projeto2018;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.activity.CadastroMedicoActivity;
import br.edu.utfpr.projeto2018.activity.MainActivity;
import br.edu.utfpr.projeto2018.banco.Medicodao;
import br.edu.utfpr.projeto2018.model.Medico;

public class DeleteDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.stat_notify_error)
                .setTitle("Demonstracao do Dialog?")
                .setMessage("Confirmar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("EII", "EII");
                        Bundle bundle  = new Bundle();
                        bundle.putInt("VALOR", 1);
                        Log.v("EII2", "EII2");
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Voce cancelou", Toast.LENGTH_SHORT).show();

                    }
                }).create();
    }

}