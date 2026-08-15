package Projeto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Treino {
    
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

    public void exportarParaCSV(String caminhoArquivo){
        
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))){

            writer.write("Divisao,Exercicio,Series,Tipo,Detalhe");
            writer.newLine();

            for (Exercicio ex : exercicios) {
                String linha;
                if (ex instanceof ExercicioForca) {
                    ExercicioForca ef = (ExercicioForca) ex;
                    linha = nomeDivisao + "," + ef.getNome() + "," + ef.getSeries() + ",Forca," + ef.getCargaKg() + "kg x " + ef.getRepeticoes() + "reps";
                } else {
                    ExercicioCardio ec = (ExercicioCardio) ex;
                    linha = nomeDivisao + "," + ec.getNome() + "," + ec.getSeries() + ",Cardio," + ec.getTempoMinutos() + "min";
                }
                writer.write(linha);
                writer.newLine();           
            }


        }catch (IOException e) {
        System.out.println("Erro ao salvar CSV: " + e.getMessage());
        }
    }
}