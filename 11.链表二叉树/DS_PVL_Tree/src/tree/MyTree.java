package tree;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyTree<E> {
	private Entry<E> root = null;
	private int size = 0;

	public MyTree() {
	}

	public int size() {
		return size;
	}

	private void rotateLeft(Entry<E> p) {
		if (p != null) {
			Entry<E> r = p.right;
			p.right = r.left;
			if (r.left != null)
				r.left.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}

	private void rotateRight(Entry<E> p) {
		if (p != null) {
			Entry<E> l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			l.right = p;
			p.parent = l;
		}
	}

	public boolean add(E element) {
		Entry<E> t = root;
		if (t == null) {
			root = new Entry<E>(element, null);
			size = 1;
			return true;
		}
		int cmp;
		Entry<E> parent;
		do {
			parent = t;
			cmp = element.toString().compareTo(t.element.toString());
			if (cmp < 0) {
				t = t.left;
			} else if (cmp > 0) {
				t = t.right;
			} else {
				return false;
			}
		} while (t != null);

		Entry<E> child = new Entry(element, parent);
		if (cmp < 0) {
			parent.left = child;

		} else {
			parent.right = child;
		}
		while (parent != null) {
			cmp = element.toString().compareTo(parent.element.toString());
			if (cmp < 0) {
				parent.balance++;
			} else {
				parent.balance--;
			}
			if (parent.balance == 0) {
				break;
			}
			if (Math.abs(parent.balance) == 2) {
				fixAfterInsertion(parent);
				break;
			}
			parent = parent.parent;
		}
		size++;
		return true;
	}

	private void fixAfterInsertion(Entry<E> p) {
		if (p.balance == 2) {
			leftBalance(p);
		}
		if (p.balance == -2) {
			rightBalance(p);
		}
	}

	private boolean leftBalance(Entry<E> t) {
		boolean heightLower = true;
		Entry<E> l = t.left;
		switch (l.balance) {
		case LH:
			t.balance = l.balance = EH;
			rotateRight(t);
			break;
		case RH:
			Entry<E> rd = l.right;
			switch (rd.balance) {
			case LH:
				t.balance = RH;
				l.balance = EH;
				break;
			case EH:
				t.balance = l.balance = EH;
				break;
			case RH:
				t.balance = EH;
				l.balance = LH;
				break;
			}
			rd.balance = EH;
			rotateLeft(t.left);
			rotateRight(t);
			break;
		case EH:
			l.balance = RH;
			t.balance = LH;
			rotateRight(t);
			heightLower = false;
			break;
		}
		return heightLower;
	}

	private boolean rightBalance(Entry<E> t) {
		boolean heightLower = true;
		Entry<E> r = t.right;
		switch (r.balance) {
		case LH:
			Entry<E> ld = r.left;
			switch (ld.balance) {
			case LH:
				t.balance = EH;
				r.balance = RH;
				break;
			case EH:
				t.balance = r.balance = EH;
				break;
			case RH:
				t.balance = LH;
				r.balance = EH;
				break;
			}
			ld.balance = EH;
			rotateRight(t.right);
			rotateLeft(t);
			break;
		case RH:
			t.balance = r.balance = EH;
			rotateLeft(t);
			break;
		case EH:
			r.balance = LH;
			t.balance = RH;
			rotateLeft(t);
			heightLower = false;
			break;
		}
		return heightLower;
	}

	private Entry<E> getEntry(Object element) {
		Entry<E> tmp = root;
		Comparator<? super E> e = (Comparator<? super E>) element;
		int c;
		while (tmp != null) {
			c = e.toString().compareTo(tmp.element.toString());
			if (c == 0) {
				return tmp;
			} else if (c < 0) {
				tmp = tmp.left;
			} else {
				tmp = tmp.right;
			}
		}
		return null;
	}

	public boolean remove(Object o) {
		Entry<E> e = getEntry(o);
		if (e != null) {
			deleteEntry(e);
			return true;
		}
		return false;
	}

	private void deleteEntry(Entry<E> p) {
		size--;
		if (p.left != null && p.right != null) {
			Entry<E> s = successor(p);
			p.element = s.element;
			p = s;
		}
		Entry<E> replacement = (p.left != null ? p.left : p.right);

		if (replacement != null) {
			replacement.parent = p.parent;
			if (p.parent == null)
				root = replacement;
			else if (p == p.parent.left)
				p.parent.left = replacement;
			else
				p.parent.right = replacement;

			p.left = p.right = p.parent = null;

			fixAfterDeletion(replacement);

		} else if (p.parent == null) {
			root = null;
		} else {
			fixAfterDeletion(p);
			if (p.parent != null) {
				if (p == p.parent.left)
					p.parent.left = null;
				else if (p == p.parent.right)
					p.parent.right = null;
				p.parent = null;
			}
		}
	}

	static <E> Entry<E> successor(Entry<E> t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Entry<E> p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else {
			Entry<E> p = t.parent;
			Entry<E> ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	private void fixAfterDeletion(Entry<E> p) {
		boolean heightLower = true;
		Entry<E> t = p.parent;
		Comparator<? super E> e = (Comparator<? super E>) p.element;
		int cmp;
		while (t != null && heightLower) {
			cmp = e.toString().compareTo(t.element.toString());
			if (cmp >= 0) {
				t.balance++;
			} else {
				t.balance--;
			}
			if (Math.abs(t.balance) == 1) {
				break;
			}
			Entry<E> r = t;
			if (t.balance == 2) {
				heightLower = leftBalance(r);
			} else if (t.balance == -2) {
				heightLower = rightBalance(r);
			}
			t = t.parent;
		}
	}

	private static final int LH = 1;
	private static final int EH = 0;
	private static final int RH = -1;

	static class Entry<E> {
		E element;
		Entry<E> parent;
		Entry<E> left;
		Entry<E> right;
		int balance = EH;

		public Entry(E element, Entry<E> parent) {
			this.element = element;
			this.parent = parent;
		}

		public Entry() {
		}

		@Override
		public String toString() {
			return element + " BF=" + balance;
		}

	}

	private class BinarySortMyTreeIterator implements MyTreeIterator<E> {
		Entry<E> next;
		Entry<E> lastReturned;

		public BinarySortMyTreeIterator() {
			Entry<E> s = root;
			if (s != null) {
				while (s.left != null) {
					s = s.left;
				}
			}
			next = s;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public E next() {
			Entry<E> e = next;
			if (e == null)
				throw new NoSuchElementException();
			next = successor(e);
			lastReturned = e;
			return e.element;
		}

		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException();
			if (lastReturned.left != null && lastReturned.right != null)
				next = lastReturned;
			deleteEntry(lastReturned);
			lastReturned = null;
		}
	}

	public MyTreeIterator<E> myTreeIterator() {
		return new BinarySortMyTreeIterator();
	}

	private int treeHeight(Entry<E> p) {
		if (p == null) {
			return -1;
		}
		return 1 + Math.max(treeHeight(p.left), treeHeight(p.right));
	}
}
