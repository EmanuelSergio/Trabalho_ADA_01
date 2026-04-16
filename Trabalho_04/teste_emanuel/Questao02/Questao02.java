package Trabalho_04.Questao02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Questao02 {

    private static final int LIST_SIZE = 10_000;
    private static final int NUM_TESTS = 20;

    public static void main(String[] args) {
        System.out.println("=== Questão 2: Inserção em Posição Aleatória ===");
        System.out.println("Elementos por teste : " + LIST_SIZE);
        System.out.println("Número de testes    : " + NUM_TESTS);
        System.out.println();

        long[] alTimes = new long[NUM_TESTS];
        long[] llTimes = new long[NUM_TESTS];

        for (int i = 0; i < NUM_TESTS; i++) {
            // Gera posições aleatórias válidas para cada inserção.
            // Na j-ésima inserção (0-based), a lista já tem j elementos,
            // então a posição válida está em [0, j].
            Random rand = new Random(i);
            int[] pos = new int[LIST_SIZE];
            for (int j = 0; j < LIST_SIZE; j++) {
                pos[j] = (j == 0) ? 0 : rand.nextInt(j + 1);
            }

            // ── ArrayList ────────────────────────────────────────────────
            ArrayList<Integer> al = new ArrayList<>();
            long start = System.nanoTime();
            for (int j = 0; j < LIST_SIZE; j++) al.add(pos[j], j);
            alTimes[i] = System.nanoTime() - start;

            // ── LinkedList ───────────────────────────────────────────────
            LinkedList<Integer> ll = new LinkedList<>();
            start = System.nanoTime();
            for (int j = 0; j < LIST_SIZE; j++) ll.add(pos[j], j);
            llTimes[i] = System.nanoTime() - start;
        }

        double avgAL = avg(alTimes);
        double avgLL = avg(llTimes);

        System.out.printf("ArrayList  - Tempo médio: %8.3f ms%n", ms(avgAL));
        System.out.printf("LinkedList - Tempo médio: %8.3f ms%n", ms(avgLL));

        if (avgAL <= avgLL) {
            System.out.printf("→ ArrayList foi %.2fx mais rápida que LinkedList%n", avgLL / avgAL);
        } else {
            System.out.printf("→ LinkedList foi %.2fx mais rápida que ArrayList%n", avgAL / avgLL);
        }
    }

    private static double avg(long[] t) {
        long sum = 0;
        for (long v : t) sum += v;
        return (double) sum / t.length;
    }

    private static double ms(double nanos) { return nanos / 1_000_000.0; }
}
