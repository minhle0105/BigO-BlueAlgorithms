package Trie;

import java.util.*;

class Node {
    static final int MAX = 26;
    public Node[] child;
    public int countWord;
    public int maxPriority;

    public Node() {
        this.countWord = 0;
        this.child = new Node[MAX];
        this.maxPriority = 0;
    }
}

class TTrie {
    public static final int MAX = 26;
    private Node root;
    Map<String, Integer> priorities;

    public TTrie(Map<String, Integer> priorities) {
        this.root = new Node();
        this.priorities = priorities;
    }

    public int getMaxPriority(String word) {
        int ch;
        Node temp = root;
        for (int i = 0; i < word.length(); i++) {
            ch = word.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return -1;
            }
            temp = temp.child[ch];
        }
        return temp.maxPriority;
    }


    public void addWord(String s) {
        int ch;
        Node temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                Node x = new Node();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
            temp.maxPriority = Math.max(temp.maxPriority, this.priorities.get(s));
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        Node temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return false;
            }
            temp = temp.child[ch];
        }
        return temp.countWord > 0;
    }

}

public class SearchEngine {

    private static int[] solution(List<String> words, Map<String, Integer> map, List<String> queries) {
        TTrie trie = new TTrie(map);
        for (String word : words) {
            trie.addWord(word);
        }
        int[] results = new int[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String thisWord = queries.get(i);
            for (int j = 0; j < thisWord.length(); j++) {
                results[i] = trie.getMaxPriority(thisWord);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int q = Integer.parseInt(sc.next());
        List<String> words = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = sc.next();
            int level = Integer.parseInt(sc.next());
            words.add(word);
            map.put(word, level);
        }
        List<String> queries = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            String word = sc.next();
            queries.add(word);
        }
        int[] results = solution(words, map, queries);
        for (int result : results) {
            System.out.println(result);
        }
        sc.close();
    }
}
