import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Input parameters cannot be null");
        }

        int low = 0;
        int high = a.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparator.compare(key, a[mid]);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                result = mid;  // Found a match, but keep searching to the left
                high = mid - 1;
            }
        }
        return result;  // Return the first occurrence index, or -1 if not found
    }

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("Input parameters cannot be null");
        }

        int low = 0;
        int high = a.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparator.compare(key, a[mid]);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                result = mid;  // Found a match, but keep searching to the right
                low = mid + 1;
            }
        }
        return result;  // Return the last occurrence index, or -1 if not found
    }
}
