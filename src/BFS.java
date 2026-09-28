import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * Classe que implementa o algoritmo de busca em largura (BFS) para encontrar
 * a saída do  labirinto.
 */
public class BFS extends AbstractBusca {
    private Queue<Nodo> fila;
    private boolean[][] visitados;

    public BFS(Labirinto labirinto, boolean debug) {
        super(labirinto, debug);
    }

    public Posicao[] buscar(boolean aEstrela, boolean aEstrelaAlt) {
        raiz = new Nodo(null, labirinto.getPosicaoAtual());
        fila = new ArrayDeque<>();
        visitados = new boolean[labirinto.getDimX()][labirinto.getDimY()];

        Posicao inicio = (Posicao) raiz.getValor();
        visitados[inicio.getX()][inicio.getY()] = true;
        fila.add(raiz);

        while (!fila.isEmpty()) {
            Nodo solucao = expandir(fila.remove(), aEstrela, aEstrelaAlt);
            if (solucao != null) {
                return construirCaminho(solucao);
            }
        }

        if (debug) System.out.println("Busca em largura: saída não encontrada.");
        return null;
    }

    public Nodo expandir(Nodo nodo, boolean aEstrela, boolean aEstrelaAlt) {
        Posicao atual = (Posicao) nodo.getValor();
        if (atual.comparaCom(labirinto.getPosicaoSaida())) {
            if (debug) System.out.println("Saída encontrada: " + atual);
            return nodo;
        }

        for (Posicao proxima : labirinto.getExpansao(atual)) {
            int x = proxima.getX();
            int y = proxima.getY();
            if (!visitados[x][y]) {
                visitados[x][y] = true;
                fila.add(new Nodo(nodo, proxima));
            }
        }
        return null;
    }

    private Posicao[] construirCaminho(Nodo solucao) {
        ArrayList<Posicao> caminho = new ArrayList<>();
        for (Nodo nodo = solucao; nodo != null; nodo = nodo.getPai()) {
            caminho.add(0, (Posicao) nodo.getValor());
        }
        return caminho.toArray(new Posicao[0]);
    }
}
