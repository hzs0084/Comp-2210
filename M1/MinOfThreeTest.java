import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }


   @Test
   public void testMin1(){
   
      int expected = 5;
      int a = 10;
      int b = 10;
      int c = 5;
      MinOfThree.min1(a, b, c);
      assertEquals(expected, 5);
      
      int expected2 = 7;
      MinOfThree.min1(11, 8, 7);
      assertEquals(expected2, 7);
      
      int expected3 = 77;
      MinOfThree.min1(111, 234, 77);
      assertEquals(expected3, 77);
   }
   
   /**
   Test case for the second method
   */
   
   @Test
   public void testMin2(){
   
      int expected = 2;
      MinOfThree.min2(5, 3, 2);
      assertEquals(expected, 2);
   
      int expected2 = 2;
      MinOfThree.min2(6, 2, 9);
      assertEquals(expected2, 2);
   
      int expected3 = 77;
      MinOfThree.min2(111, 234, 77);
      assertEquals(expected3, 77);
   }
   
}
