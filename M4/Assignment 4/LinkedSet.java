import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author YOUR NAME (you@auburn.edu)
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    public boolean isEmpty() {
        return (size == 0);
    }

    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    public boolean add(T element) {
        if (element == null) {
            return false;
        }

        Node newNode = new Node(element);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            Node current = front;
            while (current != null && current.element.compareTo(element) < 0) {
                current = current.next;
            }

            if (current != null && current.element.compareTo(element) == 0) {
                return false;
            }

            if (current == null) { // Insert at the end
                rear.next = newNode;
                newNode.prev = rear;
                rear = newNode;
            } else if (current == front) { // Insert at the front
                newNode.next = front;
                front.prev = newNode;
                front = newNode;
            } else { // Insert in the middle
                Node previous = current.prev;
                previous.next = newNode;
                newNode.prev = previous;
                newNode.next = current;
                current.prev = newNode;
            }
        }

        size++;
        return true;
    }

    public boolean remove(T element) {
        if (element == null || isEmpty()) {
            return false;
        }

        Node current = front;

        while (current != null) {
            if (current.element.compareTo(element) == 0) {
                if (current == front) {
                    front = front.next;
                    if (front != null) {
                        front.prev = null;
                    } else {
                        rear = null;
                    }
                } else if (current == rear) {
                    rear = rear.prev;
                    if (rear != null) {
                        rear.next = null;
                    } else {
                        front = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }

                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean contains(T element) {
        if (element == null) {
            return false;
        }

        Node current = front;
        while (current != null) {
            if (current.element.compareTo(element) == 0) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean equals(Set<T> s) {
        if (s == null || size != s.size()) {
            return false;
        }

        Iterator<T> it = s.iterator();
        for (T element : this) {
            if (!it.hasNext() || !element.equals(it.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(LinkedSet<T> s) {
        if (s == null || size != s.size()) {
            return false;
        }

        Node thisNode = this.front;
        Node thatNode = s.front;

        while (thisNode != null && thatNode != null) {
            if (!thisNode.element.equals(thatNode.element)) {
                return false;
            }
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        return true;
    }

    public Set<T> union(Set<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        for (T element : this) {
            result.add(element);
        }
        for (T element : s) {
            result.add(element);
        }
        return result;
    }

    public Set<T> union(LinkedSet<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node thisNode = this.front;
        Node thatNode = s.front;

        while (thisNode != null) {
            result.add(thisNode.element);
            thisNode = thisNode.next;
        }

        while (thatNode != null) {
            result.add(thatNode.element);
            thatNode = thatNode.next;
        }

        return result;
    }

    public Set<T> intersection(Set<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        for (T element : this) {
            if (s.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Set<T> intersection(LinkedSet<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node thisNode = this.front;

        while (thisNode != null) {
            if (s.contains(thisNode.element)) {
                result.add(thisNode.element);
            }
            thisNode = thisNode.next;
        }

        return result;
    }

    public Set<T> complement(Set<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        for (T element : this) {
            if (!s.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public Set<T> complement(LinkedSet<T> s) {
        LinkedSet<T> result = new LinkedSet<>();
        Node thisNode = this.front;

        while (thisNode != null) {
            if (!s.contains(thisNode.element)) {
                result.add(thisNode.element);
            }
            thisNode = thisNode.next;
        }

        return result;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    public Iterator<T> descendingIterator() {
        return new Iterator<T>() {
            private Node current = rear;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = current.element;
                current = current.prev;
                return element;
            }
        };
    }

    public Iterator<Set<T>> powerSetIterator() {
        // Power set contains 2^N subsets
        final int powerSetSize = (int) Math.pow(2, size);
        return new Iterator<Set<T>>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < powerSetSize;
            }

            @Override
            public Set<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                LinkedSet<T> subset = new LinkedSet<>();
                Node currentNode = front;
                int index = currentIndex;

                for (int i = 0; currentNode != null; i++) {
                    if ((index & 1) == 1) {
                        subset.add(currentNode.element);
                    }
                    currentNode = currentNode.next;
                    index >>= 1;
                }

                currentIndex++;
                return subset;
            }
        };
    }

    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    class Node {
        T element;
        Node next;
        Node prev;

        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
   
    }
}