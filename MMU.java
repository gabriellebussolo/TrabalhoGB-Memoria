
public class MMU {

	private int[][] tabelaDePaginas;
	private MemoriaPrincipal mp;
	private MemoriaVirtual mv;
	private int[] historico;

	/*
	 * historico = {total de solicitacoes, quant de solicitacoes inseridas na
	 * memoria principal sem substituicao, quant de substituicoes, quant de
	 * solicitacao que ja estavam na memoria principal}
	 */
	public MMU(int tam, MemoriaPrincipal mp, MemoriaVirtual mv) {
		tabelaDePaginas = this.inicializaTabelaPaginas(tam); // [frame memoria principal][bit validade]
		this.mp = mp;
		this.mv = mv;
		this.historico = new int[4];
	}

	public int[] getHistorico() {
		return historico;
	}

	public MemoriaVirtual getMv() {
		return mv;
	}

	public int[][] getTabelaDePaginas() {
		return tabelaDePaginas;
	}

	public int[][] inicializaTabelaPaginas(int tam) {
		this.tabelaDePaginas = new int[tam][2];

		for (int i = 0; i < tabelaDePaginas.length; i++)
			tabelaDePaginas[i][0] = -1;

		return tabelaDePaginas;
	}

	public int procuraFrame(int frame) {
		for (int i = 0; i < tabelaDePaginas.length; i++) {
			if (tabelaDePaginas[i][0] == frame)
				return i;
		}
		return -1;
	}

	// Bit de validade: 0 = não se encontra na memória principal, 1 = se encontra
	public int verificaBitValidade(int pag) {
		if (tabelaDePaginas[pag][1] == 1)
			return tabelaDePaginas[pag][0];
		else
			return -1;
	}

	public void atualizaNovoFrame(int pag, int frame) {
		this.tabelaDePaginas[pag][1] = 1; // Bit de validade = 1
		this.tabelaDePaginas[pag][0] = frame; // Atualiza frame
	}

	public void atualizaFrameSubstituido(int frame) {
		int pag = procuraFrame(frame);

		this.tabelaDePaginas[pag][1] = 0; // Bit de validade = 0
		this.tabelaDePaginas[pag][0] = -1; // Frame = -1
	}

	public void respondeSolicitacao(String solicitacao) {

		try {
			this.historico[0]++;

			// Procura na memoria virtual a pagina que a solicitacao se encontra
			int pagMV = mv.search(solicitacao);
			int frameMP = 0;

			if (pagMV == -1)
				System.out.println("ERRO: solicitacao nao se encontra na memoria virtual");
			else {
				System.out.println("Verificando tabela de paginas...");

				Thread.sleep(2000);

				// Verifica qual o bit de validade dessa pagina na tabela de paginas
				frameMP = verificaBitValidade(pagMV);

				// Bit de validade = 1 (pagina se encontra na memoria principal)
				if (frameMP > -1) {
					this.historico[3]++;
					System.out.printf("Processo %s foi encontrado na memória principal:\n", solicitacao);
					System.out.printf("Pagina virtual: %s\n", pagMV);
					System.out.printf("Frame da memoria principal: %s\n", frameMP);
					System.out.printf("Conteúdo no frame: %s\n", mp.search(frameMP));

				// Bit de validade = 0 (pagina nao se encontra na memoria principal)
				} else {
					System.out.println("Ocorreu uma Falta de Página!");
					Thread.sleep(2000);
					System.out.println("Tentando adicionar pagina faltante na memoria principal...");
					Thread.sleep(2000);
					
					// Memoria virtual cheia: chama algoritmo de substituicao
					if (mp.isFull()) {
						System.out.println("Memoria Principal cheia - processo de substituicao ativado...");

						Thread.sleep(2000);

						int frameSubstituido = mp.substituicao(solicitacao);
						this.atualizaFrameSubstituido(frameSubstituido);
						this.atualizaNovoFrame(pagMV, frameSubstituido);
						System.out.println("Frame substituido: " + frameSubstituido);

						this.historico[2]++;
						
					// Memoria virtual possui espaço: adiciona pagina a um frame na memoria principal
					} else {
						frameMP = mp.add(solicitacao);
						this.atualizaNovoFrame(pagMV, frameMP);
						this.historico[1]++;
					}
				}

				System.out.println("\n======================== Estado atual ==========================");
				Thread.sleep(1500);
				System.out.println(mv);
				Thread.sleep(1500);
				System.out.println(this.toString());
				Thread.sleep(1500);
				System.out.println(mp);
				Thread.sleep(1500);
				System.out.println("==================================================================");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String retorno = "====== Tabela de Paginas (Pagina | Frame | Bit de validade) ======\n";

		int[] linha = new int[4];

		for (int i = 0; i < this.tabelaDePaginas.length / 4; i++) {
			linha[0] = i;
			linha[1] = i + this.tabelaDePaginas.length / 4;
			linha[2] = i + this.tabelaDePaginas.length / 2;
			linha[3] = i + (this.tabelaDePaginas.length / 4 + this.tabelaDePaginas.length / 2);

			for (int j = 0; j < linha.length; j++) {
				if (this.tabelaDePaginas[linha[j]][0] == -1)
					retorno += linha[j] + "| |" + this.tabelaDePaginas[linha[j]][1] + "\t\t";
				else
					retorno += linha[j] + "|" + this.tabelaDePaginas[linha[j]][0] + "|"
							+ this.tabelaDePaginas[linha[j]][1] + "\t\t";
			}
			retorno += "\n";
		}

		return retorno;
	}

}
