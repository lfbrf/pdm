package br.edu.utfpr.projeto2018;


public class ScriptSQL {

    public static String getCreateUsuarios(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Usuarios( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("usuario VARCHAR(50), ");
        stringBuilder.append("senha VARCHAR(20), ");
        stringBuilder.append("cargo VARCHAR(50), ");
        stringBuilder.append("telefone VARCHAR(14)); ");

        return stringBuilder.toString();
    }

    public static String getCreateMedicos(){
        //junção de diversas string sql
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE Medicos( ");
        stringBuilder.append("_id INTEGER PRIMARY KEY, ");
        stringBuilder.append("nome VARCHAR(100), ");
        stringBuilder.append("endereco VARCHAR(50), ");
        stringBuilder.append("sexom BOOLEAN(2), ");
        stringBuilder.append("sexof BOOLEAN(2), ");
        stringBuilder.append("conbr BOOLEAN(2), ");
        stringBuilder.append("conca BOOLEAN(2), ");
        stringBuilder.append("concli BOOLEAN(2), ");
        stringBuilder.append("conit BOOLEAN(2), ");
        stringBuilder.append("conou BOOLEAN(2), ");
        stringBuilder.append("consa BOOLEAN(2), ");
        stringBuilder.append("conun BOOLEAN(2), ");
        stringBuilder.append("especialidade VARCHAR(20)); ");
        return stringBuilder.toString();
    }


}