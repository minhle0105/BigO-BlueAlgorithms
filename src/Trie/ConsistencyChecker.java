package Trie;

import java.util.Scanner;

class ConsistencyNode {
    static final int MAX = 10;
    public ConsistencyNode[] child;
    public int countWord;

    public ConsistencyNode() {
        this.countWord = 0;
        this.child = new ConsistencyNode[MAX];
    }
}

class ConsistencyTrie {
    public static final int MAX = 10;
    private ConsistencyNode root;

    public ConsistencyTrie() {
        this.root = new ConsistencyNode();
    }

    public void addWord(String s) {
        int ch;
        ConsistencyNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - '0';
            if (temp.child[ch] == null) {
                ConsistencyNode x = new ConsistencyNode();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        ConsistencyNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - '0';
            if (temp.child[ch] == null) {
                return false;
            }
            temp = temp.child[ch];
        }
        return temp.countWord > 0;
    }

    public boolean containsASubWord(String s) {
        ConsistencyNode temp = root;
        int ch;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - '0';
            if (temp.child[ch] != null && temp.child[ch].countWord > 0) {
                count ++;
            }
            temp = temp.child[ch];
        }
        return count > 1;
    }
}

public class ConsistencyChecker {

    private static boolean check(ConsistencyTrie trie, String[] signals) {
        for (String signal : signals){
            if (trie.containsASubWord(signal)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        String[] results = new String[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            String[] signals = new String[n];
            for (int i = 0; i < n; i++) {
                signals[i] = sc.next();
            }
            ConsistencyTrie trie = new ConsistencyTrie();
            for (String signal : signals) {
                trie.addWord(signal);
            }
            boolean result = check(trie, signals);
            if (result) {
                results[test] = "NO";
            }
            else {
                results[test] = "YES";
            }
        }

        for (int i = 0; i < results.length; i++) {
            System.out.println("Case " + (i+1) + ": " + results[i]);
        }
        sc.close();
    }
}
