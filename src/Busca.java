public class Busca extends AbstractBusca {

    public Busca(Labirinto l, boolean d) {
        super(l, d);
    }

    @Override
    public Posicao[] buscar(boolean aEstrela, boolean aEstrelaAlt) {
        if (!aEstrela && !aEstrelaAlt) {
            BFS bfs = new BFS();
            bfs.Inicializa(labirinto);
            return bfs.busca(labirinto);
        } else if (!aEstrela && aEstrelaAlt) {
            Busca_profundidade buscaProfundidade = new Busca_profundidade();
            buscaProfundidade.Inicializa(labirinto);
            return buscaProfundidade.busca(labirinto);
        } else {
                  throw new UnsupportedOperationException("Algoritmo de busca não implementado.");
        }
    }

    @Override
    public Nodo expandir(Nodo n, boolean aEstrela, boolean aEstrelaAlt) {
        return null;
    }
    
}
