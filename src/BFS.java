import java.util.ArrayList;
/**
 * Classe que implementa o algoritmo de busca em largura (BFS) para encontrar
 * a saída do  labirinto.
 */
public class BFS {

    public ArrayList<Posicao> Fila;
    public ArrayList<Nodo> Arvore;
    public boolean[][] visitados;

    public void Inicializa(Labirinto labirinto) {

        Fila = new ArrayList<>();
        Arvore = new ArrayList<>();
        visitados = new boolean[labirinto.getDimX()][labirinto.getDimY()];
        Nodo raiz = new Nodo(null, labirinto.getPosicaoAtual());
        Arvore.add(raiz);
        AdicionaNaFila(labirinto.getPosicaoAtual());
    }
    public void busca(Labirinto labirinto) {
        while (!Fila.isEmpty()) {

            Posicao p = Fila.get(0);
            Fila.remove(0);

            if (p.comparaCom(labirinto.getPosicaoSaida())) {
                System.out.println("Saída encontrada: " + p.toString());
                ArrayList<Posicao> caminho = getCaminho(p);
                System.out.println("Caminho até a saída:");
                for (Posicao pos : caminho) {
                    System.out.println("  " + pos.toString());
                }
                return;
            }

            ArrayList<Posicao> expansao = labirinto.getExpansao(p);

            for (Posicao pos : expansao) {
                if (!visitados[pos.getX()][pos.getY()]) {

                    visitados[pos.getX()][pos.getY()] = true;
                    Nodo nodoPai = getNodoPorValor(p); // Retorna o nodo Pai que contém a posição p
                    Nodo nodoFilho = new Nodo(nodoPai, pos);
                    Arvore.add(nodoFilho);
                    AdicionaNaFila(pos);
                }
            }

        }
        System.out.println("Busca finalizada. Saída não encontrada.");

    }

    public void AdicionaNaFila(Posicao p) {
        Fila.addLast(p);
    }

    public Nodo getNodoPorValor(Posicao p ){
        for (Nodo nodo : Arvore) {
            if (((Posicao) nodo.getValor()).comparaCom(p)) {
                return nodo;
            }
        }
        return null;
    }
    public ArrayList<Posicao> getCaminho(Posicao p) {
        ArrayList<Posicao> caminho = new ArrayList<>();
        Nodo nodo = getNodoPorValor(p);
        while (nodo != null) {
            caminho.add(0, (Posicao) nodo.getValor());
            nodo = nodo.getPai();
        }
        return caminho;
    }
}
