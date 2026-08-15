package Projeto;

public class ExercicioCardio extends Exercicio {

    private int tempoMinutos;

    private static final long serialVersionUID = 1L;

    public ExercicioCardio(String nome, int series, int tempoMinutos) throws TempoInvalidoException{

        super(nome, series);
        this.tempoMinutos = tempoMinutos;

    }

    public void exibirDetalhes(){

        System.out.println("  - " + nome + " | " + series + " séries | Cardio | " + tempoMinutos + " min");

    }

    public int getTempoMinutos() {

        return tempoMinutos;
        
    }
    
}
