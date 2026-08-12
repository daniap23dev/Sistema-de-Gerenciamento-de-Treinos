package Projeto;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Usuario usuario = null;
        boolean rodando = true;

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
                    break;
            
                default:
                    
            }

        }


        sc.close();
    }
}