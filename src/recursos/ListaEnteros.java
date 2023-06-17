package recursos;

import java.util.ArrayList;

public class ListaEnteros implements OperacionesLista{
	
	protected ArrayList<Integer> lista;
	
	public ListaEnteros() {
		this.lista = new ArrayList<>();
	}
	
	public int sumaLista() {
		if(this.tamanio()>0) {
			if(this.tamanio()==1) {
				return (int)this.obtener(0);
			}else {
				int acumulador=0;
		
				for(int i=0;i<this.tamanio();i++) {
					acumulador = acumulador + (int)this.lista.get(i);
				}
		
				return acumulador;
			}
		}else {
			return 0;
		}
	}

	@Override
	public void agregar(Object o) {
		this.lista.add((int)o);
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
			this.lista.set(i, (int)o);
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
	
	public Tupla ultimoNoCeroLista() {
		boolean bandera=false;
		int i=0;
		while(i<this.tamanio() && (int)this.obtener(i)==0) {
			i++;
		}
		
		if(i<this.tamanio()) {
			if((int)this.obtener(i)==this.sumaLista()) {
				bandera=true;
			}
		}
		
		if(!bandera) {
			i=-1;
		}
		
		return new Tupla(bandera,i);
	}
}
