package recursos;

import java.util.ArrayList;

public class MatrizDoubles {
	private int filas;
	private int columnas;
	protected ArrayList<ListaDoubles> matriz;
	
	public MatrizDoubles(int filas, int columnas) {
		this.setFilas(filas);
		this.setColumnas(columnas);
		this.matriz = new ArrayList<>();
		this.iniciaMatriz();
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	
	public void iniciaMatriz() {
		ListaDoubles aux = new ListaDoubles();
		for(int i=0;i<this.filas;i++) {
			for(int j=0;j<this.columnas;j++) {
				aux.agregar(0.0);
			}
			this.matriz.add(aux);
		}
	}
	
	public void mostrarMatriz() {
		System.out.println();
		for(int i=0;i<this.filas;i++) {
			for(int j=0;j<this.columnas;j++) {
				System.out.print((double)this.matriz.get(i).obtener(j) +  " ");
			}
			System.out.println();
		}
	}
	
	public void cargarFila(int i, ListaDoubles lista) {
		if(i<this.filas) {
			this.matriz.set(i, lista);
		}else {
			System.out.println("Error. el indice ingresado es mayor a la cantidad maxima de filas");
		}
	}
	
	public void cargarColumna(int j, ListaDoubles lista) {
		if(j<this.columnas) {
			for(int i=0;i<this.filas;i++) {
				this.matriz.get(i).modificar(j, lista.obtener(i));
			}
		}else {
			System.out.println("Error. el indice ingresado es mayor a la cantidad maxima de filas");
		}
	}
	
	public ListaDoubles penalizacionFilas() {
		ListaDoubles penalizacion = new ListaDoubles();
		ListaDoubles aux = new ListaDoubles();
		
		for(int i=0;i<this.filas;i++) {
			aux = this.matriz.get(i).copiarLista();
			aux.depurarLista(-1.0);
			if(aux.tamanio()!=0) {
				penalizacion.agregar(aux.diferenciaMenores());
			}else {
				penalizacion.agregar(-1.0);
			}
		}
		
		return penalizacion;
	}
	
	public ListaDoubles penalizacionColumnas() {
		ListaDoubles penalizacion = new ListaDoubles();
		ListaDoubles aux = new ListaDoubles();
		
		for(int i=0;i<this.columnas;i++) {
			aux = this.obtenerColumna(i);
			aux.depurarLista(-1.0);
			if(aux.tamanio()!=0) {
				penalizacion.agregar(aux.diferenciaMenores());
			}else {
				penalizacion.agregar(-1.0);
			}
		}
		
		return penalizacion;
	}
	
	public ListaDoubles obtenerFila(int fila) {
		return this.matriz.get(fila);
	}
	
	public ListaDoubles obtenerColumna(int columna) {
		ListaDoubles aux = new ListaDoubles();
		
		for(int i=0;i<this.filas;i++) {
			aux.agregar(this.matriz.get(i).obtener(columna));
		}
		
		return aux;
	}
	
	public void eliminarFila(int fila) {
		if(fila<this.filas) {
			for(int i=fila;i<this.filas-1;i++) {
				this.matriz.set(i, this.matriz.get(i));
			}
			this.filas--;
		}else {
			System.out.println("Error. El indice fila es mayor a la cantidad de filas de la lista");
		}
	}
	
	public void eliminarColumna(int columna) {
		if(columna<this.filas) {
			for(int i=0;i<this.filas;i++) {
				this.matriz.get(i).eliminar(columna);
			}
			this.columnas--;
		}else {
			System.out.println("Error. El indice columna es mayor a la cantidad de filas de la lista");
		}
	}
	
	public MatrizDoubles copiarMatriz() {
		MatrizDoubles copia = new MatrizDoubles(this.filas,this.columnas);
		
		for(int i=0;i<this.filas;i++) {
			copia.cargarFila(i, this.obtenerFila(i).copiarLista());
		}
		
		return copia;
	}
	
	public void agregarFilaNula() {
		ListaDoubles aux = new ListaDoubles();
		for(int i=0;i<this.columnas;i++) {
			aux.agregar(0.0);
		}
		this.matriz.add(this.filas, aux);
		this.filas++;
	}
	
	public void agregarColumnaNula() {
		for(int i=0;i<this.filas;i++) {
			this.matriz.get(i).agregar(0.0);
		}
		this.columnas++;
	}
	
	public void modValor(int i, int j, double valor) {
		if(i<this.filas && j<this.columnas) {
			this.matriz.get(i).modificar(j, valor);
		}else {
			System.out.println("Error. Un indice o ambos no corresponden a la matriz");
		}
	}
	
	public double getValor(int i, int j) {
		if(i<this.filas && j<this.columnas) {
			return (double)this.matriz.get(i).obtener(j);
		}else {
			System.out.println("Error. Un idice o ambos no corresponden a la matriz");
			return 0;
		}
	}
	
	public void modificarFila(int p, double valor) {
		ListaDoubles aux = new ListaDoubles();
		
		for(int i=0;i<this.columnas;i++) {
			aux.agregar(valor);
		}
		
		this.cargarFila(p, aux);
	}
	
	public void modificarColumna(int p, double valor) {
		ListaDoubles aux = new ListaDoubles();
		
		for(int i=0;i<this.filas;i++) {
			aux.agregar(valor);
		}
		
		this.cargarColumna(p, aux);
	}
}
