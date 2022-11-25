
public class MemoriaPrincipal extends Memoria {
	
	private int topo;
	
	public MemoriaPrincipal(int tam) {
		super(tam);
		topo = 0;
	}

	// procura o endereco na memoria principal (MMU achou na tabela de paginas)
	public String search(int frame) {
		if (this.isEmpty()) {
			System.out.println("Memoria vazia!");
			return null;

		} else {
			if (this.getMemoria()[frame] != null)
				return this.getMemoria()[frame];
			System.out.printf("Frame %s esta vazio!\n", frame);
			return null;
		}
	}

	// Algoritmo FIFO (first in, first out)
	public int substituicao(String pagProcesso) {
		int frameSubstituido = topo;
		
		this.getMemoria()[frameSubstituido] = pagProcesso;
		
		//atualiza topo
		if (topo == this.getMemoria().length-1)
			topo = 0;
		else
			topo++;
				
		return frameSubstituido;
	}

	// adiciona uma pagina do processo que esta na memoria virtual, na memoria principal
	public int add(String pagProcesso) {
		this.getMemoria()[this.getNumElementos()] = pagProcesso;

		String[] separada = pagProcesso.split("-");
		System.out.printf("Pagina %s do processo %s foi adicionada ao frame %s da memoria principal.\n",
					separada[1], separada[0], this.getNumElementos());

		this.setNumElementos(getNumElementos() + 1);
			
		return getNumElementos()-1;
	}
	
	@Override
	public String toString() {
		String retorno = "============== Memoria Principal (Frame | conteudo) ==============\n";	
		
		int[] linha = new int[2];
		
		for(int i = 0; i < this.getMemoria().length/2; i++) {
			linha[0] = i;
			linha[1] = i + this.getMemoria().length/2;
			
			for(int j = 0; j < linha.length; j++) {
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
