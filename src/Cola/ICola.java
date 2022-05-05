
package Cola;

public interface ICola<T> {
	public boolean isFull();
	
	public boolean isEmpty();
	
	public void add(T obj);
	
	public void remove();
	
	public T peek();
}
