package recursos;

import java.util.ArrayList;

public class ListaDoubles implements OperacionesLista{
	protected ArrayList<Double> lista;
	
	public ListaDoubles() {
		this.lista=new ArrayList<>();
	}

	@Override
	public void agregar(Object o) {
		this.lista.add((double)o);
	}

	@Override
	public Object obtener(int i) {
		if(i<this.tamanio()) {
			return this.lista.get(i);
		}else {
			System.out.println("Error. Indice mayor a la cantidad de elementos en la lista");
			return null;
		}
	}

	@Override
	public void modificar(int i, Object o) {
		if(i<this.tamanio()) {
			this.lista.set(i, (double)o);
		}else {
			System.out.println("Error. Indice mayor a la cantidad de elementos en la lista");
		}
	}

	@Override
	public int tamanio() {
		return this.lista.size();
	}

	@Override
	public void eliminar(int i) {
		if(i<this.tamanio()) {
			this.lista.remove(i);
		}else {
			System.out.println("Error. Indice mayor a la cantidad de elementos en la lista");
		}
	}
	
	public void mostrarLista() {
		for(int i=0;i<this.tamanio();i++) {
			System.out.print(this.lista.get(i) + " ");
		}
	}
	
	public Tupla determinaMayor() {
		Tupla mayor = new Tupla(this.lista.get(0),0);
		
		for(int i=0;i<this.tamanio();i++) {
			if((double)mayor.getA()<this.lista.get(i)) {
				mayor.setA(this.lista.get(i));
				mayor.setB(i);
			}
		}
		
		return mayor;
	}
	
	public Tupla determinaMenor() {
		Tupla menor = new Tupla(this.lista.get(0),0);
		
		for(int i=0;i<this.tamanio();i++) {
			if((double)menor.getA()>this.lista.get(i) && this.lista.get(i)!=-1.0) {
				menor.setA(this.lista.get(i));
				menor.setB(i);
			}
		}
		
		return menor;
	}
	
	public double diferenciaMenores() {
		double diferencia=0;
		ListaDoubles aux = new ListaDoubles();
		
		if(this.tamanio()>1) {
			double menor = (double)this.determinaMenor().getA();
			aux=this.copiarLista();
			aux.eliminar((int)this.determinaMenor().getB());
			diferencia = (double)aux.determinaMenor().getA() - menor;
		}else {
			diferencia = (double)this.obtener(0);
		}
		
		System.out.println(" " + diferencia);
		return diferencia;
	}
	
	public ListaDoubles copiarLista() {
		ListaDoubles copia = new ListaDoubles();
		
		for(int i=0;i<this.tamanio();i++) {
			copia.agregar(this.obtener(i));
		}
		
		return copia;
	}
	
	public void depurarLista(double valor) {
		int i=0;
		while(i<this.tamanio()) {
			if(this.lista.get(i)==-1.0) {
				this.eliminar(i);
			}else {
				i++;
			}
		}
	}
}
