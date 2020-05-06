import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Hemant SHerawat
 * @version
 */
 
public class DoubleEndedList2<T> implements DoubleEndedList<T> {
   private Node front;
   private int size;
   private Node last;
   
   @SuppressWarnings("unchecked")
   
   public DoubleEndedList2() {
      front = null;
      size = 0;
      last = null;
   }
   
   public int size() {
      return size;
   }
   
   public Iterator<T> iterator() {
      return new itr();
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public void addFirst(T element) {
      if (element == null) 
         throw new IllegalArgumentException();
         
      Node n = new Node(element);
      
      if (size == 0) {
         front = n;
         last = n;
      }
      
      else {
         n.next = front;
         front = n;
      }
      
      size = size + 1;
      
   }
   
   public T removeFirst() {
      if (size == 0)
         return null;
      
      T removed = front.element;
      front = front.next;
      size = size - 1;
   
      return removed;
   }
   
   public void addLast(T element) {
      if (element == null) 
         throw new IllegalArgumentException();
         
      Node n = new Node(element);
      n.element = element;
      
      if (size == 0) {
         front = n;
         last = n;
      }
      
      else {
         last.next = n;
         last = n;
      }
      
      size = size + 1;
   }
   
   public T removeLast() {
      if (size == 0)
         return null;
         
      else if (size == 1) {
         T removed = front.element;
         front = null;
         last = null;
         size--;
         return removed;
      }
      
      else {
         Node n = front;
         
         while (n.next.next != null)
            n = n.next;
            
         T removed = n.next.element;
         n.next = null;
         last = n;
         size = size - 1;
         return removed;
      }
   }
   
   private class Node {
      private T element;
      private Node next;
   
      public Node(T t) {
         element = t;
      }
   
      public Node(T t, Node n) {
         element = t;
         next = n;
      }
   }      
   
   private class itr implements Iterator<T> {
      private Node current = front;
   
      public T next() {
         if (!hasNext())
            throw new NoSuchElementException();
         
         T result = current.element;
         current = current.next;
         return result;
      }
   
      public boolean hasNext() {
         return current != null;
      }
   
      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}