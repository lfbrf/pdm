package br.edu.utfpr.projeto2018.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.utfpr.projeto2018.R;
import br.edu.utfpr.projeto2018.adapters.ListAdapterUsuario;
import br.edu.utfpr.projeto2018.banco.UsuarioDAO;
import br.edu.utfpr.projeto2018.model.Usuario;


public class UsuariosActivity extends AppCompatActivity {


    EditText nomeCompletoUser, usuarioUser, senhaUser, cargoUser, telefoneUser;
    Button btnSalvarUser, btnVoltarUser, btnExcluirUser, btnEditarUser;
    ImageButton imageButton1;
    ListView listUser;
    ArrayList<Usuario> usuarios;

    Usuario usuario;



    public void toHome(View view){
        Intent menuUsuario = new Intent(this, MainActivity.class);
        startActivity(menuUsuario);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        nomeCompletoUser = (EditText) findViewById(R.id.txtCadNomeCompletoUser);
        usuarioUser = (EditText) findViewById(R.id.txtCadUsuarioUser);
        senhaUser = (EditText) findViewById(R.id.txtCadPasswordUser);
        cargoUser = (EditText) findViewById(R.id.txtCadCargoUser);
        telefoneUser = (EditText) findViewById(R.id.txtCadPhoneUser);

        btnExcluirUser = (Button) findViewById(R.id.btnExcluirUser);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);

        btnVoltarUser = (Button) findViewById(R.id.btnVoltarUser);
        btnSalvarUser = (Button) findViewById(R.id.btnSalvarUser);
        btnEditarUser = (Button) findViewById(R.id.btnEditarUser);

        listUser = (ListView) findViewById(R.id.listViewUsuarios);

        btnVoltarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSalvarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                usuario = new Usuario();
                usuario.setNome(nomeCompletoUser.getText().toString());
                usuario.setUsuario(usuarioUser.getText().toString());
                usuario.setSenha(senhaUser.getText().toString());
                usuario.setTelefone(telefoneUser.getText().toString());

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (nomeCompletoUser.length() == 0 || usuarioUser.length() == 0 || senhaUser.length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("O campo nome ou usuario ou senha nao podem estar vazio");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }else if (usuarioDAO.insertUsuario(usuario)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Cadastro de Usuario");
                    builder.setMessage("Cadastro realizado com sucesso");
                    notifyThis("Usuario Cadastrado", "Nome: "+ usuario.getNome());
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();

                    limparCampos();
                    usuario = null;
                    atualizaListaUsuarios();

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao cadastrar usuario");
                    builder.setMessage("Nao foi possivel cadastrar usuario");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Cadastro Negado" , Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();
                }
            }
        });

        btnEditarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (usuario==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setMessage("Nenhum usuario selecionado.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            //Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {

                    if (nomeCompletoUser.length() == 0 || usuarioUser.length() == 0 || senhaUser.length() == 0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                        builder.setTitle("Erro ao atualizar o usuario");
                        builder.setMessage("Nenhum usuario selecionado para atualizar, os campos nao podem estar vazios.");
                        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(UsuariosActivity.this, "Cadastro Negado", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    } else{

                        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                        usuario.setNome(nomeCompletoUser.getText().toString());
                        usuario.setUsuario(usuarioUser.getText().toString());
                        usuario.setSenha(senhaUser.getText().toString());
                        usuario.setTelefone(telefoneUser.getText().toString());

                        usuarioDAO.updateUsuario(usuario);

                        AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                        builder.setTitle("Atualização do Usuario");
                        builder.setMessage("Cadastro Atualizado com sucesso");
                        builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                        builder.show();
                        limparCampos();
                        atualizaListaUsuarios();

                    }
                }

            }
        });


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UsuariosActivity.this, "Crud completo no mesmo XML", Toast.LENGTH_SHORT).show();
                Toast.makeText(UsuariosActivity.this, "Aqui e possivel criar, editar e excluir usuarios", Toast.LENGTH_SHORT).show();

            }
        });

        btnExcluirUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());

                if (usuario==null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Erro ao excluir o usuario");
                    builder.setMessage("Nenhum usuario selecionado para excluir, os campos nao podem estar vazios.");
                    builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(UsuariosActivity.this, "Exclusao Negada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }else {
                    usuarioDAO.deleteUsuario(usuario);
                    AlertDialog.Builder builder = new AlertDialog.Builder(UsuariosActivity.this);
                    builder.setTitle("Exclusão do Usuario");
                    builder.setMessage("Cadastro excluido com sucesso");
                    builder.setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

                        }
                    });
                    builder.show();
                    usuario = null;
                    limparCampos();
                    atualizaListaUsuarios();


                }
            }
        });

        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.getUsuarios();
        //aqui pegar do bundle
        listUser.setAdapter(
                new ListAdapterUsuario(this, usuarios)
        );

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                populaCampos(i);
            }
        });
    }

    public void atualizaListaUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        usuarios = usuarioDAO.getUsuarios();

        listUser.setAdapter(
                new ListAdapterUsuario(this, usuarios)
        );
    }

    public void populaCampos(int i){
        usuario = usuarios.get(i);

        nomeCompletoUser.setText(usuario.getNome());
        usuarioUser.setText(usuario.getUsuario());
        senhaUser.setText(usuario.getSenha());
        cargoUser.setText(usuario.getCargo());
        telefoneUser.setText(usuario.getTelefone());
    }

    public void limparCampos(){

        nomeCompletoUser.setText("");
        usuarioUser.setText("");
        senhaUser.setText("");
        telefoneUser.setText("");
        cargoUser.setText("");

    }

    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }


}