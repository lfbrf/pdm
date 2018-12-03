package br.edu.utfpr.projeto2018;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Medicodao {

    private static final String NOME_TABELA = "Medicos";
    private Context context;


    public Medicodao(Context context) {
        this.context=context;
    }

    public boolean insertMedico(Medico medico){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            Log.v("MEDICONOM", medico.getNome());
            ContentValues values = new ContentValues();
            values.put("nome",medico.getNome());
            values.put("endereco", medico.getEndereco());
            values.put("especialidade", medico.getEspecialidade());

            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateMedico(Medico medico){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+medico.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",medico.getNome());
            values.put("endereco", medico.getEndereco());
            values.put("especialidade", medico.getEspecialidade());
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public ArrayList<Medico> getMedicos(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Medico> medicoArray = new ArrayList<>();
        String getUser = "SELECT * FROM "+ NOME_TABELA+" ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getUser, null);

            if (cursor.moveToFirst()){

                do {
                    Medico u = new Medico();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setEndereco(cursor.getString(2));
                    u.setEspecialidade(cursor.getString(3));
                    medicoArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        //Log.v("Qtde", String.valueOf(userArray.size()));
        return medicoArray;
    }


    public boolean deleteMedico(Medico medico){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteMedico = "_id = '"+medico.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteMedico, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
}