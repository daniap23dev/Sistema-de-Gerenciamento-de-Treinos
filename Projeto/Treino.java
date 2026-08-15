package Projeto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Treino implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nomeDivisao;
    private ArrayList<Exercicio> exercicios;

    public Treino(String nomeDivisao){

        this.nomeDivisao = nomeDivisao;
        this.exercicios = new ArrayList<>();

    }

    public void adicionarExercicio(Exercicio ex){

        exercicios.add(ex);

    }
    
    public void exibirTreino(){

        System.out.println("Treino: " + nomeDivisao);
        for(Exercicio ex : exercicios){
            ex.exibirDetalhes();
        }

    }

    public String getNomeDivisao(){

        return nomeDivisao;
    }

    public void exportarParaCSV(String caminhoArquivo, Usuario usuario){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))){

            if (usuario != null) {
                writer.write("Nome | Peso(kg) | Altura(m) | Idade | Objetivo");

                writer.newLine();
                writer.write(usuario.getNome() + " |" + usuario.getPeso() + " |" + usuario.getAltura() + " |" + usuario.getIdade() + " |" + usuario.getObjetivo());
                writer.newLine();
                writer.newLine();
            }

            writer.write("Divisao | Exercicio | Series | Tipo | Detalhe");
            writer.newLine();

            for (Exercicio ex : exercicios) {
                String linha;
                if (ex instanceof ExercicioForca) {
                    ExercicioForca ef = (ExercicioForca) ex;
                    linha = nomeDivisao + " |" + ef.getNome() + " |" + ef.getSeries() + "| Forca |" + ef.getCargaKg() + " kg x " + ef.getRepeticoes() + "reps";
                } else {
                    ExercicioCardio ec = (ExercicioCardio) ex;
                    linha = nomeDivisao + " |" + ec.getNome() + " |" + ec.getSeries() + "| Cardio|" + ec.getTempoMinutos() + " min";
                }
                writer.write(linha);
                writer.newLine();           
            }


        }catch (IOException e) {
        System.out.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }
}