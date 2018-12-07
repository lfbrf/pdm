package br.edu.utfpr.projeto2018;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import br.edu.utfpr.projeto2018.activity.MainActivity;

public class ExitDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setIcon(android.R.drawable.stat_notify_error)
                .setTitle("Demonstracao do Dialog?")
                .setMessage("Confirmar?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Voce confirmou", Toast.LENGTH_SHORT).show();
                        ((MainActivity)getActivity()).finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Voce cancelou", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}