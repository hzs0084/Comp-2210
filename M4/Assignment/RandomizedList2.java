import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Hemant Sherawat
 * 
 */
 
public class RandomizedList2<T> implements RandomizedList<T> {
   private T[] elements;
   private int size;
   private static final int DEFAULT_SIZE = 5;
  
   //@SuppressWarnings("unchecked")
   
   public RandomizedList2() {
      size = 0;
      elements = (T[]) new Object[DEFAULT_SIZE]; 
   }
   
   public int size() {
      return size;
   }
   
   public boolean isEmpty() {
      return size == 0;
   }
   
   public Iterator<T> iterator() {
      itr i = new itr(elements, size);
      return i;
   }
   
   public void add(T element) {
      if (element == null)
         throw new IllegalArgumentException();
         
      if (size == elements.length)
         resize(elements.length * 2);
         
      elements[size] = element;
      size = size + 1;
   }
   
   public T remove() {
      if (size == 0)
         return null;
      
      int i = new Random().nextInt(size);
      
      T removed = elements[i];
      elements[i] = elements[size - 1];
      elements[size - 1] = null;
      size = size - 1;
      
      if (size > 0 && size < elements.length / 4)
         resize(elements.length / 2);
      
      return removed;
   }
   
   public T sample() {
      if (size == 0)
         return null;
         
      Random r = new Random();
      int i = r.nextInt(size);
      return elements[i];  
   }
   
   private void resize(int capacity) {
      T[] a = (T[]) new Object[capacity];
      for (int i = 0; i < size(); i ++) {
         a[i] = elements[i];
      }
      elements = a;
   }
   
   public class itr<T> implements Iterator<T> {
      private T[] objects;
      private int length;
      
      public itr(T[] elements, int sizeIn) {
         objects = elements;
         length = sizeIn;
      }
      
      public boolean hasNext() {
         return (length > 0);
      }
      
      public T next() {
         if (!hasNext())
            throw new NoSuchElementException();
         
         Random r = new Random();
         int i = r.nextInt(length);
         T next = objects[i];
         
         if (i != (length - 1)) {
            objects[i] = objects[length - 1];
            objects[length - 1] = next;
         }
         
         length--;
         
         return next;   
      }
      
      public void remove() {
         throw new UnsupportedOperationException();
      }
   
   }            

}