import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Doublets implements WordLadderGame {
    private Set<String> lexicon;

    public Doublets(InputStream in) {
        lexicon = new HashSet<>();
        try (Scanner s = new Scanner(new BufferedReader(new InputStreamReader(in)))) {
            while (s.hasNext()) {
                String word = s.next();
                lexicon.add(word.toLowerCase());
                s.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getHammingDistance(String word1, String word2) {
        if (word1.length() != word2.length()) return -1;
        int distance = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) distance++;
        }
        return distance;
    }

    @Override
    public boolean isWord(String word) {
        return lexicon.contains(word.toLowerCase());
    }

    @Override
    public int getWordCount() {
        return lexicon.size();
    }

    @Override
    public List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char originalChar = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    chars[i] = c;
                    String newWord = new String(chars);
                    if (isWord(newWord)) neighbors.add(newWord);
                }
            }
            chars[i] = originalChar;
        }
        return neighbors;
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {
        if (sequence.isEmpty()) return false;
        for (int i = 1; i < sequence.size(); i++) {
            if (getHammingDistance(sequence.get(i - 1), sequence.get(i)) != 1 || !isWord(sequence.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {
        if (!isWord(start) || !isWord(end) || start.length() != end.length()) {
            return Collections.emptyList();
        }
        if (start.equals(end)) {
            return Arrays.asList(start);
        }

        Queue<List<String>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        // Use ArrayList here to create a modifiable list.
        List<String> initialPath = new ArrayList<>();
        initialPath.add(start);
        queue.offer(initialPath);
        visited.add(start);

        while (!queue.isEmpty()) {
            List<String> path = queue.poll();
            String lastWord = path.get(path.size() - 1);

            for (String neighbor : getNeighbors(lastWord)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    
                    // Create a new modifiable list for the path to avoid modifying the original list.
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);

                    if (neighbor.equals(end)) {
                        return newPath;
                    }
                    
                    queue.offer(newPath);
                }
            }
        }
        return Collections.emptyList(); // No path found
    }

}
