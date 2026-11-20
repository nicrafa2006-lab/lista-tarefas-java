import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final List<Tarefa> tarefas = new ArrayList<>();
    private static int proximoId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1 -> adicionarTarefa(scanner);
                case 2 -> listarTarefas();
                case 3 -> concluirTarefa(scanner);
                case 4 -> removerTarefa(scanner);
                case 0 -> System.out.println("Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== LISTA DE TAREFAS ===");
        System.out.println("1 - Adicionar tarefa");
        System.out.println("2 - Listar tarefas");
        System.out.println("3 - Concluir tarefa");
        System.out.println("4 - Remover tarefa");
        System.out.println("0 - Sair");
    }

    private static void adicionarTarefa(Scanner scanner) {
        System.out.print("Descrição da tarefa: ");
        String descricao = scanner.nextLine().trim();

        if (descricao.isEmpty()) {
            System.out.println("A descrição não pode ficar vazia.");
            return;
        }

        tarefas.add(new Tarefa(proximoId++, descricao));
        System.out.println("Tarefa adicionada!");
    }

    private static void listarTarefas() {
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        tarefas.forEach(System.out::println);
    }

    private static void concluirTarefa(Scanner scanner) {
        int id = lerInteiro(scanner, "ID da tarefa a concluir: ");
        Tarefa tarefa = buscarPorId(id);

        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
        } else {
            tarefa.concluir();
            System.out.println("Tarefa concluída!");
        }
    }

    private static void removerTarefa(Scanner scanner) {
        int id = lerInteiro(scanner, "ID da tarefa a remover: ");
        Tarefa tarefa = buscarPorId(id);

        if (tarefa == null) {
            System.out.println("Tarefa não encontrada.");
        } else {
            tarefas.remove(tarefa);
            System.out.println("Tarefa removida!");
        }
    }

    private static Tarefa buscarPorId(int id) {
        for (Tarefa t : tarefas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
