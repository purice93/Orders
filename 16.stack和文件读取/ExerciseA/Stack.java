import java.util.Arrays;
import java.util.Vector;

public class Stack<E> extends Vector<E>{
	private Object[] elements;
	private int stackSize;
	private int size;

	public Stack() {
		this.stackSize=10;
		elements = new Object[stackSize]; 
	}

	public Stack(int size) {
		this.stackSize=size;
		elements = new Object[stackSize];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == stackSize;
	}

	public E peek() {
		if (isEmpty()) {
			return null;
		}
		return (E) elements[size - 1];
	}

	public E pop() {
		E e = peek();
		elements[size - 1] = null;
		size--;
		return e;
	}

	public void push(E item) {
		ensureCapacity(size + 1);
		elements[size++] = item;
	}

	public void ensureCapacity(int size) {
		int len = elements.length;
		if (size > len) {
			int newLen = (len * 3) / 2 + 1;
			elements = Arrays.copyOf(elements, newLen);
		}
	}

}
