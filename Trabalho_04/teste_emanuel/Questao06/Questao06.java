package Trabalho_04.Questao06;

public class Questao06 {

    public static void main(String[] args) {
        System.out.println("=== Questão 6: Conclusões Gerais ===");
        System.out.println();

        // ── 1. Vantagens do ArrayList ─────────────────────────────────────
        System.out.println("1. Cenários onde ArrayList foi mais vantajoso:");
        System.out.println();
        System.out.println("   Q1 – Inserção ao final:");
        System.out.println("     Ambas são O(1) amortizado, mas o ArrayList usa um array contíguo,");
        System.out.println("     favorecendo o prefetcher da CPU e evitando alocação de objetos Node.");
        System.out.println("     Com capacidade inicial igual ao total de elementos, não ocorrem");
        System.out.println("     realocações e o desempenho é ainda melhor.");
        System.out.println();
        System.out.println("   Q3 Parte 2 – Remoção do último elemento:");
        System.out.println("     Ambas são O(1), mas a localidade de cache do ArrayList favorece");
        System.out.println("     tempos ligeiramente menores.");
        System.out.println();
        System.out.println("   Q5 – Acesso aleatório por índice:");
        System.out.println("     ArrayList: O(1) por acesso (cálculo direto de endereço).");
        System.out.println("     LinkedList: O(n) por acesso (percurso de nó em nó).");
        System.out.println("     Esta é a diferença mais expressiva entre as duas estruturas.");
        System.out.println();

        // ── 2. Vantagens do LinkedList ────────────────────────────────────
        System.out.println("2. Cenários onde LinkedList foi mais vantajoso:");
        System.out.println();
        System.out.println("   Q3 Parte 1 – Remoção do primeiro elemento:");
        System.out.println("     LinkedList.removeFirst() é O(1): basta redirecionar o ponteiro head.");
        System.out.println("     ArrayList.remove(0) é O(n): todos os elementos são deslocados");
        System.out.println("     uma posição à esquerda a cada remoção.");
        System.out.println();
        System.out.println("   Inserções/remoções frequentes no início ou no meio da lista:");
        System.out.println("     Após localizar o nó (O(n)), o reencadeamento é O(1).");
        System.out.println("     No ArrayList o deslocamento de elementos é sempre necessário.");
        System.out.println();

        // ── 3. Confirmação da teoria ──────────────────────────────────────
        System.out.println("3. Os resultados experimentais confirmaram a teoria esperada?");
        System.out.println();
        System.out.println("   Em grande parte sim. As diferenças mais expressivas apareceram");
        System.out.println("   exatamente onde a teoria prevê maior assimetria de complexidade:");
        System.out.println("   • Acesso aleatório: ArrayList O(1) vs LinkedList O(n) → enorme vantagem.");
        System.out.println("   • Remoção do início: LinkedList O(1) vs ArrayList O(n) → clara vantagem.");
        System.out.println("   Operações de inserção ao final e remoção do último elemento mostraram");
        System.out.println("   ArrayList ligeiramente superior mesmo com complexidade igual, graças");
        System.out.println("   à localidade de cache e ausência de alocação de objetos Node.");
        System.out.println();

        // ── 4. Fatores práticos ───────────────────────────────────────────
        System.out.println("4. Fatores práticos que podem ter influenciado os resultados:");
        System.out.println();
        System.out.println("   • Localidade de cache (cache locality):");
        System.out.println("     O array contíguo do ArrayList é lido em blocos pela CPU (cache line),");
        System.out.println("     minimizando cache misses. Os nós da LinkedList estão espalhados no heap,");
        System.out.println("     causando muitos cache misses e penalizando o desempenho real.");
        System.out.println();
        System.out.println("   • Pressão no Garbage Collector:");
        System.out.println("     A LinkedList cria um objeto Node<E> por elemento. Com 100.000 elementos,");
        System.out.println("     são 100.000 objetos extras no heap, aumentando a frequência de coletas");
        System.out.println("     e adicionando variância nos tempos medidos.");
        System.out.println();
        System.out.println("   • JIT warm-up da JVM:");
        System.out.println("     As primeiras iterações são interpretadas; a compilação JIT ocorre durante");
        System.out.println("     as primeiras execuções. Calcular a média de 20 testes atenua esse efeito,");
        System.out.println("     mas os primeiros testes podem ser ligeiramente mais lentos.");
        System.out.println();
        System.out.println("   • Autoboxing (int → Integer):");
        System.out.println("     Cada inserção de um primitivo int converte para Integer, adicionando");
        System.out.println("     alocações no heap e overhead de boxing/unboxing.");
        System.out.println();
        System.out.println("   • Capacidade inicial do ArrayList:");
        System.out.println("     Com capacidade 10 e 100.000 inserções, ocorrem ~17 realocações,");
        System.out.println("     cada uma dobrando o array e copiando todos os elementos existentes.");
        System.out.println("     Inicializar com a capacidade final elimina esse custo completamente.");
    }
}
