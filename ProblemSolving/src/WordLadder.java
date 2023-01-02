import java.util.*;

/**
 * 4. Word Ladder
 * Level: Medium
 * Given two words (start and end) (both of same length), and a dictionary, find the length of shortest transformation sequence from start to end,
 * such that only one letter can be changed at a time, or 0 if no such sequence exists.
 * and each intermediate word must exist in the dictionary.
 *
 * Note that start does not need to be in dictionary.
 *
 * For example, given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * One shortest transformation is "hit" ->"hot" ->"dot" ->"dog" ->"cog", the program should return its length 5
 * Or another shortest transformation is "hit" ->"dot" ->"lot" ->"log" ->"cog", the program should return its length 5
 * */
class WordNode {
    String words;
    int numSteps;

    WordNode(String words, int numSteps) {
        this.words = words;
        this.numSteps = numSteps;
    }
}

public class WordLadder {

    /**
     * Solution 1A - Breath First Search
     * Breath First Search (BFS) is implemented for TreeNode by those steps:
     * Create linked list queue
     * Pop the first node from the queue
     * If that node is the one we're searching for, then return
     * Otherwise, add this node's children to the end of the queue and repeat the steps
     * */
    public static int findLengthOfShortestTransformationSequenceUsingBFS(String beginWord, String endWord, Set<String>
            wordDict) {

        LinkedList<WordNode> queue = new LinkedList<>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        while(!queue.isEmpty()) {
            // pop the first node from the queue
            WordNode top = queue.remove();
            String wordOnTop = top.words;

            if (wordOnTop.equals(endWord)) {
                return top.numSteps;
            }

            char[] wordArr = wordOnTop.toCharArray();
            for (int i=0; i<wordArr.length; i++) {
                for (char c='a'; c<='z'; c++) {
                    // replace all character from a-z with first character of wordOnTop if different
                    char temp = wordArr[i];
                    if (wordArr[i] != c) {
                        wordArr[i] = c;
                    }

                    // check newWord after replaced is available in dictionary
                    String newWord = new String(wordArr);
                    if (wordDict.contains(newWord)) {
                        int nextLevel = top.numSteps +1;
                        System.out.println(newWord + "- " + nextLevel);
                        queue.add(new WordNode(newWord, nextLevel));
                        wordDict.remove(newWord);
                    }

                    wordArr[i] = temp;
                }
            }
        }

        return 0;
    }

    /**
     * Solution 1B - Breath First Search with clean code
     * Breath First Search (BFS) is implemented for TreeNode by those steps:
     * Create linked list queue
     * Create set checked to ensure not check word twice
     * Pop the first node from the queue
     * If that node is the one we're searching for, then return
     * Otherwise, add this node's children to the end of the queue,
     * also add this node's children to the checked and repeat the steps
     * */
    public static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        // Breadth First Search
        Queue<WordNode> queue = new LinkedList();

        // put original world as a root node
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        // don't check words twice
        Set<String> checked = new HashSet();

        while(!queue.isEmpty()) {
            WordNode top = queue.remove();

            if(top.words.equals(endWord)) {
                return top.numSteps;
            }

            for(String w : wordDict) {
                String key = w;
                if(!checked.contains(key) && isOnlyOneLetterDifference(w, top.words) ) {
                    queue.add(new WordNode(w, top.numSteps + 1));
                    checked.add(key);
                }
            }
        }
        return 0;
    }

    /*
     * validate two strings
     * rule: only one letter can be changed at a time
     */
    private static boolean isOnlyOneLetterDifference(String current, String destination) {
        // the rule: all words have the same length.
        if(current.length() != destination.length()) {
            return false;
        }

        int count = 0;
        for(int i=0; i < current.length(); i++) {
            if(current.charAt(i) != destination.charAt(i)) {
                count++;

                if(count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));

        // C1A
        System.out.println(findLengthOfShortestTransformationSequenceUsingBFS("hit", "cog", dictionary));

        // C1B
        System.out.println(ladderLength("hit", "cog", dictionary));
    }
}
