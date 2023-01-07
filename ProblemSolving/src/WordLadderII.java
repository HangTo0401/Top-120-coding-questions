import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;

import java.util.*;

/**
 * 5. Word Ladder II
 * Level: Hard
 * Given two words (start and end), and a dictionary, find all possible shortest transformation
 * sequence(s) from start to end, such that:
 *
 * 1) Only one letter can be changed at a time,
 * 2) Each intermediate word must exist in the dictionary.
 * Each sequence should be returned as a list of the words [start, s1, s2, ..., sk]
 *
 * For example, given: start = "hit", end = "cog", and dict = ["hot","dot","dog","lot","log"],
 * return:
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 *
 * All possible transformation are:
 * "hit" ->"hot" ->"dot" ->"lot" ->"dog" ->"log" ->"cog", the program should return its length 7
 * "hit" ->"hot" ->"dot" ->"dog" ->"lot" ->"cog", the program should return its length 6
 * "hit" ->"hot" ->"dot" ->"dog" ->"cog", the program should return its length 5
 * "hit" ->"dot" ->"lot" ->"log" ->"cog", the program should return its length 5
 * */
public class WordLadderII {

    static class WordNode {
        String words;
        int numSteps;
        WordNode pre;

        WordNode(String words, int numSteps, WordNode pre) {
            this.words = words;
            this.numSteps = numSteps;
            this.pre = pre;
        }
    }

    /**
     * Solution 1 - Breath First Search + Depth First Search
     * Step 1: Using Breath First Search to find the shortest transformation sequence(s) from start to end which is 5 in this example
     * Step 2: Apply all possible shortest transformation sequence(s)
     * We have to store the pointer from parent node to child node to make list "hit" ->"hot" ->"dot" ->"dog" ->"cog"
     * and also if we traverse in case there is pointing from node A to B which is SAME LEVEL, then do nothing
     *
     * Breath First Search (BFS) is implemented for TreeNode by those steps:
     * Create linked list queue
     * Pop the first node from the queue
     * If that node is the one we're searching for, then return
     * Otherwise, add this node's children to the end of the queue and repeat the steps
     * */
    public static List<List<String>> findShortestTransformationSequenceUsingBFSAndDFS(String beginWord, String endWord, Set<String>
            wordDict) {
        List<List<String>> result = new ArrayList<List<String>>();

        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1, null));
        wordDict.add(endWord);

        int minStep = 0;
        HashSet<String> visited = new HashSet<String>();
        HashSet<String> unvisited = new HashSet<String>();
        unvisited.addAll(wordDict);

        int preNumSteps = 0;
        while (!queue.isEmpty()) {
            WordNode top = queue.remove();
            String word = top.words;
            int currNumSteps = top.numSteps;

            if (word.equals(endWord)) {
                if (minStep == 0) {
                    minStep = top.numSteps;
                }
                if (top.numSteps == minStep && minStep != 0) {
                    // get the shortest transformation sequences
                    ArrayList<String> t = new ArrayList<String>();
                    t.add(top.words);
                    while (top.pre != null) {
                        t.add(0, top.pre.words);
                        top = top.pre;
                    }
                    result.add(t);
                    continue;
                }
            }
            if (preNumSteps < currNumSteps) {
                unvisited.removeAll(visited);
            }
            preNumSteps = currNumSteps;
            char[] arr = word.toCharArray();

            for (int i = 0; i < arr.length; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char temp = arr[i];
                    if (arr[i] != c) {
                        arr[i] = c;
                        String newWord = new String(arr);

                        if (unvisited.contains(newWord)) {
                            queue.add(new WordNode(newWord, top.numSteps + 1, top));
                            visited.add(newWord);
                        }
                        arr[i] = temp;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Set<String> dictionary = new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));

        // C1
        List<List<String>> result = findShortestTransformationSequenceUsingBFSAndDFS("hit", "cog", dictionary);
        result.stream().forEach(l -> {
            l.stream().forEach(System.out::println);
            System.out.println("================");
        });
    }
}