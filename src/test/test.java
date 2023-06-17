package test;

import recursos.*;
import contenedores.*;

public class test {

	public static void main(String[] args) {
		/*ListaDoubles lista = new ListaDoubles();
		ListaDoubles listaDos = new ListaDoubles();
		lista.agregar(5.0);
		lista.agregar(6.0);
		lista.agregar(5.4);
		
		MatrizDoubles m = new MatrizDoubles(2,3);
		m.mostrarMatriz();
		
		m.cargarFila(0, lista);
		listaDos.agregar(7.0);
		listaDos.agregar(4.6);
		listaDos.agregar(6.6);
		m.cargarFila(1, listaDos);
		m.mostrarMatriz();
		
		System.out.println("");
		lista=m.penalizacionFilas();
		listaDos=m.penalizacionColumnas();
		lista.mostrarLista();
		System.out.println("");
		listaDos.mostrarLista();
		
		System.out.println("");
		m.eliminarColumna(0);
		m.mostrarMatriz();
		
		System.out.println("");
		m.eliminarFila(1);
		m.mostrarMatriz();
		
		System.out.println("");
		m.eliminarFila(1);
		m.mostrarMatriz();
		
		m.eliminarColumna(2);
		
		m.agregarFilaNula();
		m.mostrarMatriz();
		m.agregarColumnaNula();
		m.mostrarMatriz();*/
		
		MatrizModelo modelo = new MatrizModelo(2,3);
		modelo.cargar();
		
		modelo.mostrarVogel();
		
		modelo.vogel();
		
		modelo.mostrarVogel();
		
		System.out.println("Costo total= " + modelo.calcularCosto());
	}

}
