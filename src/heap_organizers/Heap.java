package heap_organizers;

import java.util.ArrayList;

/**
 * This class if for storing the Songs and Tasks in the Jukebox and Tasklist
 * classes. Max heap so keeps the biggest value at the root.
 * 
 * @author Ryan
 *
 * @param <T>
 *            Anything that implements comparable, probably Task or Song
 */
public class Heap<T extends Comparable<T>> {

	/**
	 * Stores the values in the heap.
	 */
	private ArrayList<T> heap;

	/**
	 * Creates a new heap.
	 */
	public Heap() {
		heap = new ArrayList<T>();
	}

	/**
	 * Gets the size of the heap.
	 * 
	 * @return An int for the size of the heap.
	 */
	public int getSize() {
		return heap.size();
	}

	/**
	 * Checks if the heap is empty.
	 * 
	 * @return True if heap is empty, false if not.
	 */
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	/**
	 * Gets the location of a node's parent.
	 * 
	 * @param i
	 *            The node whose parent to get.
	 * @return An int for the parent's location.
	 */
	public int getPLoc(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Gets the location of a node's left child.
	 * 
	 * @param i
	 *            The node whose left child to get.
	 * @return An int for the left child's location.
	 */
	public int getLCLoc(int i) {
		return 2 * i + 1;
	}

	/**
	 * Gets the location of a node's right child.
	 * 
	 * @param i
	 *            The node whose right child to get.
	 * @return An int for the right child's location.
	 */
	public int getRCLoc(int i) {
		return 2 * i + 2;
	}

	/**
	 * Gets the object at the given index.
	 * 
	 * @param i
	 *            The index to get the object at.
	 * @return The object of type T.
	 */
	public T get(int i) {
		if (heap.get(i) == null) {
			System.out.println("Item does not exist.");
			return null;
		} else
			return heap.get(i);
	}

	/**
	 * Adds a new object to the heap.
	 * 
	 * @param t
	 *            The object of type T to add.
	 */
	public void add(T t) {
		heap.add(null);
		int index = heap.size() - 1;
		while (index > 0 && get(getPLoc(index)).compareTo(t) < 0) {
			heap.set(index, get(getPLoc(index)));
			index = getPLoc(index);
		}
		heap.set(index, t);
	}

	/**
	 * Removes the object at the root of the heap. Adjusts other nodes to
	 * maintain heap structure
	 * 
	 * @return The object that was removed.
	 */
	public T removeMax() {
		T max = heap.get(0);
		int index = heap.size() - 1;
		T last = heap.remove(index);
		if (index > 0) {
			heap.set(0, last);
			T root = heap.get(0);
			int end = heap.size() - 1;
			index = 0;
			boolean done = false;
			while (!done) {
				if (getLCLoc(index) <= end) {// left exists
					T child = get(getLCLoc(index));
					int childLoc = getLCLoc(index);
					if (getRCLoc(index) <= end) {// rt exists
						if (get(getRCLoc(index)).compareTo(child) > 0) {
							child = get(getRCLoc(index));
							childLoc = getRCLoc(index);
						}
					}
					if (child.compareTo(root) > 0) {
						heap.set(index, child);
						index = childLoc;
					} else {
						done = true;
					}
				} else {// no children
					done = true;
				}
			}
			heap.set(index, root);
		}
		return max;
	}

	/**
	 * Prints the objects in the heap in index order.
	 */
	public void printHeap() {
		for (int i = 0; i < heap.size(); i++) {
			System.out.println(heap.get(i) + " ");
		}
		System.out.println();
	}

	/**
	 * Prints the objects in the heap in comparable order.
	 */
	public void printSorted() {
		ArrayList<T> temp = new ArrayList<T>(heap);
		for (int i = 0; i < temp.size(); i++) {
			int lowest = i;
			for (int j = i + 1; j < temp.size(); j++) {
				if (temp.get(j).compareTo(temp.get(lowest)) > 0) {
					lowest = j;
				}
			}
			T swap = temp.get(i);
			temp.set(i, temp.get(lowest));
			temp.set(lowest, swap);
		}

		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i) + " ");
		}
		System.out.println();
	}
}
