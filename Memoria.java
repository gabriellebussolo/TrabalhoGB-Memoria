
public class Memoria {

	private String[] memoria;
	private int numElementos;
	private boolean isEmpty;
	private boolean isFull;

	public Memoria(int tam) {
		memoria = new String[tam];
		numElementos = 0;
		isEmpty = true;
		isFull = false;
	}

	public String[] getMemoria() {
		return memoria;
	}

	public int getNumElementos() {
		return numElementos;
	}

	public void setNumElementos(int numElementos) {
		this.numElementos = numElementos;
	}

	public boolean isEmpty() {
		if(this.numElementos > 0)
			return false;
		return true;
	}

	public boolean isFull() {
		if(this.numElementos == this.memoria.length)
			return true;
		return false;
	}

}
