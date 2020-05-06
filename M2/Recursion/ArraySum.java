/**
 * Provides a sum function on arrays.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-26
 */
public class ArraySum {

	/** Returns the sum of values in the given array. */
   public static int sum(int[] a, int left, int right) {
      int result;
      
      if (right == 0)
         result = a[right];
      else   
         result = a[right] + sum(a, left, right - 1);
      
      return result;
   }
   
   /** Execution of main. */
   public static void main(String[] args) {
      int[] a = {1, 3, 5, 7, 9};
      int sum = sum(a, 0, a.length - 1);
      System.out.print(sum);
   }


}
