package list;

public class MyLinkedList<E> {
	private Node<E> head, tail;

	protected int size = 0;

	private MyListIterator<E> myListIterator = new MyListIterator<>();
	
	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public int lastIndeOf(E e) {
		return 0;
	}

	public MyLinkedList() {
		this.myListIterator  = myListIterator;
	}
	
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e);
		if (tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			tail = tail.next;
		}
		size++;
	}
	
	public E removeFirst() {
		if (size == 0)
			return null;
		else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> temp = head;
			head = head.next;
			size--;
			return temp.element;
		}
	}
	
	public E removeLast() {
		if (size == 0)
			return null;
		else if (size == 1) {
			Node<E> temp = head;
			head = tail = null;
			size = 0;
			return temp.element;
		} else {
			Node<E> current = head;
			for (int i = 0; i < size - 2; i++) {
				current = current.next;
			}
			Node<E> temp = tail;
			tail = current;
			tail.next = null;
			size--;
			return temp.element;
		}
	}

	public E remove(int index) {
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return removeFirst();
		else if (index == size - 1)
			return removeLast();
		else {
			Node<E> previous = head;
			for (int i = 1; i < index; i++) {
				previous = previous.next;
			}
			Node<E> current = previous.next;
			previous.next = current.next;
			size--;
			return current.element;
		}
	}

	public E get(int index) {
		if (index < 0 || index >= size)
			return null;
		else if (index == 0)
			return head.element;
		else if (index == size - 1)
			return tail.element;
		else {
			Node<E> current = head;
			for (int i = 1; i < index; i++)
				current = current.next;
			Node<E> temp = current.next;
			return temp.element;
		}
	}

	public MyListIterator<E> myListIterator() {
		this.myListIterator.setIndex(0);
		this.myListIterator.setMyLinkedList(this);
		return this.myListIterator;
	}
}

class Node<E> {
	E element;
	Node<E> next;

	public Node(E e) {
		element = e;
	}
}
