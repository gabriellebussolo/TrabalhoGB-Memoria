
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		MemoriaPrincipal mp = new MemoriaPrincipal(8); // 8 pag de 8KB = total 64KB
		MemoriaVirtual mv = new MemoriaVirtual(128); // 128 pag de 8KB = total 1024KB (1MB)
		MMU tabela = new MMU(128, mp, mv);

		// Solicita quantidade de solicitações
		Scanner s = new Scanner(System.in);
		System.out.println("Insira a quantidade de solicitacoes que o processo ira realizar:");
		int quantSolicitacao = Integer.parseInt(s.next());

		// Gera processo de 1 byte a 1048576 bytes (1MB)
		int tamanho = (int) (1 + (Math.random() * 1048576)) / 1024;
		int quantPag = 0;

		if (tamanho % 8 != 0 | tamanho == 0)
			quantPag = (tamanho / 8) + 1;
		else
			quantPag = tamanho / 8;

		try {
			System.out.printf("Criando processo de %d KB (%d paginas de 8KB) e adicionando-o na memoria virtual...\n", tamanho, quantPag);
			Thread.sleep(1500);
			Thread p = new Thread(new solicitaPagina(tabela, 1, quantPag, quantSolicitacao));
			
			// Adiciona as paginas do processo na memoria virtual
			mv.add(quantPag, 1);
			Thread.sleep(1500);
			System.out.println(mv);
			
			System.out.println("\nIniciando simulação...");
			Thread.sleep(1500);
			p.start();
			
			p.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("==================================================================");
		System.out.println("\tPrograma finalizado. Resumo final:");
		System.out.println("==================================================================");
		System.out.println("Total de solicitacoes respondidas: " + tabela.getHistorico()[0]);
		System.out.println("Solicitacoes encontradas na memoria principal: " + tabela.getHistorico()[3]);
		System.out.println("Solicitacoes adicionadas a memoria principal sem substituição: " + tabela.getHistorico()[1]);
		System.out.println("Solicitacoes adicionadas a memoria principal atraves de substituição: " + tabela.getHistorico()[2]);
		System.out.println("==================================================================");
	}
}
