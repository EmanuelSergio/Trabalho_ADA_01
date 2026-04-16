package Trabalho_04.Questao05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Questao05 {

    private static final int LIST_SIZE   = 100_000;
    private static final int NUM_TESTS   = 20;
    private static final int NUM_ACCESSES = 10_000;

    public static void main(String[] args) {
        System.out.println("=== Questão 5: Acesso a Índices Aleatórios ===");
        System.out.println("Elementos na lista  : " + LIST_SIZE);
        System.out.println("Acessos por teste   : " + NUM_ACCESSES);
        System.out.println("Número de testes    : " + NUM_TESTS);
        System.out.println();

        // Listas já preenchidas – não fazem parte do tempo medido
        ArrayList<Integer> al = new ArrayList<>(LIST_SIZE);
        LinkedList<Integer> ll = new LinkedList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            al.add(i);
            ll.add(i);
        }

        long[] alTimes = new long[NUM_TESTS];
        long[] llTimes = new long[NUM_TESTS];

        for (int i = 0; i < NUM_TESTS; i++) {
            Random rand = new Random(i);
            int[] indices = new int[NUM_ACCESSES];
            for (int j = 0; j < NUM_ACCESSES; j++) indices[j] = rand.nextInt(LIST_SIZE);

            // Acumula em 'sink' para impedir que o JIT elimine os acessos
            // como código morto (dead-code elimination).
            int sink = 0;

            // ── ArrayList ────────────────────────────────────────────────
            long start = System.nanoTime();
            for (int idx : indices) sink ^= al.get(idx);
            alTimes[i] = System.nanoTime() - start;

            // ── LinkedList ───────────────────────────────────────────────
            start = System.nanoTime();
            for (int idx : indices) sink ^= ll.get(idx);
            llTimes[i] = System.nanoTime() - start;

            // Evita que o compilador elimine 'sink' (nunca impresso em condição normal)
            if (sink == Integer.MIN_VALUE) System.out.println(sink);
        }

        double avgAL = avg(alTimes);
        double avgLL = avg(llTimes);

        System.out.printf("ArrayList  - Tempo médio: %8.3f ms%n", ms(avgAL));
        System.out.printf("LinkedList - Tempo médio: %8.3f ms%n", ms(avgLL));

        if (avgAL <= avgLL) {
            System.out.printf("→ ArrayList foi %.2fx mais rápida que LinkedList%n%n",
                    avgLL / avgAL);
        } else {
            System.out.printf("→ LinkedList foi %.2fx mais rápida que ArrayList%n%n",
                    avgAL / avgLL);
        }

        System.out.println("Explicação:");
        System.out.println("  ArrayList armazena os elementos em um array contíguo. O acesso");
        System.out.println("  por índice é calculado diretamente (base + índice × tamanho),");
        System.out.println("  resultando em O(1) e excelente aproveitamento de cache de CPU.");
        System.out.println();
        System.out.println("  LinkedList armazena cada elemento em um nó separado na memória.");
        System.out.println("  Para get(i), é preciso percorrer a lista desde a cabeça (ou");
        System.out.println("  cauda, se i > size/2), resultando em O(n) por acesso. Com");
        System.out.println("  índices aleatórios, a diferença de desempenho é drástica.");
    }

    private static double avg(long[] t) {
        long sum = 0;
        for (long v : t) sum += v;
        return (double) sum / t.length;
    }

    private static double ms(double nanos) { return nanos / 1_000_000.0; }
}
