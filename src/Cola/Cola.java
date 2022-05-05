package Cola;

public class Cola<T> implements ICola<T> {
	class Nodo{
		T dato;
		Nodo sig;
	}
	
	private Nodo raiz;
	private Nodo ultimo;
	
	public Cola() {
		raiz = null;
		ultimo = null;
	}
	
	/* Metodo que comprueba si esta vacia la cola */
	public boolean isEmpty() {
		return(raiz == null);
	}
	
	/* Metodo que comprueba si esta llena la cola */
	public boolean isFull() {
		return(!isEmpty());
	}
	
	/* Metodo que añade un objeto a la cola */
	public void add(T elem) {
		Nodo nuevo = new Nodo();
		nuevo.dato = elem;
		
		if(isEmpty()) {
			ultimo = nuevo;
			raiz = nuevo;
		}else {
			ultimo.sig = nuevo;
			ultimo = nuevo;
		}
	}
	
	/* Metodo que elimina el siguiente elemento de la cola */
	public void remove() {
		if(!isEmpty()) {
			if(raiz == ultimo) {
				ultimo = null;
				raiz = null;
			}else {
				raiz = raiz.sig;
			}
		}else
			System.out.println("La cola esta vacia");
	}
	
	/* Metodo que deveulve el elemento de la cima */
	public T peek() {
		return raiz.dato;
	}



	
	
	
}
