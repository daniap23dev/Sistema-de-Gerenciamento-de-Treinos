package Projeto;

public class ExercicioCardio extends Exercicio {

    private int tempoMinutos;

    public ExercicioCardio(String nome, int series, int tempoMinutos) throws TempoInvalidoException{

        super(nome, series);
        this.tempoMinutos = tempoMinutos;

    }

    public void exibirDetalhes(){



    }

    public int getTempoMinutos() {

        return tempoMinutos;
        
    }
    
}
