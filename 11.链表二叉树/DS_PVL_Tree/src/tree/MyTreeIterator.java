package tree;

public interface MyTreeIterator<E> {
    boolean hasNext();
    E next();
	void remove();
}
