package recursos;

public class Tupla {
	protected Object a;
	protected Object b;
	
	public Tupla() {
		a=null;
		b=null;
	}
	
	public Tupla(Object a, Object b) {
		this.setA(a);
		this.setB(b);
	}
	
	public void setA(Object a) {
		this.a=a;
	}
	
	public void setB(Object b) {
		this.b=b;
	}
	
	public Object getA() {
		return a;
	}
	
	public Object getB() {
		return b;
	}
}
