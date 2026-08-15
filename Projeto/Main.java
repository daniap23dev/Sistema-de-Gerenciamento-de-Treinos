package Projeto;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Usuario usuario = null;
        boolean rodando = true;
        ArrayList<Treino> treinos = new ArrayList<>();

        while (rodando) {

            System.out.println("\nSISTEMA DE GERENCIAMENTO DE TREINOS:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Criar treino");
            System.out.println("3 - Adicionar exercício a um treino");
            System.out.println("4 - Vincular treino a um dia da semana");
            System.out.println("5 - Exibir cronograma");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:

                    System.out.println("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite sua idade: ");
                    int idade = sc.nextInt();

                    System.out.println("Digite seu peso: ");
                    double peso = sc.nextDouble();

                    System.out.println("Digite sua altura: ");
                    double altura = sc.nextDouble();

                    sc.nextLine();

                    System.out.println("Digite seu objetivo: ");
                    String objetivo = sc.nextLine();
                    
                    usuario = new Usuario(nome, peso, altura, idade, objetivo);

                    System.out.println("\nUsuário Cadastrado com Sucesso.");
                    break;

                case 2:

                    System.out.print("Nome da divisão (ex: Peito/Tríceps/Ombro): ");
                    String nomeDivisao = sc.nextLine();

                    Treino novoTreino = new Treino(nomeDivisao);
                    treinos.add(novoTreino);

                    System.out.println("Treino criado com sucesso!");
                    break;

                case 3:

                    if (treinos.isEmpty()) {
                    System.out.println("Crie um treino primeiro.");
                    break;
                    }

                    for (int i = 0; i < treinos.size(); i++){
                        System.out.println(i + " - " + treinos.get(i).getNomeDivisao());
                    }

                    System.out.print("Escolha o treino: ");
                    int idxTreino = sc.nextInt();
                    sc.nextLine();
                    Treino treinoEscolhido = treinos.get(idxTreino);

                    System.out.println("1 - Força  |  2 - Cardio");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome do exercício: ");
                    String nomeEx = sc.nextLine();
                    System.out.print("Número de séries: ");
                    int series = sc.nextInt();
                    sc.nextLine();

                    if (tipo == 1){

                        System.out.print("Repetições: ");
                        int reps = sc.nextInt();

                        System.out.print("Carga (kg): ");
                        double carga = sc.nextDouble();
                        sc.nextLine();

                        try{

                            treinoEscolhido.adicionarExercicio(new ExercicioForca(nomeEx, series, reps, carga));
                            System.out.println("Exercício adicionado!");

                        }
                        catch(CargaInvalidaException e){

                            System.out.println("Erro: " + e.getMessage());

                        }

                    }
                    else {
                        System.out.print("Tempo (minutos): ");
                        int tempo = sc.nextInt();
                        sc.nextLine();
                        try{
                            treinoEscolhido.adicionarExercicio(new ExercicioCardio(nomeEx, series, tempo));
                            System.out.println("Exercício adicionado!");
                        }
                        catch(TempoInvalidoException t){

                            System.out.println("Erro: " + t.getMessage());

                        }
                    }
                    break;
                    
                case 4:
                
                case 5:

                    usuario.exibirCronograma();

                case 6:

                    if (treinos.isEmpty()) {
                        System.out.println("Nenhum treino criado ainda.");
                        break;
                    }   

                    for (int i = 0; i < treinos.size(); i++) {
                        System.out.println(i + " - " + treinos.get(i).getNomeDivisao());
                    }

                    System.out.print("Escolha o treino para exportar: ");
                    int idxCsv = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nome do arquivo (ex: treino.csv): ");
                    String nomeCsv = sc.nextLine();

                    treinos.get(idxCsv).exportarParaCSV(nomeCsv);
                    break;

                default:
                    
            }

        }


        sc.close();
    }
}