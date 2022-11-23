
public class MemoriaVirtual extends Memoria {

	public MemoriaVirtual(int tam) {
		super(tam);
	}

	// Adiciona as paginas do processo na memoria virtual
	public void add(int quantPag, int numProc) {
		int cont = 1;

		// verifica se ha quantidade de paginas necessarias para esse processo
		if (this.getNumElementos() + quantPag <= this.getMemoria().length) {
			int limite = this.getNumElementos() + quantPag;

			for (int i = this.getNumElementos(); i < limite; i++) {

				this.getMemoria()[i] = numProc + "-" + cont; // NumProcesso-NumDaPaginaDoProcesso

				this.setNumElementos(this.getNumElementos() + 1);
				cont++;
			}
		} else
			System.out.printf("Nao ha paginas suficientes na memoria para alocar as %d paginas do processo %d.\n",
					quantPag, numProc);

	}

	public int search(String solicitacao) {
		for (int i = 0; i < this.getNumElementos(); i++)
			if (this.getMemoria()[i].equals(solicitacao))
				return i;
		return -1;
	}

	@Override
	public String toString() {
		String retorno = "============== Memoria Virtual (Pagina | conteudo) ===============\n";

		int[] linha = new int[4];

		for (int i = 0; i < this.getMemoria().length / 4; i++) {
			linha[0] = i;
			linha[1] = i + this.getMemoria().length / 4;
			linha[2] = i + this.getMemoria().length / 2;
			linha[3] = i + (this.getMemoria().length / 4 + this.getMemoria().length / 2);

			for (int j = 0; j < linha.length; j++) {
				if (this.getMemoria()[linha[j]] == null)
					retorno += linha[j] + "| \t\t";
				else
					retorno += linha[j] + "|" + this.getMemoria()[linha[j]] + "\t\t";
			}
			retorno += "\n";
		}

		return retorno;
	}

}
