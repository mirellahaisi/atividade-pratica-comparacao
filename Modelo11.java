public class Modelo11 {
    public static void main(String[] args) throws InterruptedException {
        // Quantidades de tarefas/threads a serem testadas
        int[] quantidades = {10, 100, 500, 1000};

        // Loop para testar o desempenho com diferentes quantidades de threads
        for (int n : quantidades) {
            // Cria um espaço para cada tarefa
            Thread[] threads = new Thread[n];

            // Tempo inicial antes de iniciar as threads
            long inicio = System.currentTimeMillis();

            // Cria e inicia N threads, cada uma executando o método tarefa()
            for (int i = 0; i < n; i++) {
                threads[i] = new Thread(() -> tarefa()); // cria a thread
                threads[i].start(); // inicia a thread
            }

            // Aguarda todas as threads terminarem
            // O método join() faz a thread principal esperar cada thread terminar sua execução
            for (int i = 0; i < n; i++) {
                threads[i].join();
            }

            // Tempo final após todas as threads terminarem
            long fim = System.currentTimeMillis();

            // Exibe no console o número de threads e o tempo total gasto
            System.out.println("N=" + n + " | Modelo 1:1 | Tempo total: " + (fim - inicio) + " ms");
        }
    }

    // Método que representa a tarefa que cada thread executa
    static void tarefa() {
        long soma = 0;

        // Faz uma soma simples para simular uma operação de CPU
        for (int i = 0; i < 10000; i++) {
            soma += i;
        }

        try {
            // Pausa a execução da thread por 10 milissegundos
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // Caso a thread seja interrompida, restaura o estado de interrupção
            Thread.currentThread().interrupt();
        }
    }
}

