package Trabalho_04.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MedidorPerformanceListas {

    private final Random random = new Random();

    private List listaEncadeada;
    private List listaVetorial;

    public void executarTestes() {

        int quantidadeExecucoes = 10_000;
        int quantidadeRepeticoes = 20;
        int quantidadeRepeticoesCapacidadeInicial = 10;

        System.out.println("QUESTÃO 1");
        calcularTempoMedioInsercoes(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();
        calcularTempoMedioInsercoes(quantidadeExecucoes, quantidadeRepeticoesCapacidadeInicial, 10);
        System.out.println();
        calcularTempoMedioInsercoes(quantidadeExecucoes, quantidadeRepeticoesCapacidadeInicial, 1000);
        System.out.println();
        calcularTempoMedioInsercoes(quantidadeExecucoes, quantidadeRepeticoesCapacidadeInicial, 100_000);
        System.out.println();

        System.out.println("QUESTÃO 2 ");
        calcularTempoMedioInsercoesAleatorias(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();

        System.out.println("QUESTÃO 3.1");
        calcularTempoMedioRemocaoInicio(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();

        System.out.println("QUESTÃO 3.2");
        calcularTempoMedioRemocaoFinal(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();

        System.out.println("QUESTÃO 4");
        calcularTempoMedioRemocaoAleatoria(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();

        System.out.println("QUESTÃO 5");
        calcularTempoMedioAcessoAleatorio(quantidadeExecucoes, quantidadeRepeticoes);
        System.out.println();
    }

    // Questao 1 =======================================================================================================
    public void calcularTempoMedioInsercoes(int quantidadeExecucoes, int quantidadeRepeticoes) {
        // 10 é o tamanho padrão em Java (até onde eu sei kk)
        this.calcularTempoMedioInsercoes(quantidadeExecucoes, quantidadeRepeticoes, 10);
    }

    public void calcularTempoMedioInsercoes(
            int quantidadeExecucoes,
            int quantidadeRepeticoes,
            int tamanhoInicialListaVetorial) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for(int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = new ArrayList(tamanhoInicialListaVetorial);
            listaEncadeada = new LinkedList();

            tempoTotalListaVetorial += calcularTempoInsercaoLista(quantidadeExecucoes, listaVetorial);
            tempoTotalListaEncadeada += calcularTempoInsercaoLista(quantidadeExecucoes, listaEncadeada);

        }

        long tempoMedioListaVetorialNanosegundos = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeadaNanosegundos = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println(" ---- Comparação de tempo médio de inserção de itens");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeadaNanosegundos / 1_000_000.0 ) + " ms");
        System.out.println("Lista vetorial (iniciada com " + tamanhoInicialListaVetorial
        + "): " + (tempoMedioListaVetorialNanosegundos / 1_000_000.0) + " ms");

    }

    public long calcularTempoInsercaoLista(int quantidadeExecucoes, List lista) {
        long tempoInicial = System.nanoTime();

        for(int i = 0; i < quantidadeExecucoes; i++) {
            lista.add(new Object());
        }

        long tempoFinal = System.nanoTime();

        return calcularTempoExecucao(tempoInicial, tempoFinal);
    }

    // Questão 2 =======================================================================================================
    public void calcularTempoMedioInsercoesAleatorias(int quantidadeExecucoes, int quantidadeRepeticoes) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for (int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = new ArrayList<>();
            listaEncadeada = new LinkedList<>();

            tempoTotalListaVetorial += calcularTempoInsercaoAleatoriaLista(quantidadeExecucoes, listaVetorial);
            tempoTotalListaEncadeada += calcularTempoInsercaoAleatoriaLista(quantidadeExecucoes, listaEncadeada);
        }

        long tempoMedioListaVetorial = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeada = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println("---- Comparação de inserções aleatórias");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeada / 1_000_000.0) + " ms");
        System.out.println("Lista vetorial: " + (tempoMedioListaVetorial / 1_000_000.0) + " ms");

    }

    public long calcularTempoInsercaoAleatoriaLista(int quantidadeExecucoes, List<Object> lista) {

        long tempoInicial = System.nanoTime();

        for (int i = 0; i < quantidadeExecucoes; i++) {
            int indiceAleatorio = random.nextInt(lista.size() + 1);
            lista.add(indiceAleatorio, new Object());
        }

        long tempoFinal = System.nanoTime();

        return tempoFinal - tempoInicial;
    }

    // Questão 3.1 =====================================================================================================
    public void calcularTempoMedioRemocaoInicio(int quantidadeExecucoes, int quantidadeRepeticoes) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for (int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = criarListaPreenchidaArrayList(quantidadeExecucoes);
            listaEncadeada = criarListaPreenchidaLinkedList(quantidadeExecucoes);

            tempoTotalListaVetorial += calcularTempoRemocaoInicio(listaVetorial);
            tempoTotalListaEncadeada += calcularTempoRemocaoInicio(listaEncadeada);
        }

        long tempoMedioListaVetorial = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeada = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println("---- Remoção do primeiro elemento");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeada / 1_000_000.0) + " ms");
        System.out.println("Lista vetorial: " + (tempoMedioListaVetorial / 1_000_000.0) + " ms");

    }

    public long calcularTempoRemocaoInicio(List<Object> lista) {

        long tempoInicial = System.nanoTime();

        while (!lista.isEmpty()) {
            lista.remove(0);
        }

        long tempoFinal = System.nanoTime();

        return tempoFinal - tempoInicial;
    }

    // Questão 3.2 =====================================================================================================
    public void calcularTempoMedioRemocaoFinal(int quantidadeExecucoes, int quantidadeRepeticoes) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for (int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = criarListaPreenchidaArrayList(quantidadeExecucoes);
            listaEncadeada = criarListaPreenchidaLinkedList(quantidadeExecucoes);

            tempoTotalListaVetorial += calcularTempoRemocaoFinal(listaVetorial);
            tempoTotalListaEncadeada += calcularTempoRemocaoFinal(listaEncadeada);
        }

        long tempoMedioListaVetorial = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeada = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println("---- Remoção do último elemento");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeada / 1_000_000.0) + " ms");
        System.out.println("Lista vetorial: " + (tempoMedioListaVetorial / 1_000_000.0) + " ms");

    }

    public long calcularTempoRemocaoFinal(List<Object> lista) {

        long tempoInicial = System.nanoTime();

        while (!lista.isEmpty()) {
            lista.remove(lista.size() - 1);
        }

        long tempoFinal = System.nanoTime();

        return tempoFinal - tempoInicial;
    }

    // Questão 4 =======================================================================================================
    public void calcularTempoMedioRemocaoAleatoria(int quantidadeExecucoes, int quantidadeRepeticoes) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for (int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = criarListaPreenchidaArrayList(quantidadeExecucoes);
            listaEncadeada = criarListaPreenchidaLinkedList(quantidadeExecucoes);

            tempoTotalListaVetorial += calcularTempoRemocaoAleatoria(listaVetorial);
            tempoTotalListaEncadeada += calcularTempoRemocaoAleatoria(listaEncadeada);
        }

        long tempoMedioListaVetorial = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeada = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println("---- Remoção aleatória");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeada / 1_000_000.0) + " ms");
        System.out.println("Lista vetorial: " + (tempoMedioListaVetorial / 1_000_000.0) + " ms");

    }

    public long calcularTempoRemocaoAleatoria(List<Object> lista) {

        long tempoInicial = System.nanoTime();

        while (!lista.isEmpty()) {
            int indiceAleatorio = random.nextInt(lista.size());
            lista.remove(indiceAleatorio);
        }

        long tempoFinal = System.nanoTime();

        return tempoFinal - tempoInicial;
    }

    // Questão 5 =======================================================================================================
    public void calcularTempoMedioAcessoAleatorio(int quantidadeElementos, int quantidadeRepeticoes) {

        long tempoTotalListaVetorial = 0;
        long tempoTotalListaEncadeada = 0;

        for (int i = 0; i < quantidadeRepeticoes; i++) {

            listaVetorial = criarListaPreenchidaArrayList(quantidadeElementos);
            listaEncadeada = criarListaPreenchidaLinkedList(quantidadeElementos);

            tempoTotalListaVetorial += calcularTempoAcessoAleatorio(listaVetorial);
            tempoTotalListaEncadeada += calcularTempoAcessoAleatorio(listaEncadeada);
        }

        long tempoMedioListaVetorial = tempoTotalListaVetorial / quantidadeRepeticoes;
        long tempoMedioListaEncadeada = tempoTotalListaEncadeada / quantidadeRepeticoes;

        System.out.println("---- Acesso aleatório");
        System.out.println("Lista encadeada: " + (tempoMedioListaEncadeada / 1_000_000.0) + " ms");
        System.out.println("Lista vetorial: " + (tempoMedioListaVetorial / 1_000_000.0) + " ms");

    }

    public long calcularTempoAcessoAleatorio(List<Object> lista) {

        long tempoInicial = System.nanoTime();

        for (int i = 0; i < 10_000; i++) {
            int indiceAleatorio = random.nextInt(lista.size());
            lista.get(indiceAleatorio);
        }

        long tempoFinal = System.nanoTime();

        return tempoFinal - tempoInicial;
    }

    // Questão 6 =======================================================================================================
    /*
    -> ArrayList tende a ser melhor em inserções no final, acesso aleatório e remoção no final.
    -> LinkedList tende a ser melhor em inserções e remoções no início.
    -> Em inserções ou remoções aleatórias, muitas vezes ArrayList ainda pode surpreender e ficar próximo ou até melhor,
     porque LinkedList precisa percorrer a lista até chegar no índice desejado.
    -> Fatores práticos que podem influenciar:
        * Garbage Collector
        * Uso de CPU por outros programas
        * Quantidade de memória disponível
        * Tamanho escolhido para os testes
        * Diferença entre nanossegundos e milissegundos
        * Cache de memória do processador
     */
    // =================================================================================================================


    // Métodos auxiliares ==============================================================================================
    public List<Object> criarListaPreenchidaArrayList(int quantidadeElementos) {

        List<Object> lista = new ArrayList<>(quantidadeElementos);

        for (int i = 0; i < quantidadeElementos; i++) {
            lista.add(new Object());
        }

        return lista;
    }

    public List<Object> criarListaPreenchidaLinkedList(int quantidadeElementos) {

        List<Object> lista = new LinkedList<>();

        for (int i = 0; i < quantidadeElementos; i++) {
            lista.add(new Object());
        }

        return lista;
    }

    public long calcularTempoExecucao(Long tempoInicial, Long tempoFinal) {
        return tempoFinal - tempoInicial;
    };
}
