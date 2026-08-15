package Projeto;

public class ExercicioForca extends Exercicio {
    
    private double cargaKg;
    private int repeticoes;

    private static final long serialVersionUID = 1L;

    public ExercicioForca(String nome, int series, int repeticoes, double cargaKg) throws CargaInvalidaException{

        super(nome, series);
        this.cargaKg = cargaKg;
        this.repeticoes = repeticoes;
    }

    public void exibirDetalhes(){

        System.out.println("  - " + nome + " | " + series + " séries | Força | " + cargaKg + "kg x " + repeticoes + " reps");
    
    }

    public double getCargaKg(){

        return cargaKg;
    }

    public int getRepeticoes(){

        return repeticoes;
    }
}
