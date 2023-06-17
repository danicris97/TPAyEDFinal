package recursos;

public interface OperacionesLista {
	
	public void agregar(Object o);
	public Object obtener(int i);
	public void modificar(int i, Object o);
	public int tamanio();
	public void eliminar(int i);
}
