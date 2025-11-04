import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ModeloNM {
    public static void main(String[] args) throws InterruptedException {
        // Array com as quantidades de tarefas N que serão testadas
        int[] quantidades = {10, 100, 500, 1000};

        // Número fixo de threads que o sistema pode executar ao mesmo tempo M
        int m = 10;

        // Loop para testar o programa com diferentes quantidades de tarefas
        for (int n : quantidades) {
            // Tempo inicial antes de começar as tarefas
            long inicio = System.currentTimeMillis();

            // Cria um pool fixo com tamanho M 10 threads
            ExecutorService pool = Executors.newFixedThreadPool(m);

            // Envia N tarefas para o pool
            for (int i = 0; i < n; i++) {
                pool.submit(() -> {
                    tarefa();
                });
            }

            // Indica que não serão adicionadas novas tarefas
            pool.shutdown();

            // Aguarda até que todas as tarefas terminem
            pool.awaitTermination(10, TimeUnit.MINUTES);

            // Tempo final após todas as tarefas terminarem
            long fim = System.currentTimeMillis();

            // Mostra no console o número de tarefas, número de threads e o tempo total gasto
            System.out.println("N=" + n + " | M=" + m + " | Tempo total: " + (fim - inicio) + " ms");
        }
    }

    // Representa uma tarefa a ser executada pelas threads
    static void tarefa() {
        long soma = 0;

        // Faz uma soma simples
        for (int i = 0; i < 10000; i++) {
            soma += i;
        }

        try {
            // Pausa a execução por 10 milissegundos
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // Se a thread for interrompida, restaura o estado de interrupção
            Thread.currentThread().interrupt();
        }
    }
}
