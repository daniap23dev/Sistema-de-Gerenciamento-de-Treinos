package Projeto;
import java.util.ArrayList;

public class Treino {
    
    private String nomeDivisao;
    private ArrayList<Exercicio> exercicios;

    public Treino(String nomeDivisao){

        this.nomeDivisao = nomeDivisao;

    }

    public void adicionarExercicio(Exercicio ex){


    }
    
    public void exibirTreino(){

        System.out.println("só para não ficar vazia"); //alterar, só coloquei pra poder fazer o usuario funcionar 

    }

    public String getNomeDivisao(){

        return nomeDivisao;
    }

}
