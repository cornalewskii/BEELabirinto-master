import java.util.ArrayList;

/** Busca em profundidade: explora cada ramo antes de voltar ao anterior. */
public class Busca_profundidade extends AbstractBusca {
    private boolean[][] visitados;

    public Busca_profundidade(Labirinto labirinto, boolean debug) {
        super(labirinto, debug);
    }

    public Posicao[] buscar(boolean aEstrela, boolean aEstrelaAlt) {
        raiz = new Nodo(null, labirinto.getPosicaoAtual());
        visitados = new boolean[labirinto.getDimX()][labirinto.getDimY()];

        Nodo solucao = expandir(raiz, aEstrela, aEstrelaAlt);
        if (solucao == null) {
            if (debug) System.out.println("Busca em profundidade: saída não encontrada.");
            return null;
        }
        return construirCaminho(solucao);
    }

    public Nodo expandir(Nodo nodo, boolean aEstrela, boolean aEstrelaAlt) {
        Posicao atual = (Posicao) nodo.getValor();
        if (visitados[atual.getX()][atual.getY()]) return null;
        visitados[atual.getX()][atual.getY()] = true;

        if (atual.comparaCom(labirinto.getPosicaoSaida())) {
            if (debug) System.out.println("Saída encontrada: " + atual);
            return nodo;
        }

        for (Posicao proxima : labirinto.getExpansao(atual)) {
            if (!visitados[proxima.getX()][proxima.getY()]) {
                Nodo solucao = expandir(new Nodo(nodo, proxima), aEstrela, aEstrelaAlt);
                if (solucao != null) return solucao;
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
