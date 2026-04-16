package Trabalho_04.Questao04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Questao04 {

    private static final int LIST_SIZE = 10_000;
    private static final int NUM_TESTS = 20;

    public static void main(String[] args) {
        System.out.println("=== Questão 4: Remoção em Índices Aleatórios ===");
        System.out.println("Elementos por lista : " + LIST_SIZE);
        System.out.println("Número de testes    : " + NUM_TESTS);
        System.out.println();

        long[] alTimes = new long[NUM_TESTS];
        long[] llTimes = new long[NUM_TESTS];

        for (int i = 0; i < NUM_TESTS; i++) {
            // ── ArrayList ────────────────────────────────────────────────
            Random rand = new Random(i);
            ArrayList<Integer> al = fill(new ArrayList<>(LIST_SIZE));
            long start = System.nanoTime();
            while (!al.isEmpty()) al.remove(rand.nextInt(al.size()));
            alTimes[i] = System.nanoTime() - start;

            // Mesma semente: mesma sequência de índices para comparação justa
            rand = new Random(i);
            LinkedList<Integer> ll = fill(new LinkedList<>());
            start = System.nanoTime();
            while (!ll.isEmpty()) ll.remove(rand.nextInt(ll.size()));
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

    private static <L extends java.util.List<Integer>> L fill(L list) {
        for (int i = 0; i < LIST_SIZE; i++) list.add(i);
        return list;
    }

    private static double avg(long[] t) {
        long sum = 0;
        for (long v : t) sum += v;
        return (double) sum / t.length;
    }

    private static double ms(double nanos) { return nanos / 1_000_000.0; }
}
