package Trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class BerlandNode {
    static final int MAX = 2;
    public BerlandNode[] child;
    public BerlandNode parent;
    public int countWord;
    public int heightFromRoot;

    public BerlandNode() {
        this.heightFromRoot = 0;
        this.countWord = 0;
        this.child = new BerlandNode[MAX];
        this.parent = null;
    }

    public boolean hasAtLeastOneNull() {
        int countNull = 0;
        for (int i = 0; i < 2; i++) {
            if (this.child[i] == null) {
                countNull ++;
            }
        }
        return countNull >= 1;
    }
}

class BerlandTrie {
    public static final int MAX = 2;
    private BerlandNode root;
    private BerlandNode mostRecentlyAdded;

    public BerlandTrie() {
        this.root = new BerlandNode();
        this.mostRecentlyAdded = root;
    }

    private boolean rootHasTwoChildren() {
        int count = 0;
        for (int i = 0; i < 2; i++) {
            if (this.root.child[i] != null) {
                count ++;
            }
        }
        return count == 2;
    }

    public boolean addWord(int wordLength) {
        BerlandNode temp = this.mostRecentlyAdded;
        while (temp != null && (!temp.hasAtLeastOneNull() || temp.heightFromRoot >= wordLength)) {
            temp = temp.parent;
        }
        boolean rootHasTwoChildren = rootHasTwoChildren();
        if (temp == null) {
            return false;
        }
        for (int i = 0; i < 2; i++) {
            if (temp.child[i] == null) {
                for (int j = temp.heightFromRoot; j < wordLength; j++) {
                    int val = j == temp.heightFromRoot ? i : 0;
                    BerlandNode x = new BerlandNode();
                    temp.child[val] = x;
                    temp.child[val].parent = temp;
                    temp.child[val].heightFromRoot = temp.heightFromRoot + 1;
                    temp = temp.child[val];
                }
                break;
            }
        }
        temp.countWord++;
        this.mostRecentlyAdded = temp;
        return true;
    }

    public boolean findWord(String s) {
        int ch;
        BerlandNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return false;
            }
            temp = temp.child[ch];
        }
        return temp.countWord > 0;
    }

    public void printAllWords() {
        printAllWords(root, "");
    }

    private void printAllWords(BerlandNode root, String s) {
        if (root.countWord > 0) {
            System.out.println(s);
        }
        for (int i = 0; i < MAX; i++) {
            if (root.child[i] != null) {
                printAllWords(root.child[i], s + (i + ""));
            }
        }
    }
}

public class OldBerland {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        int[] wordLength = new int[n];
        for (int i = 0; i < n; i++) {
            wordLength[i] = Integer.parseInt(sc.next());
        }
        BerlandTrie trie = new BerlandTrie();
        boolean result = true;
        for (int length : wordLength) {
            boolean canAdd = trie.addWord(length);
            if (!canAdd) {
                result = false;
                break;
            }
        }
        if (result) {
            System.out.println("YES");
            trie.printAllWords();
        }
        else {
            System.out.println("NO");
        }
        sc.close();
    }
}
