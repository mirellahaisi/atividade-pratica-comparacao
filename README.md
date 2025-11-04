# 🚀 Comparação de Desempenho entre Modelos de Threads N:M e 1:1

Este documento apresenta a análise comparativa de desempenho do tempo de execução entre dois modelos fundamentais de threads: **N:M** (User-Level com Pool Fixo) e **1:1** (Kernel-Level).

## 🎯 Objetivo

Comparar a performance de tempo de execução entre o **Modelo 1:1** e o **Modelo N:M (Pool Fixo $M=10$)**, analisando o impacto da limitação de threads do sistema.

## ⚙️ Metodologia

Ambos os programas Java executaram a mesma tarefa, que consiste em um cálculo simples seguido de um tempo de bloqueio (`Thread.sleep(10)`). Os testes foram realizados com diferentes quantidades de tarefas ($N$): **10, 100, 500 e 1000**.

## 📊 Resultados

Os tempos de execução foram medidos em milissegundos (ms):

| N (Tarefas/Threads) | Modelo N:M (M=10) | Modelo 1:1 |
| :-----------------: | :---------------: | :--------: |
| 10                  | 34 ms             | 17 ms      |
| 100                 | 165 ms            | 38 ms      |
| 500                 | 795 ms            | 86 ms      |
| 1000                | 1586 ms           | 149 ms     |

### Gráfico Comparativo

O gráfico abaixo ilustra a diferença crescente nos tempos de execução entre os dois modelos:

![Gráfico comparação Modelo N:M e 1:1]

<img width="886" height="527" alt="image" src="https://github.com/user-attachments/assets/d4ec4177-1a62-495d-be9c-59614b1fa16e" />


## 📈 Análise e Conclusões

### 1. Identificação do Ponto de Vantagem (Modelo 1:1)

* O **Modelo 1:1** foi mais vantajoso em desempenho em todos os cenários de teste.
* O ponto em que o 1:1 se torna mais vantajoso é **imediato** ($N=10$).
* O modelo 1:1 obteve seu maior ganho em $N=1000$, sendo aproximadamente 10 vezes mais rápido (149 ms vs. 1586 ms).

### 2. Impacto da Limitação de Threads (Modelo N:M)

* O **Modelo N:M** é limitado pelo pool fixo de **$M=10$ threads reais**.
* Como as tarefas possuem um tempo de bloqueio (`Thread.sleep(10)`), a limitação de $M=10$ faz com que as tarefas em espera sejam **serializadas** pelas threads disponíveis, elevando o tempo de execução total de forma acentuada (atingindo 1586 ms).
* O N:M é mais lento, mas oferece **maior controle sobre o uso de recursos**.

### 3. Conclusão Final

* O **Modelo 1:1** é superior para esta carga de trabalho com bloqueio (I/O-Bound), devido ao seu **máximo paralelismo**.
* O **Modelo N:M** é ideal para ambientes com **restrições rigorosas de recursos** ou onde é necessário um alto controle sobre a criação de threads do sistema.
