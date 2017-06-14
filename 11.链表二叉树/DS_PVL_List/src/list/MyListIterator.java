package list;

public class MyListIterator<E> {
	private MyLinkedList<E> myLinkedList;
	private int index =0;
	public void setIndex(int index) {
		this.index = index;
	}

	public MyLinkedList<E> getMyLinkedList() {
		return myLinkedList;
	}

	public void setMyLinkedList(MyLinkedList myLinkedList) {
		this.myLinkedList = myLinkedList;
	}

	public boolean hasNext() {
		if(myLinkedList.isEmpty()||(index>=myLinkedList.size())) {
			return false;
		} else {
			return true;
		}
	}

	public E next() {
		E e = myLinkedList.get(index);
		index++;
		return e;
	}
}
