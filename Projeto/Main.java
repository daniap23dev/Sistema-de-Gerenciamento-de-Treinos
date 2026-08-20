package Projeto;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

    private static final String ARQUIVO_DADOS = "dados.ser";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        Dados dados = carregarDados();
        Usuario usuario = dados.getUsuario();
        ArrayList<Treino> treinos = dados.getTreinos();


        while (rodando) {

            System.out.println("\nSISTEMA DE GERENCIAMENTO DE TREINOS:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Criar treino");
            System.out.println("3 - Adicionar exercício a um treino");
            System.out.println("4 - Vincular treino a um dia da semana");
            System.out.println("5 - Exibir cronograma");
            System.out.println("6 - Exportar o treino para arquivo CSV");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            int opcao = sc.nextInt();
            sc.nextLine();

            if(opcao == 0){

                rodando = false;
                salvarDados(usuario, treinos);

                System.out.println("Dados salvos. Até a próxima!");
                continue;
            }

            switch (opcao) {

                case 1:

                    System.out.print("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite sua idade: ");
                    int idade = sc.nextInt();

                    System.out.print("Digite seu peso: ");
                    double peso = sc.nextDouble();

                    System.out.print("Digite sua altura: ");
                    double altura = sc.nextDouble();

                    sc.nextLine();

                    System.out.print("Digite seu objetivo: ");
                    String objetivo = sc.nextLine();
                    
                    usuario = new Usuario(nome, peso, altura, idade, objetivo);

                    System.out.println("\nUsuário Cadastrado com Sucesso.");
                    salvarDados(usuario, treinos);

                    break;

                case 2:

                    System.out.print("Nome da divisão (ex: Peito/Tríceps/Ombro): ");
                    String nomeDivisao = sc.nextLine();

                    Treino novoTreino = new Treino(nomeDivisao);
                    treinos.add(novoTreino);

                    System.out.println("Treino criado com sucesso!");
                    salvarDados(usuario, treinos);

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

                    System.out.print("1 - Força  |  2 - Cardio :");
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
                            salvarDados(usuario, treinos);

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

                     if (usuario == null) {

                        System.out.println("Cadastre um usuário primeiro.");
                        break;

                    }
                    if (treinos.isEmpty()) {

                        System.out.println("Crie um treino primeiro.");
                        break;

                    }

                    for (int i = 0; i < treinos.size(); i++) {

                        System.out.println(i + " - " + treinos.get(i).getNomeDivisao());

                    }

                    System.out.print("Escolha o treino: ");
                    int idxVincular = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Dias disponíveis: ");
                    DiaSemana[] dias = DiaSemana.values();

                    for (int i = 0; i < dias.length; i++) {

                        System.out.println(i + " - " + dias[i]);

                    }

                    System.out.print("Escolha o dia: ");
                    int idxDia = sc.nextInt();
                    sc.nextLine();

                    try {

                        usuario.vincularTreino(dias[idxDia], treinos.get(idxVincular));
                        System.out.println("Treino vinculado com sucesso!");
                        salvarDados(usuario, treinos);

                    } catch (TreinoRepetidoException e) {

                        System.out.println("Erro: " + e.getMessage());

                    }
                    break;
                
                case 5:

                    if (usuario == null) {

                        System.out.println("Cadastre um usuário primeiro.");
                        break;
                    }

                    usuario.exibirCronograma();
                    break;

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

                    treinos.get(idxCsv).exportarParaCSV(nomeCsv, usuario);
                    break;

                default:
                    
            }

        }


        sc.close();
    }

    private static void salvarDados(Usuario usuario, ArrayList<Treino> treinos) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {

            out.writeObject(new Dados(usuario, treinos));

        } 
        catch (IOException e) {

            System.out.println("Erro ao salvar dados: " + e.getMessage());
            
        }
    }

    private static Dados carregarDados() {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARQUIVO_DADOS))) {

            return (Dados) in.readObject();

        } catch (EOFException | java.io.FileNotFoundException e) {
            
            return new Dados(null, new ArrayList<>());

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Não foi possível carregar dados salvos, começando do zero: " + e.getMessage());
            return new Dados(null, new ArrayList<>());

        }
    }

}