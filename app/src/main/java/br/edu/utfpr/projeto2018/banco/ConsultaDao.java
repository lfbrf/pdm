package br.edu.utfpr.projeto2018.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.model.Consulta;

public class ConsultaDao {

    private static final String NOME_TABELA = "Consulta";
    private Context context;


    public ConsultaDao(Context context) {
        this.context=context;
    }

    public boolean insertConsulta(Consulta consulta){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("data",consulta.getData());
            values.put("medico", consulta.getMedico());
            values.put("usuario", consulta.getUsuario());
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }


    public ArrayList<Consulta> getConsulta(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Consulta> alunoArray = new ArrayList<>();
        String getAluno = "SELECT * FROM "+ NOME_TABELA + " ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getAluno, null);

            if (cursor.moveToFirst()){

                do {
                    Consulta c = new Consulta();
                    c.setId(cursor.getInt(0));
                    c.setData(cursor.getString(1));
                    c.setUsuario(cursor.getInt(2));
                    c.setMedico(cursor.getInt(3));
                    alunoArray.add(c);
                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        return alunoArray;
    }


}
