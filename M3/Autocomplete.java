import java.util.Arrays;
import java.util.Comparator;

/**
 * Autocomplete.
 */
public class Autocomplete {

    private Term[] terms;

    /**
     * Initializes a data structure from the given array of terms.
     * This method throws a NullPointerException if terms is null.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null) {
            throw new NullPointerException("Terms array cannot be null");
        }
        this.terms = Arrays.copyOf(terms, terms.length);
        Arrays.sort(this.terms); // Sort terms lexicographically
    }

    /** 
     * Returns all terms that start with the given prefix, in descending order of weight. 
     * This method throws a NullPointerException if prefix is null.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null) {
            throw new NullPointerException("Prefix cannot be null");
        }

        // Create a comparator that compares only by the prefix
        Comparator<Term> prefixComparator = Term.byPrefixOrder(prefix.length());

        // Find the first and last indices of terms that match the prefix
        Term prefixTerm = new Term(prefix, 0);
        int firstIndex = BinarySearch.firstIndexOf(terms, prefixTerm, prefixComparator);
        int lastIndex = BinarySearch.lastIndexOf(terms, prefixTerm, prefixComparator);

        // If no matches are found, return an empty array
        if (firstIndex == -1 || lastIndex == -1) {
            return new Term[0];
        }

        // Extract the matching terms
        Term[] matches = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);

        // Sort the matching terms by descending weight
        Arrays.sort(matches, Term.byDescendingWeightOrder());

        return matches;
    }

}
