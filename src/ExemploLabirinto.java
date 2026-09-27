import java.util.Scanner;

public class ExemploLabirinto {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		boolean debug = false;
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
		System.out.println("2 - Labirinto sem informação");
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

			/*case 2:

				System.out.println("\n======================================");
				System.out.println("        BUSCA SEM INFORMAÇÃO");
				System.out.println("======================================");

				BuscaSemInformacao buscaSemInformacao =
						new BuscaSemInformacao(labirinto, debug);

				caminho = buscaSemInformacao.buscar();

				break;

			default:

				System.out.println("\nOpção inválida.");
				scanner.close();
				return;*/
		}

		System.out.println("\n======================================");
		System.out.println("          RESULTADO");
		System.out.println("======================================");

		if (caminho == null) {

			System.out.println("Nenhum caminho encontrado.");

			// Imprime o labirinto sem caminho
			labirinto.print(null);

		} else {

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