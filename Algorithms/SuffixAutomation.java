/*
 * File: AStar.java
 * Author: Shashank S
 * Description: Description: 
 * This program constructs a Suffix Array and computes the Longest Common Prefix (LCP) array for a given string.
 * The Suffix Array provides an efficient way to represent all suffixes of a string in lexicographical order,
 * while the LCP array stores the length of the longest common prefix between consecutive suffixes.
 * These data structures are fundamental for solving problems in string processing such as pattern matching,
 * substring queries, and text compression algorithms.
 * Date: 2025-10-16
 */

// SuffixAutomaton.java
// Usage: javac SuffixAutomaton.java && java SuffixAutomaton
import java.util.*;

public class SuffixAutomation {
    static class State {
        int len, link;
        Map<Character, Integer> next = new HashMap<>();
        State(int len) { this.len = len; this.link = -1; }
    }

    private final ArrayList<State> st;
    private int last;

    public SuffixAutomation(String s) {
        st = new ArrayList<>();
        st.add(new State(0)); // state 0
        last = 0;
        for (char c : s.toCharArray()) extend(c);
    }

    private void extend(char c) {
        int cur = st.size();
        st.add(new State(st.get(last).len + 1));
        int p = last;
        while (p != -1 && !st.get(p).next.containsKey(c)) {
            st.get(p).next.put(c, cur);
            p = st.get(p).link;
        }
        if (p == -1) {
            st.get(cur).link = 0;
        } else {
            int q = st.get(p).next.get(c);
            if (st.get(p).len + 1 == st.get(q).len) {
                st.get(cur).link = q;
            } else {
                int clone = st.size();
                State cloned = new State(st.get(p).len + 1);
                cloned.next.putAll(st.get(q).next);
                cloned.link = st.get(q).link;
                st.add(cloned);
                while (p != -1 && st.get(p).next.get(c) == q) {
                    st.get(p).next.put(c, clone);
                    p = st.get(p).link;
                }
                st.get(q).link = st.get(cur).link = clone;
            }
        }
        last = cur;
    }

    // Number of different substrings = sum(len[v] - len[link[v]])
    public long distinctSubstringsCount() {
        long res = 0;
        for (int i = 1; i < st.size(); i++) {
            res += st.get(i).len - st.get(st.get(i).link).len;
        }
        return res;
    }

    // Find LCS of this SAM's string and another string t
    public int longestCommonSubstring(String t) {
        int v = 0, l = 0, best = 0;
        for (char c : t.toCharArray()) {
            if (st.get(v).next.containsKey(c)) {
                v = st.get(v).next.get(c);
                l++;
            } else {
                while (v != -1 && !st.get(v).next.containsKey(c)) v = st.get(v).link;
                if (v == -1) { v = 0; l = 0; }
                else {
                    l = st.get(v).len + 1;
                    v = st.get(v).next.get(c);
                }
            }
            if (l > best) best = l;
        }
        return best;
    }

    // Demo / test
    public static void main(String[] args) {
        String s = "ababa";
        if (args.length > 0) s = args[0];
        SuffixAutomation sam = new SuffixAutomation(s);
        System.out.println("Original string: " + s);
        System.out.println("Number of states: " + sam.st.size());
        System.out.println("Distinct substrings count: " + sam.distinctSubstringsCount());

        // LCS test with another string
        String t = "baba";
        System.out.println("LCS with '" + t + "': " + sam.longestCommonSubstring(t));
    }
}// SuffixArrayLCP.java
// Usage: javac SuffixArrayLCP.java && java SuffixArrayLCP

//How to run 
/*
javac SuffixAutomaton.java
java SuffixAutomaton
# with custom:
java SuffixAutomaton "banana"
 */