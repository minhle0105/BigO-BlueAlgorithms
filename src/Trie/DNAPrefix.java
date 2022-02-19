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
            ch = s.charAt(i) - 'A';
            if (temp.child[ch] == null) {
                DNANode x = new DNANode();
                temp.child[ch] = x;
                temp.child[ch].distanceFromRoot = temp.distanceFromRoot + 1;
            }
            temp = temp.child[ch];
            temp.wordThatHasThisPrefix++;
        }
        temp.countWord++;
    }

    public int dfs() {
        Stack<DNANode> stack = new Stack<>();
        stack.add(root);
        root.isVisited = true;
        int max = root.wordThatHasThisPrefix * root.distanceFromRoot;
        while (!stack.isEmpty()) {
            DNANode thisNode = stack.pop();
            for (int i = 0; i < 26; i++) {
                DNANode nextNode = thisNode.child[i];
                if (nextNode != null && !nextNode.isVisited) {
                    int val = nextNode.wordThatHasThisPrefix * nextNode.distanceFromRoot;
                    if (val > max) {
                        max = val;
                    }
                    nextNode.isVisited = true;
                    stack.add(nextNode);
                }
            }
        }
        return max;
    }
}

class DNANode {
    static final int MAX = 26;
    public DNANode[] child;
    public int countWord;
    public int distanceFromRoot;
    public boolean isVisited;
    public int wordThatHasThisPrefix;

    public DNANode() {
        this.countWord = 0;
        this.child = new DNANode[MAX];
        this.distanceFromRoot = 0;
        this.isVisited = false;
        this.wordThatHasThisPrefix = 0;
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
            int max = trie.dfs();
            results[i] = max;
        }
        for (int i = 0; i < numberOfTest; i++) {
            System.out.println("Case " + (i + 1) + ": " + results[i]);
        }

        sc.close();
    }
}
