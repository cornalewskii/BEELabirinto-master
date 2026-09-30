import java.util.Scanner;

public class ExemploLabirinto {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		boolean debug = true;
		Labirinto labirinto = new Labirinto(10, 10, 30, debug);

		System.out.println("======================================");
		System.out.println("        LABIRINTO INICIAL");
		System.out.println("======================================");

		labirinto.print(null);

		System.out.println("Entrada: " + labirinto.getPosicaoAtual());
		System.out.println("Saída:   " + labirinto.getPosicaoSaida());


		System.out.println("\n======================================");
		System.out.println("              MENU");
		System.out.println("======================================");
		System.out.println("1 - Labirinto com informação");
		System.out.println("2 - Labirinto sem informação Busca em Largura (BFS)");
		System.out.println("3 - Labirinto sem informação Busca em Profundidade (DFS)");
		System.out.println("0 - Todos os algoritmos");
		System.out.println("======================================");
		System.out.print("Escolha uma opção: ");

		int opcao = scanner.nextInt();

		Posicao[] caminho = null;


		switch (opcao) {

			case 1:

				System.out.println("\n======================================");
				System.out.println("       BUSCA COM INFORMAÇÃO");
				System.out.println("======================================");

				BuscaComInformacao buscaComInformacao =
						new BuscaComInformacao(labirinto, debug);

				caminho = buscaComInformacao.buscar(true, false);

				break;

			case 2:

				System.out.println("\n======================================");
				System.out.println("        Busca em Largura (BFS)");
				System.out.println("======================================");

			     	BFS buscaEmLargura = new BFS(labirinto, debug);
					caminho = buscaEmLargura.buscar(false, false);

				break;
			case 3:

				System.out.println("\n======================================");
				System.out.println("        Busca em Profundidade (DFS)");
				System.out.println("======================================");

					Busca_profundidade buscaEmProfundidade = new Busca_profundidade(labirinto, debug);
					caminho = buscaEmProfundidade.buscar(false, false);

				break;

			case 0:
				
				System.out.println("\n======================================");
				System.out.println("       BUSCA COM INFORMAÇÃO");
				System.out.println("======================================");

				BuscaComInformacao buscaComInformacaoTodos =
					new BuscaComInformacao(labirinto, debug);
					long TempoInicial = System.nanoTime();
                    caminho = buscaComInformacaoTodos.buscar(true, false);
					long TempoFinal = System.nanoTime();
					long TempoDecorrido = (TempoFinal - TempoInicial);
					labirinto.print(caminho);
					System.out.println("Tempo decorrido: " + TempoDecorrido + " Nanosegundos");

				System.out.println("\n======================================");
				System.out.println("        Busca em Largura (BFS)");
				System.out.println("======================================");

			     	BFS buscaEmLarguraTodos = new BFS(labirinto, debug);
					TempoInicial = System.nanoTime();
					caminho = buscaEmLarguraTodos.buscar(false, false);
					TempoFinal = System.nanoTime();
					labirinto.print(caminho);
					TempoDecorrido = (TempoFinal - TempoInicial);
					System.out.println("Tempo decorrido: " + TempoDecorrido + " Nanosegundos");	


				System.out.println("\n======================================");
				System.out.println("        Busca em Profundidade (DFS)");
				System.out.println("======================================");

					Busca_profundidade buscaEmProfundidadeTodos = new Busca_profundidade(labirinto, debug);
					TempoInicial = System.nanoTime();
					caminho = buscaEmProfundidadeTodos.buscar(false, false);
					TempoFinal = System.nanoTime();
					labirinto.print(caminho);
					TempoDecorrido = (TempoFinal - TempoInicial);
					System.out.println("Tempo decorrido: " + TempoDecorrido + " Nanosegundos");

				break;
			default:

				System.out.println("\nOpção inválida.");
				scanner.close();
				return;
		}

		System.out.println("\n======================================");
		System.out.println("          RESULTADO");
		System.out.println("======================================");

		if (caminho == null) {

			System.out.println("Nenhum caminho encontrado.");

			// Imprime o labirinto sem caminho
			labirinto.print(null);

		} else if (caminho != null && opcao != 0) {

			// Imprime o labirinto com o caminho encontrado
			labirinto.print(caminho);

			System.out.println("\nCasas percorridas: " + caminho.length);

			System.out.print("Caminho: ");

			for (Posicao posicao : caminho) {
				System.out.print(posicao + " ");
			}

			System.out.println();
		}
		scanner.close();
	}
}