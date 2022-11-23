
public class solicitaPagina implements Runnable {
	
	private MMU tabela;
	private int numProcesso;
	private int quantPag;
	private int quantSolicitacao;

	public solicitaPagina(MMU tabela, int num, int quantPag, int quantSolicitacao) {
		this.tabela = tabela;
		this.numProcesso = num;
		this.quantPag = quantPag;
		this.quantSolicitacao = quantSolicitacao;
	}

	@Override
	public void run() {
		
		for(int i = 0; i < quantSolicitacao; i++) {
			int paginaEscolhida = (int) (1 + (Math.random() * quantPag));
			
			String solicitacao = numProcesso + "-" + paginaEscolhida;
			
			System.out.printf("Processo %d: solicitando acesso a pagina %d\n", this.numProcesso, paginaEscolhida);
			
			try {
				Thread.sleep(1500);
				tabela.respondeSolicitacao(solicitacao);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
