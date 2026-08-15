package Projeto;

public class ExercicioForca extends Exercicio {
    
    private double cargaKg;
    private int repeticoes;

    public ExercicioForca(String nome, int series, int repeticoes, double cargaKg) throws CargaInvalidaException{

        super(nome, series);
        this.cargaKg = cargaKg;
        this.repeticoes = repeticoes;
    }

    public void exibirDetalhes(){

    
    }

    public double getCargaKg(){

        return cargaKg;
    }

    public int getRepeticoes(){

        return repeticoes;
    }
}
