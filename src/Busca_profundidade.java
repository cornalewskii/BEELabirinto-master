import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
public class Busca_profundidade  {
        
    public Queue<Posicao> Pilha;
    public ArrayList<Nodo> Arvore;
    public boolean[][] visitados;

    public void Inicializa(Labirinto labirinto) {

        Pilha = new ArrayDeque<>();
        Arvore = new ArrayList<>();
        visitados = new boolean[labirinto.getDimX()][labirinto.getDimY()];
        Nodo raiz = new Nodo(null, labirinto.getPosicaoAtual());
        Arvore.add(raiz);
        AdicionaNaPilha(labirinto.getPosicaoAtual());
    }
    public Posicao[] busca(Labirinto labirinto) {

        while (!Pilha.isEmpty()) {

            Posicao p = Pilha.poll();
            

            if (p.comparaCom(labirinto.getPosicaoSaida())) {
                System.out.println("Saída encontrada: " + p.toString());
                ArrayList<Posicao> caminho = getCaminho(p);
                System.out.println("Caminho até a saída:");
                for (Posicao pos : caminho) {
                    System.out.println("  " + pos.toString());
                }
                return caminho.toArray(new Posicao[0]);
            }

            ArrayList<Posicao> expansao = labirinto.getExpansao(p);

            for (Posicao pos : expansao) {
                if (!visitados[pos.getX()][pos.getY()]) {

                    visitados[pos.getX()][pos.getY()] = true;
                    Nodo nodoPai = getNodoPorValor(p); // Retorna o nodo Pai que contém a posição p
                    Nodo nodoFilho = new Nodo(nodoPai, pos);
                    Arvore.add(nodoFilho);
                    AdicionaNaPilha(pos);
                }
            }

        }
        System.out.println("Busca finalizada. Saída não encontrada.");
        return null;

    }

    public void AdicionaNaPilha(Posicao p) {
        Pilha.add(p);
    }
    public Nodo getNodoPorValor(Posicao p ){
        for (Nodo nodo : Arvore) {
            if (nodo.getValor() instanceof Posicao) {
                Posicao pos = (Posicao) nodo.getValor();
                if (pos.comparaCom(p)) {
                    return nodo;
                }
            }
        }
        return null; // Retorna null se não encontrar o nodo correspondente
    }
    public ArrayList<Posicao> getCaminho(Posicao p) {
        ArrayList<Posicao> caminho = new ArrayList<>();
        Nodo nodoAtual = getNodoPorValor(p);

        while (nodoAtual != null) {
            if (nodoAtual.getValor() instanceof Posicao) {
                caminho.add(0, (Posicao) nodoAtual.getValor()); // Adiciona no início da lista
            }
            nodoAtual = nodoAtual.getPai();
        }

        return caminho;
    }

}
