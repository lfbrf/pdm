package br.edu.utfpr.projeto2018;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

public class CadastroLoginActivity extends AppCompatActivity {

    Button btnCadastrar, btnCancelar;
    TextView txtTitle;
    EditText editTextNomeCompleto, editTextUsuario, editTextSenha, editTextCargo, editTextTelefone;


    public void toHome(View view){
        Intent menuUsuario = new Intent(this, MainActivity.class);
        startActivity(menuUsuario);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Thanks for using application!!",Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
        editTextNomeCompleto = (EditText) findViewById(R.id.editTextCadastroNomeCompleto);
        editTextUsuario = (EditText) findViewById(R.id.editTextCadastroUsuario);
        editTextSenha = (EditText) findViewById(R.id.editTextCadastroSenha);
        editTextCargo = (EditText)findViewById(R.id.editTextCadastroCargo);
        editTextTelefone = (EditText) findViewById(R.id.editTextCadastroTelefone);

        String nome = editTextNomeCompleto.getText().toString();
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();
        String cargo = editTextCargo.getText().toString();
        String telefone = editTextTelefone.getText().toString();

        editor.putString("nome", nome);
        editor.putString("usuario", usuario);
        editor.putString("senha", senha);
        editor.putString("cargo", cargo);
        editor.putString("telefone", telefone);
        editor.apply();
        finish();
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_login);

        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);


        txtTitle = (TextView) findViewById(R.id.textViewCadastroTitle);
        editTextNomeCompleto = (EditText) findViewById(R.id.editTextCadastroNomeCompleto);
        editTextUsuario = (EditText) findViewById(R.id.editTextCadastroUsuario);
        editTextSenha = (EditText) findViewById(R.id.editTextCadastroSenha);
        editTextCargo = (EditText)findViewById(R.id.editTextCadastroCargo);
        editTextTelefone = (EditText) findViewById(R.id.editTextCadastroTelefone);
        btnCadastrar = (Button) findViewById(R.id.buttonCadastrarUsuario);
        btnCancelar = (Button) findViewById(R.id.buttonCancelarCadastro);



            String nome = prefs.getString("nome", "");//"No name defined" is the default value.
            String usuario = prefs.getString("usuario", "");//"No name defined" is the default value.
            String cargo = prefs.getString("cargo", "");//"No name defined" is the default value.
            String senha = prefs.getString("senha", "");//"No name defined" is the default value.
            String telefone = prefs.getString("telefone", "");//"No name defined" is the default value.

            editTextNomeCompleto.setText(nome);
            editTextUsuario.setText(usuario);
            editTextCargo.setText(cargo);
            editTextSenha.setText(senha);
            editTextTelefone.setText(telefone);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                editor.clear();
                editor.apply();
                //pegando os valores
                String nome = editTextNomeCompleto.getText().toString();
                String usuario = editTextUsuario.getText().toString();
                String senha = editTextSenha.getText().toString();
                String cargo = editTextCargo.getText().toString();
                String telefone = editTextTelefone.getText().toString();

                //salvando os dados
                Usuario usuario1 = new Usuario();
                usuario1.setNome(editTextNomeCompleto.getText().toString());
                usuario1.setUsuario(editTextUsuario.getText().toString());
                usuario1.setSenha(editTextSenha.getText().toString());
                usuario1.setTelefone(editTextTelefone.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (nome.length() == 0 || usuario.length() == 0 || senha.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Erro ao entrar no sistema");
                    builder.setMessage("O campo nome ou usuario ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(CadastroLoginActivity.this, "Cadastro Negado=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                } else if (usuarioDAO.insertUsuario(usuario1)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Cadastro de Usuario");
                    builder.setMessage("Cadastro realizado com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent telaLogin = new Intent(CadastroLoginActivity.this, MainActivity.class);
                            startActivity(telaLogin);
                        }
                    });
                    builder.show();

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroLoginActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("Nao foi possivel cadastrar usuario");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(CadastroLoginActivity.this, "Cadastro Negado=" + arg1, Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }

            }
        });
    }
}
