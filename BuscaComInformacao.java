import java.util.ArrayList;

public class BuscaComInformacao extends AbstractBusca {

    public BuscaComInformacao(Labirinto l, boolean d) {
        super(l, d);
    }

    @Override
    public Posicao[] buscar(boolean aEstrela, boolean aEstrelaAlt) {

        //nodo inicial
        raiz = new Nodo(null, labirinto.getPosicaoAtual());

        // Nodos em aberto (ainda serão analisados).
        ArrayList<Nodo> abertos = new ArrayList<Nodo>();
        ArrayList<Posicao> fechados = new ArrayList<Posicao>();

        abertos.add(raiz);

        while (!abertos.isEmpty()) {

            //Pega o nodo com melhor posição e se move para ele
            Nodo atual = melhorNodo(abertos, aEstrela, aEstrelaAlt);
            abertos.remove(atual);
            Posicao posicao = (Posicao) atual.getValor();

            // Verifica se encontrou a saída, se encontrou, retorna todo o caminho percorrido.
            if (posicao.comparaCom(labirinto.getPosicaoSaida())) {
                return criarCaminho(atual);
            }

            // Marca a posição como já analisada.
            fechados.add(posicao);

            // Cria os filhos do nodo atual.
            expandir(atual, aEstrela, aEstrelaAlt);

            // Percorre todos os filhos criados.
            Nodo filho = atual.getFilho();

            while (filho != null) {

                Posicao posicaoFilho = (Posicao) filho.getValor();

                // Só adiciona se ainda não foi analisado.
                if (!fechados.contains(posicaoFilho) && !contemPosicao(abertos, posicaoFilho)) {
                    abertos.add(filho);
                }

                filho = filho.getIrmao();
            }
        }

        // Não encontrou caminho.
        return null;
    }


    @Override
    public Nodo expandir(Nodo n, boolean aEstrela, boolean aEstrelaAlt) {

        Posicao atual = (Posicao) n.getValor();

        // Obtém os movimentos possíveis.
        ArrayList<Posicao> movimentos = labirinto.getExpansao(atual);

        // Cria um Nodo para cada movimento válido, não cria caminhos que já foram percorridos.
        for (Posicao posicao : movimentos) {

            if (estaNoCaminho(n, posicao)) {
                continue;
            }
            new Nodo(n, posicao);
        }

        return n.getFilho();
    }

    //Retorna o nodo mais próximo da saida
    private Nodo melhorNodo(ArrayList<Nodo> abertos, boolean aEstrela, boolean aEstrelaAlt) {

        Nodo melhor = abertos.get(0);

        for (Nodo nodo : abertos) {

            if (valor(nodo, aEstrela, aEstrelaAlt) < valor(melhor, aEstrela, aEstrelaAlt)) {
                melhor = nodo;
            }
        }

        return melhor;
    }

    //Calcula rota até a saída
    private double valor(Nodo nodo, boolean aEstrela, boolean aEstrelaAlt) {

        Posicao atual = (Posicao) nodo.getValor();

        //custo do caminho até o nodo.
        double g = nodo.getProfundidade();
        //estimativa até a saída.
        double h;

        if (aEstrelaAlt) {
            h = Math.abs(atual.getX() - labirinto.getPosicaoSaida().getX()) + Math.abs(atual.getY() - labirinto.getPosicaoSaida().getY());
        } else {
            h = labirinto.getDLR(atual, labirinto.getPosicaoSaida());
        }

        // A*.
        if (aEstrela) {
            return g + h;
        }

        // Busca gulosa.
        return h;
    }

    //Verifica se nodo já existe na lista de abertos
    private boolean contemPosicao(ArrayList<Nodo> nodos, Posicao posicao) {

        for (Nodo nodo : nodos) {

            Posicao p = (Posicao) nodo.getValor();
            if (p.comparaCom(posicao)) {
                return true;
            }
        }
        return false;
    }



    private boolean estaNoCaminho(Nodo nodo, Posicao posicao) {
        Nodo atual = nodo;

        while (atual != null) {

            Posicao p = (Posicao) atual.getValor();
            if (p.comparaCom(posicao)) {
                return true;
            }
            atual = atual.getPai();
        }
        return false;
    }

    //Gera caminho percorrido
    private Posicao[] criarCaminho(Nodo solucao) {

        int tamanho = solucao.getProfundidade() + 1;
        Posicao[] caminho = new Posicao[tamanho];
        Nodo atual = solucao;
        int indice = tamanho - 1;

        while (atual != null) {
            caminho[indice] = (Posicao) atual.getValor();
            indice--;
            atual = atual.getPai();
        }

        return caminho;
    }
}