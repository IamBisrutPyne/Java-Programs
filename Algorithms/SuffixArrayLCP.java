import java.util.*;

public class SuffixArrayLCP {
    public static Integer[] buildSA(String s) {
        int n = s.length();
        Integer[] sa = new Integer[n];
        int[] ranks = new int[n];
        int[] tmp = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            ranks[i] = s.charAt(i);
        }

        for (int k = 1; k < n; k <<= 1) {
            final int K = k;
            Arrays.sort(sa, (a, b) -> {
                if (ranks[a] != ranks[b]) return Integer.compare(ranks[a], ranks[b]);
                int ra = a + K < n ? ranks[a + K] : -1;
                int rb = b + K < n ? ranks[b + K] : -1;
                return Integer.compare(ra, rb);
            });

            tmp[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                tmp[sa[i]] = tmp[sa[i - 1]];
                int prev = sa[i - 1], cur = sa[i];
                if (ranks[prev] != ranks[cur]) {
                    tmp[cur]++;
                } else {
                    int rprev = prev + K < n ? ranks[prev + K] : -1;
                    int rcur = cur + K < n ? ranks[cur + K] : -1;
                    if (rprev != rcur) tmp[cur]++;
                }
            }
            System.arraycopy(tmp, 0, ranks, 0, n);
            if (ranks[sa[n - 1]] == n - 1) break;
        }
        return sa;
    }

    public static int[] buildLCP(String s, Integer[] sa) {
        int n = s.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[sa[i]] = i;
        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            int r = rank[i];
            if (r > 0) {
                int j = sa[r - 1];
                while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
                lcp[r] = h;
                if (h > 0) h--;
            }
        }
        return lcp;
    }

    public static long countDistinctSubstrings(String s) {
        int n = s.length();
        Integer[] sa = buildSA(s);
        int[] lcp = buildLCP(s, sa);
        long total = (long) n * (n + 1) / 2;
        long sumLcp = 0;
        for (int x : lcp) sumLcp += x;
        return total - sumLcp;
    }

    public static void main(String[] args) {
        String s = "banana";
        if (args.length > 0) s = args[0];
        Integer[] sa = buildSA(s);
        int[] lcp = buildLCP(s, sa);

        System.out.println("String: " + s);
        System.out.println("Index\tSA\tSuffix\tLCP");
        for (int i = 0; i < s.length(); i++) {
            System.out.printf("%d\t%d\t%s\t%d\n", i, sa[i], s.substring(sa[i]), lcp[i]);
        }
        System.out.println("Distinct substrings: " + countDistinctSubstrings(s));
    }
}