import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic. Two strings are isomorphic if the characters in s can be replaced to get t.
 * For example,"egg" and "add" are isomorphic, "foo" and "bar" are not.
 *
 * Level: Easy
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * */

public class IsomorphicStrings {

    /**
     * Solution 1 - Naive Approach
     *
     * A simple solution is to consider every character of ‘str1’ and check if all occurrences of it map to the same character in ‘str2’.
     *
     * Using map<character of fist string, character of second string>
     *
     * Time Complexity: O(N * N)
     * Auxiliary Space: O(1)
     * */
    public static boolean isIsomorphicUsingMap(String s, String t) {
        if (s == null || t == null) return false;

        if (s.length() != t.length()) return false;

        if (s.length() == 0 && t.length() == 0) return true;

        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            Character c = getKey(map, c2);
            if (c != null && c != c1) {
                // check whether a particular key of character of second string being mapped in the map
                // is same with character of fist string or not
                return false;
            } else if (map.containsKey(c1)) {
                // return false if first occurrence of c1 is mapped to a different character.
                if (c2 != map.get(c1)) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }

        return true;
    }


    public static Character getKey(Map<Character, Character> map, Character target) {
        // a method for getting key of a target value
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(target)) {
                return entry.getKey();
            }
        }

        return null;
    }

    /**
     * Solution 2 - Using array
     * The idea is that we need to map a char to another one, for example, "egg" and "add", we need to construct the mapping 'e' -> 'a' and 'g' -> 'd'.
     * Instead of directly mapping 'e' to 'a', another way is to mark them with same value,
     *
     * for example, 'e' -> 1, 'a'-> 1, and 'g' -> 2, 'd' -> 2, this works same.
     *
     * So we use 2 arrays here m1 and m2, initialized space is 256 (Since the whole ASCII size is 256, 128 also works here).
     * Traverse the character of both s and t on the same position,
     * if their mapping values in m1 and m2 are different, means they are not mapping correctly, return false;
     * else we construct the mapping, since m1 and m2 are both initialized as 0, we want to use a new value when i == 0, so i + 1 works here.
     *
     * Data structure: int array which is faster than hashmap.
     * */
    public static boolean isIsomorphicUsingArray(String s, String t) {
        if (s == null || t == null) return false;

        if (s.length() != t.length()) return false;

        if (s.length() == 0 && t.length() == 0) return true;

        int[] m1 = new int[256];
        int[] m2 = new int[256];

        // assign -1 to all elements in array
        Arrays.fill(m1,-1);
        Arrays.fill(m2,-1);

        for (int i=0; i<s.length(); i++) {
            if (m1[s.charAt(i)] != m2[t.charAt(i)]) return false;

            m1[s.charAt(i)] = i + 1;
            m2[t.charAt(i)] = i + 1;
        }

        return true;
    }

    public static void main(String[] args) {
        // C1
        System.out.println(isIsomorphicUsingMap("egg", "add"));

        // C2
        System.out.println(isIsomorphicUsingArray("egg", "add"));
    }
}
