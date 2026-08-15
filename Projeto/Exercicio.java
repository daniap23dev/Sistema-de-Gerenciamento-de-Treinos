package Projeto;

public abstract class Exercicio {
    
    protected String nome;
    protected int series;

    public Exercicio(String nome, int series){

    this.nome = nome;
    this.series = series;
    
    }

    public abstract void exibirDetalhes();

    public String getNome(){

        return nome;
        
    }

    public int getSeries(){
        
        return series;
        
    }
}

