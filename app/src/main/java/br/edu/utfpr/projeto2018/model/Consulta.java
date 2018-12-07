package br.edu.utfpr.projeto2018.model;




public class Consulta  {

    private int id;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    private int usuario;

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getUsuario() {
        return usuario;
    }

    public int getMedico() {
        return medico;
    }

    public void setMedico(int medico) {
        this.medico = medico;
    }

    private int medico;
    //Construtor para ser instanciada a classe em alguma outra classe do projeto
    public Consulta() {

    }

    public Consulta(int id, String data, int medico, int usuario) {
        this.id = id;
        this.data = data;
        this.usuario = usuario;
        this.medico = medico;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return data;
    }


    @Override
    public int hashCode(){
        return this.id;
    }


}