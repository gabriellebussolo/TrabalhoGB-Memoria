## Trabalho Prático - Gerenciamento de Memória
 
O Trabalho Prático - Gerenciamento de Memória, consiste no segundo trabalho da disciplina de Laboratório de Sistemas Operacionais. Nele, foi criado um simulador de página virtual.
Haverá três estruturas principais para simular o gerenciamento de memória: uma memória principal de 64KB, uma memória virtual de 1MB (1024KB) e uma MMU para gerenciar a tabela de páginas. Além disso, durante a execução do código é criado uma thread responsável por solicitar acesso a suas páginas ao passar um período de tempo aleatório.

<p align="center">
 <a href="#tecnologias">Tecnologias</a> •
 <a href="#serviços-usados">Serviços usados</a> • 
 <a href="#features">Features</a> • 
 <a href="#autora">Autora</a>
</p>
 
## Tecnologias 
  
* Java SE 15
 
## Serviços usados
 
* Github
* Eclipse IDE version 4.24.0

## Features
 
* Solicitação de acesso a suas páginas pelo processo (thread)
* Adição de páginas do processo à memória virtual
* Adição de páginas do processo à memória principal
* Mapeamento das páginas em memória virtual nos frames da memória principal por meio da tabela de páginas
* Substituição de frames na memória principal por meio do algoritmo FIFO (first in - first out)
* Visualização das mudanças realizadas nas memórias da seguinte forma:
  * Memória Virtual: "(x|y)", sendo x o número da página na memória virtual e y a identificação da página do processo
  * Tabela de Páginaas: "(x|y|z)", sendo x a página da memória virtual, y o frame na memória principal e z o bit de validade (0 = não se encontra na memória principal, 1 = se encontra)
  * "RR:" para quando um produto de qualdiade "Ruim" for removido  

![Imagem da execução do código](Memorias_execucao.jpg )                                                                                                          
<sub>Exemplo da visualização das mudanças na memória.</sub>

* Visualização da estatística final do simulador, incluindo:
  * Lista de produtos produzidos de cada tipo
  * Quantidade total de produtos produzidos de cada tipo
  * Média de tempo de inserção e remoção de cada tipo de produto

![Imagem da impressão da estatística final durante a execução do código](Esteira_resumo.jpg )                                                                  
<sub>Exemplo da visualização da estatística final.</sub>  
 
## Autora
 
* **Gabrielle Bussolo**: @gabriellebussolo (https://github.com/gabriellebussolo)
