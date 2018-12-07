package br.edu.utfpr.projeto2018.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.projeto2018.banco.CriaBanco;
import br.edu.utfpr.projeto2018.model.Usuario;


public class UsuarioDAO {

    private static final String NOME_TABELA = "Usuarios";
    private Context context;


    public UsuarioDAO(Context context) {
        this.context=context;
    }

    public boolean insertUsuario(Usuario usuario){
        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();


        try{
            ContentValues values = new ContentValues();
            values.put("nome",usuario.getNome());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("cargo", usuario.getCargo());
            values.put("telefone", usuario.getTelefone());
            db.insert(NOME_TABELA,null,values);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updateUsuario(Usuario usuario){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        try{
            String where = " _id = '"+usuario.getId()+"'";
            ContentValues values = new ContentValues();
            values.put("nome",usuario.getNome());
            values.put("usuario", usuario.getUsuario());
            values.put("senha", usuario.getSenha());
            values.put("cargo", usuario.getCargo());
            values.put("telefone", usuario.getTelefone());
            db.update(NOME_TABELA, values, where,null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public ArrayList<Usuario> getUsuarios(){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        ArrayList<Usuario> userArray = new ArrayList<>();
        String getUser = "SELECT * FROM "+ NOME_TABELA+" ORDER by _id ASC";

        try {
            Cursor cursor = db.rawQuery(getUser, null);

            if (cursor.moveToFirst()){

                do {
                    Usuario u = new Usuario();
                    u.setId(cursor.getInt(0));
                    u.setNome(cursor.getString(1));
                    u.setUsuario(cursor.getString(2));
                    u.setSenha(cursor.getString(3));
                    u.setCargo(cursor.getString(4));
                    u.setTelefone(cursor.getString(5));
                    userArray.add(u);

                }while (cursor.moveToNext());

            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            db.close();
        }
        //Log.v("Qtde", String.valueOf(userArray.size()));
        return userArray;
    }


    public boolean deleteUsuario(Usuario usuario){

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        String deleteUsuario = "_id = '"+usuario.getId()+"'";

        try {
            db.delete(NOME_TABELA, deleteUsuario, null);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public List<Usuario> getusuariosSpinner(){

        List<Usuario> list = new ArrayList<Usuario>();

        CriaBanco criaBanco = new CriaBanco(context);
        SQLiteDatabase db = criaBanco.getReadableDatabase();

        String selectQuery = "SELECT * FROM "+ NOME_TABELA;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {

                list.add(new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;

    }
}