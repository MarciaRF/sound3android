package pt.ipleiria.estg.dei.amsi.sound3application.Listeners;

import java.util.ArrayList;

import models.Comentario;
import models.Utilizador;

public interface CommentListener {
    void onResfreshComment(ArrayList<Comentario> listaComentarios, ArrayList<Utilizador> listaComentariosUser);
    void onResfreshNovoComment(String checkNovoComment);
}
