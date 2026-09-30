## Atividade
Com base nesta especificação de problema, utilize os comportamentos disponíveis na classe Labirinto para testar algoritmos de busca. Elabore uma classe *Busca* com base na classe abstrata *AbstractBusca*. Então compare os resultados em termos de:
- qualidade da resposta: a quantidade de passos /caminho da solução (i.e. menos passos, maior qualidade);
- desepenho: tempo de execução e quantidade de memória utilizada.

Para a busca *com informação*, pesquise heurísticas que podem ser implementadas (mais de uma), forma a trazer uma avaliação adequada de alternativas.

Arquivo / classe *ExemploLabirinto* é a demonstração de um labirinto com dimensão 10x10 e uma taxa de 30% de obstáculos (preenchimento).

A aplicação imprime um labirinto aleatório, as coordenadas de uma instância de *Posicao*, da posição de entrada, do conjunto de posições possíveis a da posição atual (entrada) e da posição de saída, assim como a distância em linha reta (DLR) entre a posição atual e a de saída.

Se você está desenvolvendo este projeto para uma atividade avaliativa do professor Rodrigo, considere que **a API das classes deste projeto não devem ser alteradas**, de forma que a avaliação possa ser realizada pelo professor com os mesmos princípios presentes classe de demonstração *ExemploLabirinto*.


## Modo de execução

Ao executar o arquivo **`ExemploLabirinto`**, serão apresentadas quatro opções de algoritmos de busca:

1. **Busca com informação — A\***
2. **Busca sem informação — Busca em largura**
3. **Busca sem informação — Busca em profundidade**
4. **Executar todos**

---