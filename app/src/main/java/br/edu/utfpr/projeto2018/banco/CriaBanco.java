package br.edu.utfpr.projeto2018.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco";
    private static final int VERSAO = 9;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Medicos");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Consulta");
        db.execSQL(ScriptSQL.getCreateUsuarios());
        db.execSQL(ScriptSQL.getCreateMedicos());
        db.execSQL(ScriptSQL.getCreateConsultas());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Medicos");
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS Consulta");
        onCreate(db);

    }

}