package models;

import java.io.Serializable;

public class Utilizador implements Serializable {

    private long IdUtilizador;
    private String NomeUtilizador;
    private String PasswordUtilizador;
    private String EmailUtilizador;


    public Utilizador(long idUtilizador, String nomeUtilizador, String passwordUtilizador, String emailUtilizador){
        IdUtilizador = idUtilizador;
        NomeUtilizador = nomeUtilizador;
        PasswordUtilizador = passwordUtilizador;
        EmailUtilizador = passwordUtilizador;
    }


    public long getIdUtilizador() { return IdUtilizador; }

    public void setIdUtilizador(long idUtilizador) {
        IdUtilizador = idUtilizador;
    }

    public String getNomeUtilizador() {
        return NomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        NomeUtilizador = nomeUtilizador;
    }

    public String getPasswordUtilizador() {
        return PasswordUtilizador;
    }

    public void setPasswordUtilizador(String passwordUtilizador) { PasswordUtilizador = passwordUtilizador; }

    public String getEmailUtilizador() {
        return EmailUtilizador;
    }

    public void setEmailUtilizador(String emailUtilizador) {
        EmailUtilizador = emailUtilizador;
    }
}
