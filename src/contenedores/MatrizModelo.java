package contenedores;

import recursos.*;
import java.util.ArrayList;

public class MatrizModelo extends MatrizDoubles{
	protected ListaEnteros oferta;
	protected ListaEnteros demanda;
	protected ArrayList<Tupla> resultado;
	protected ArrayList<String> origenes;
	protected ArrayList<String> destinos;
	
	public MatrizModelo(int filas, int columnas) {
		super(filas,columnas);
		this.oferta=new ListaEnteros();
		this.demanda=new ListaEnteros();
		this.resultado=new ArrayList<>();
		this.origenes=new ArrayList<>();
		this.destinos=new ArrayList<>();
	}
	
	public void cargar() {
		this.origenes.add("Phoenix");
		this.origenes.add("Atlanta");
		this.destinos.add("San Antonio");
		this.destinos.add("Hot Springs");
		this.destinos.add("Sioux Falls");
		
		this.oferta.agregar(400);
		this.oferta.agregar(500);
		
		this.demanda.agregar(200);
		this.demanda.agregar(400);
		this.demanda.agregar(300);
		
		ListaDoubles lista = new ListaDoubles();
		ListaDoubles listaDos = new ListaDoubles();
		lista.agregar(5.0);
		lista.agregar(6.0);
		lista.agregar(5.4);
		listaDos.agregar(7.0);
		listaDos.agregar(4.6);
		listaDos.agregar(6.6);
		
		this.cargarFila(0, lista);
		this.cargarFila(1, listaDos);
	}
	
	public void vogel() {
		int totalOferta = this.oferta.sumaLista();
		int totalDemanda = this.demanda.sumaLista();
		Tupla mayorPFila = new Tupla();
		Tupla mayorPColumna = new Tupla();
		int h,k;
		
		if(totalOferta>totalDemanda) {
			this.agregarDestino(totalOferta-totalDemanda);
		}else if(totalDemanda>totalOferta) {
			this.agregarOrigen(totalDemanda-totalOferta);
		}
		
		MatrizDoubles aux = this.copiarMatriz();
		
		while(!this.detenerCiclo()) {
			mayorPFila = aux.penalizacionFilas().determinaMayor();
			mayorPColumna = aux.penalizacionColumnas().determinaMayor();
			
			if((double)mayorPFila.getA()>=(double)mayorPColumna.getA()) {
				h = (int)mayorPFila.getB();
				k = (int)aux.obtenerFila(h).determinaMenor().getB();
				this.asignaValor(h,k);
				aux.mostrarMatriz();
			}else {
				k = (int)mayorPColumna.getB();
				h = (int)aux.obtenerColumna(k).determinaMenor().getB();
				this.asignaValor(h,k);
				aux.mostrarMatriz();
			}
			
			if((int)this.oferta.obtener(h)==0) {
				aux.modificarFila(h, -1.0);
			}
			
			if((int)this.demanda.obtener(k)==0) {
				aux.modificarColumna(k, -1.0);
			}
		}
		aux.mostrarMatriz();
		Tupla temp = new Tupla();
		temp = this.demanda.ultimoNoCeroLista();
		if((boolean)temp.getA()) {
			for(int i=0;i<this.getFilas();i++) {
				if((int)this.oferta.obtener(i)!=0) {
					this.asignaValor(i, (int)temp.getB());
				}
			}
		}else {
			temp = this.oferta.ultimoNoCeroLista();
			for(int i=0;i<this.getColumnas();i++) {
				if((int)this.demanda.obtener(i)!=0) {
					this.asignaValor((int)temp.getB(), i);
				}
			}
		}
	}
	
	public void mostrarVogel() {
		Tupla aux = new Tupla();
		String origen,destino;
		if(this.matrizSatisfecha()) {
			for(int i=0;i<this.resultado.size();i++) {
				aux = (Tupla)this.resultado.get(i).getA();
				origen = this.origenes.get((int)aux.getA());
				destino = this.destinos.get((int)aux.getB());
				System.out.println("Se envia desde " + origen + " " + this.resultado.get(i).getB() + " Unidades " + "hacia " + destino);
			}
		}else {
			System.out.println("Todavia no ejecuto vogel");
		}
	}
	
	public double calcularCosto() {
		double r=0;
		Tupla aux = new Tupla();
		
		this.mostrarMatriz();
		for(int i=0;i<this.resultado.size();i++) {
			aux = (Tupla)this.resultado.get(i).getA();
			r = r + this.getValor((int)aux.getA(), (int)aux.getB())*(int)this.resultado.get(i).getB();
		}
		
		return r;
	}
	
	public ArrayList<Tupla> getResultado(){
		if(this.matrizSatisfecha()) {
			return this.resultado;
		}else {
			System.out.println("Todavia no ejecuto vogel");
			return null;
		}
	}
	
	public void agregarDestino(int valor) {
		this.agregarColumnaNula();
		this.demanda.agregar(valor);
		this.destinos.add("Ficticio");
	}
	
	public void agregarOrigen(int valor) {
		this.agregarFilaNula();
		this.oferta.agregar(valor);
		this.origenes.add("Ficticio");
	}
	
	public boolean detenerCiclo() {
		boolean bandera=false;
		
		int i=0;
		while(i<this.demanda.tamanio() && (int)this.demanda.obtener(i)==0) {
			i++;
		}
		
		if(i<this.demanda.tamanio()) {
			if((int)this.demanda.obtener(i)==this.demanda.sumaLista()) {
				bandera=true;
			}
		}
		
		int j=0;
		while(j<this.oferta.tamanio() && (int)this.oferta.obtener(j)==0) {
			j++;
		}
		
		if(j<this.oferta.tamanio()) {
			if((int)this.oferta.obtener(j)==this.oferta.sumaLista()) {
				bandera=true;
			}
		}
		
		return bandera;
	}
	
	public boolean matrizSatisfecha() {
		boolean bandera=true;
		int i=0;
		while(i<this.demanda.tamanio() && bandera) {
			if((int)this.demanda.obtener(i)!=0) {
				bandera=false;
			}
			i++;
		}
		
		return bandera;
	}
	
	private void asignaValor(int i, int j) {
		if((int)this.oferta.obtener(i)>=(int)this.demanda.obtener(j)) {
			this.resultado.add(new Tupla(new Tupla(i,j),(int)this.demanda.obtener(j)));
			this.oferta.modificar(i, (int)this.oferta.obtener(i)-(int)this.demanda.obtener(j));
			this.demanda.modificar(j, 0);
		}else {
			this.resultado.add(new Tupla(new Tupla(i,j),(int)this.oferta.obtener(i)));
			this.demanda.modificar(j, (int)this.demanda.obtener(j)-(int)this.oferta.obtener(i));
			this.oferta.modificar(i, 0);
		}
	}
	
}
