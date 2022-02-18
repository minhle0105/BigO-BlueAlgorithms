package Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class DNATrie {
    public static final int MAX = 26;
    private DNANode root;

    public DNATrie() {
        this.root = new DNANode();
    }

    public void addWord(String s) {
        int ch;
        DNANode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                DNANode x = new DNANode();
                temp.child[ch] = x;
                temp.child[ch].distanceFromRoot = temp.distanceFromRoot + 1;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;
    }

    public boolean findWord(String s) {
        int ch;
        DNANode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return false;
            }
            temp = temp.child[ch];
        }
        return temp.countWord > 0;
    }

    public void dfs() {
        Stack<DNANode> stack = new Stack<>();

    }
}

class DNANode {
    static final int MAX = 26;
    public DNANode[] child;
    public int countWord;
    public int distanceFromRoot;
    public boolean isVisited;

    public DNANode() {
        this.countWord = 0;
        this.child = new DNANode[MAX];
        this.distanceFromRoot = 0;
        this.isVisited = false;
    }
}

public class DNAPrefix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTest = Integer.parseInt(sc.next());
        int[] results = new int[numberOfTest];
        for (int i = 0; i < numberOfTest; i++) {
            int n = Integer.parseInt(sc.next());
            List<String> strs = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                strs.add(sc.next());
            }
            DNATrie trie = new DNATrie();
            for (String s : strs) {
                trie.addWord(s);
            }

        }
        for (int i = 0; i < numberOfTest; i++) {
            System.out.println("Case " + (i + 1) + ": " + results[i]);
        }

        sc.close();
    }
}
