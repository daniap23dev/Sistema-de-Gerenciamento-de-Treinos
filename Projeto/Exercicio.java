package Projeto;

import java.io.Serializable;

public abstract class Exercicio implements Serializable{
    
    protected String nome;
    protected int series;

    private static final long serialVersionUID = 1L;

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

