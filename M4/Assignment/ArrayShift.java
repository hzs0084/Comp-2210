import java.util.Arrays;

/**
 * Exam question on shifting elements in arrays.
 *
 * @author YOUR NAME (YOU@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 */
public class ArrayShift {

   /*

   >>>>>>>>>>>>>>>>>> DISCUSSION <<<<<<<<<<<<<<<<<<

   In many ordered collections that are implemented based on arrays, a major
   issue is the cost associated with shifting elements to the right when
   adding and to the left when removing.

   For example, let's say that we're implementing a list that keeps its
   elements in natural order using an array using our familiar "left
   justified" strategy. If the array has capacity 10 and the list contains
   the first five odd integers, then the array would look like this:

       [1, 3, 5, 7, 9, _, _, _, _, _]

   Adding the value 4 to the list would require elements 5, 7, and 9 to be
   shifted to the right

       [1, 3, _, 5, 7, 9, _, _, _, _]

   to make room for the 4 to be added in natural order:

       [1, 3, 4, 5, 7, 9, _, _, _, _]

   This test question asks you to write the code to shift elements to the
   right in an array.


   >>>>>>>>>>>>>>>>>> INSTRUCTIONS <<<<<<<<<<<<<<<<

   1.  Read through this entire class to make sure you understand the context.
       The only method you must complete is the method named shiftRight below.
       The shiftRight method takes two parameters. The first (array) is the
       containing the elements to be shifted. The second (index) is the starting
       index of the shift. For example, to go from the original array above to
       the shifted version (the second illustration) we could call shiftRight(a, 2).

   2.  A sample main method is provided for you to illustrate the intended
       effect of calls to the shiftRight method.

   3.  Once you have read through all the provided source code, your task is
       to provide a correct implementation of the shiftRight method at the very
       bottom of this file.

   

    */


   /** Provides example calls to the shiftRight method. */
   public static void main(String[] args) {
   
      Integer[] a = {1, 3, 5, 7, 9, null, null, null, null, null};
      System.out.println(Arrays.toString(a));
      shiftRight(a, 2);
      System.out.println(Arrays.toString(a));
      // EXPECTED OUTPUT:
      // [1, 3, 5, 7, 9, null, null, null, null, null]
      // [1, 3, null, 5, 7, 9, null, null, null, null]
   
   
      a = new Integer[]{1, 3, 5, 7, 9, null, null, null, null, null};
      System.out.println(Arrays.toString(a));
      shiftRight(a, 0);
      System.out.println(Arrays.toString(a));
      // EXPECTED OUTPUT:
      // [1, 3, 5, 7, 9, null, null, null, null, null]
      // [null, 1, 3, 5, 7, 9, null, null, null, null]
   
   
      a = new Integer[]{1, 3, 5, 7, 9, null, null, null, null, null};
      System.out.println(Arrays.toString(a));
      shiftRight(a, 4);
      System.out.println(Arrays.toString(a));
      // EXPECTED OUTPUT:
      // [1, 3, 5, 7, 9, null, null, null, null, null]
      // [1, 3, 5, 7, null, 9, null, null, null, null]
   
   
      a = new Integer[]{1, 3, 5, 7, 9, null, null, null, null, null};
      System.out.println(Arrays.toString(a));
      shiftRight(a, 5);
      System.out.println(Arrays.toString(a));
      // EXPECTED OUTPUT:
      // [1, 3, 5, 7, 9, null, null, null, null, null]
      // [1, 3, 5, 7, 9, null, null, null, null, null]
   
   }


   public static void shiftRight(Object[] array, int index) {
   
      // FILL IN THE BODY OF THIS METHOD
   
   
   }

}
