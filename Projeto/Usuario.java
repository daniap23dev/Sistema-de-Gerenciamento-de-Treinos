package Projeto;
import java.io.Serializable;
import java.util.HashMap;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private double peso;
    private double altura;
    private int idade;
    private String objetivo;
    private HashMap<DiaSemana, Treino> calendarioSemanal;

    public Usuario(String nome, double peso, double altura, int idade, String objetivo){

        this.nome = nome;
        this.altura = altura;
        this.idade = idade;
        this.peso = peso;
        this.objetivo = objetivo;
        this.calendarioSemanal = new HashMap<>();

    }

    public void vincularTreino(DiaSemana dia, Treino treino) throws TreinoRepetidoException{

        DiaSemana anterior = diaAnterior(dia);
        DiaSemana proximo = diaSeguinte(dia);

        Treino treinoAnterior = calendarioSemanal.get(anterior);
        Treino treinoProximo = calendarioSemanal.get(proximo);

        if ((treinoAnterior != null && treinoAnterior.getNomeDivisao().equals(treino.getNomeDivisao())) || (treinoProximo != null && treinoProximo.getNomeDivisao().equals(treino.getNomeDivisao()))){

            throw new TreinoRepetidoException("O treino '" + treino.getNomeDivisao() + "' já está agendado em um dia adjacente a " + dia);

        }
        calendarioSemanal.put(dia, treino);
    }

    public void exibirCronograma(){
        System.out.println("Cronograma semanal de " + nome + ":");

        for (DiaSemana dia : DiaSemana.values()) {
            Treino treino = calendarioSemanal.get(dia);
            System.out.println("\n" + dia + ":");

                if (treino != null) {

                    treino.exibirTreino();

                } else {

                    System.out.println("  Descanso");
                    
                }
    }
    }

    public String getNome() {

        return nome;

    }

    public double getPeso() {

        return peso;

    }

    public double getAltura() {

        return altura;

    }

    public int getIdade() {

        return idade;

    }

    public String getObjetivo() {

        return objetivo;

    }

    public HashMap<DiaSemana, Treino> getCalendarioSemanal(){

        return calendarioSemanal;

    } 

    private DiaSemana diaAnterior(DiaSemana dia) {

        DiaSemana[] dias = DiaSemana.values();
        int i = dia.ordinal();
        return dias[(i - 1 + dias.length) % dias.length];

    }

    private DiaSemana diaSeguinte(DiaSemana dia) {

        DiaSemana[] dias = DiaSemana.values();
        int i = dia.ordinal();
        return dias[(i + 1) % dias.length];

    }







}