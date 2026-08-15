package Projeto;
import java.io.Serializable;
import java.util.ArrayList;
 
public class Dados implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    private Usuario usuario;
    private ArrayList<Treino> treinos;
 
    public Dados(Usuario usuario, ArrayList<Treino> treinos) {

        this.usuario = usuario;
        this.treinos = treinos;
        
    }
 
    public Usuario getUsuario() {

        return usuario;

    }
 
    public ArrayList<Treino> getTreinos() {

        return treinos;

    }
}
 