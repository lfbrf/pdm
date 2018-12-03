package br.edu.utfpr.projeto2018;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import java.io.Serializable;
import java.util.List;

@Entity
public class Medico implements Serializable{
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nome;

    @Ignore
    public Medico() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String endereco;

    public String especialidade;

    public boolean sexoMasculino;


    public boolean sexoFeminino;

    public boolean convenioUnimed;

    public boolean convenioCassi;

    public boolean convenioItau;

    public boolean convenioBradesco;

    public boolean convenioOutro;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isSexoMasculino() {
        return sexoMasculino;
    }

    public void setSexoMasculino(boolean sexoMasculino) {
        this.sexoMasculino = sexoMasculino;
    }

    public boolean isSexoFeminino() {
        return sexoFeminino;
    }

    public void setSexoFeminino(boolean sexoFeminino) {
        this.sexoFeminino = sexoFeminino;
    }

    public boolean isConvenioUnimed() {
        return convenioUnimed;
    }

    public void setConvenioUnimed(boolean convenioUnimed) {
        this.convenioUnimed = convenioUnimed;
    }

    public boolean isConvenioCassi() {
        return convenioCassi;
    }

    public void setConvenioCassi(boolean convenioCassi) {
        this.convenioCassi = convenioCassi;
    }

    public boolean isConvenioItau() {
        return convenioItau;
    }

    public void setConvenioItau(boolean convenioItau) {
        this.convenioItau = convenioItau;
    }

    public boolean isConvenioBradesco() {
        return convenioBradesco;
    }

    public void setConvenioBradesco(boolean convenioBradesco) {
        this.convenioBradesco = convenioBradesco;
    }

    public boolean isConvenioOutro() {
        return convenioOutro;
    }

    public void setConvenioOutro(boolean convenioOutro) {
        this.convenioOutro = convenioOutro;
    }

    public boolean isConvenioSantander() {
        return convenioSantander;
    }

    public void setConvenioSantander(boolean convenioSantander) {
        this.convenioSantander = convenioSantander;
    }

    public boolean isConvenioClinipam() {
        return convenioClinipam;
    }

    public void setConvenioClinipam(boolean convenioClinipam) {
        this.convenioClinipam = convenioClinipam;
    }

    public boolean convenioSantander;

    public boolean convenioClinipam;

    public Medico(int id, String nome,  String endereco, String especialidade, boolean convenioBradesco, boolean convenioCassi,
                  boolean convenioClinipam, boolean convenioItau, boolean convenioOutro,
                  boolean convenioSantander, boolean convenioUnimed, boolean sexoFeminino,
                  boolean sexoMasculino) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.convenioBradesco = convenioBradesco;
        this.convenioCassi = convenioCassi;
        this.convenioClinipam = convenioClinipam;
        this.convenioItau = convenioItau;
        this.convenioOutro = convenioOutro;
        this.convenioSantander = convenioSantander;
        this.convenioUnimed = convenioUnimed;
        this.sexoFeminino = sexoFeminino;
        this.sexoMasculino = sexoMasculino;
        this.endereco = endereco;


    }

    @Override
    public String toString() {
        return "Medico{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", sexoMasculino=" + sexoMasculino +
                ", sexoFeminino=" + sexoFeminino +
                ", convenioUnimed=" + convenioUnimed +
                ", convenioCassi=" + convenioCassi +
                ", convenioItau=" + convenioItau +
                ", convenioBradesco=" + convenioBradesco +
                ", convenioOutro=" + convenioOutro +
                ", convenioSantander=" + convenioSantander +
                ", convenioClinipam=" + convenioClinipam +
                '}';
    }
}
