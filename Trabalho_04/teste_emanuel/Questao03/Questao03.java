package Trabalho_04.Questao03;

import java.util.ArrayList;
import java.util.LinkedList;

public class Questao03 {

    private static final int LIST_SIZE = 10_000;
    private static final int NUM_TESTS = 20;

    public static void main(String[] args) {
        System.out.println("=== Questão 3: Remoção de Todos os Elementos ===");
        System.out.println("Elementos por lista : " + LIST_SIZE);
        System.out.println("Número de testes    : " + NUM_TESTS);
        System.out.println();

        // ── Parte 1: Remover sempre o primeiro elemento ───────────────────
        System.out.println("--- Parte 1: Remover sempre o primeiro elemento ---");

        long[] alTimes1 = new long[NUM_TESTS];
        long[] llTimes1 = new long[NUM_TESTS];

        for (int i = 0; i < NUM_TESTS; i++) {
            ArrayList<Integer> al = fill(new ArrayList<>(LIST_SIZE));
            long start = System.nanoTime();
            while (!al.isEmpty()) al.remove(0);
            alTimes1[i] = System.nanoTime() - start;

            LinkedList<Integer> ll = fill(new LinkedList<>());
            start = System.nanoTime();
            while (!ll.isEmpty()) ll.removeFirst();
            llTimes1[i] = System.nanoTime() - start;
        }

        printResult(alTimes1, llTimes1);

        // ── Parte 2: Remover sempre o último elemento ─────────────────────
        System.out.println("--- Parte 2: Remover sempre o último elemento ---");

        long[] alTimes2 = new long[NUM_TESTS];
        long[] llTimes2 = new long[NUM_TESTS];

        for (int i = 0; i < NUM_TESTS; i++) {
            ArrayList<Integer> al = fill(new ArrayList<>(LIST_SIZE));
            long start = System.nanoTime();
            while (!al.isEmpty()) al.remove(al.size() - 1);
            alTimes2[i] = System.nanoTime() - start;

            LinkedList<Integer> ll = fill(new LinkedList<>());
            start = System.nanoTime();
            while (!ll.isEmpty()) ll.removeLast();
            llTimes2[i] = System.nanoTime() - start;
        }

        printResult(alTimes2, llTimes2);
    }

    // ── Utilitários ───────────────────────────────────────────────────────────

    private static <L extends java.util.List<Integer>> L fill(L list) {
        for (int i = 0; i < LIST_SIZE; i++) list.add(i);
        return list;
    }

    private static void printResult(long[] alTimes, long[] llTimes) {
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
    }

    private static double avg(long[] t) {
        long sum = 0;
        for (long v : t) sum += v;
        return (double) sum / t.length;
    }

    private static double ms(double nanos) { return nanos / 1_000_000.0; }
}
