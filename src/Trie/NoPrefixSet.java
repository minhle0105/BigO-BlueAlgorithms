package Trie;

import java.util.Scanner;

class PrefixNode {
    static final int MAX = 26;
    public PrefixNode[] child;
    public int countWord;

    public PrefixNode() {
        this.countWord = 0;
        this.child = new PrefixNode[MAX];
    }
}

class PrefixTrie {
    public static final int MAX = 26;
    private PrefixNode root;

    public PrefixTrie() {
        this.root = new PrefixNode();
    }

    public boolean addWord(String s) {
        int ch;
        PrefixNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                PrefixNode x = new PrefixNode();
                temp.child[ch] = x;
            } else {
                if (temp.child[ch].countWord > 0) {
                    return true;
                }
            }
            temp = temp.child[ch];
        }
        temp.countWord++;
        for (int i = 0; i < temp.child.length; i++) {
            if (temp.child[i] != null) {
                return true;
            }
        }
        return false;
    }

    public boolean findWord(String s) {
        int ch;
        PrefixNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return false;
            }
            temp = temp.child[ch];
        }
        return temp.countWord > 0;
    }

    public boolean containsASubWord(String s) {
        PrefixNode temp = root;
        int ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] != null && temp.child[ch].countWord > 0 && i != s.length() - 1) {
                return true;
            }
            temp = temp.child[ch];
        }
        return false;
    }
}

public class NoPrefixSet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }
        PrefixTrie trie = new PrefixTrie();
        boolean isBadSet = false;
        for (String word : words) {
            boolean violated = trie.addWord(word);
            if (violated) {
                isBadSet = true;
                System.out.println("BAD SET");
                System.out.println(word);
                break;
            }
        }

        if (!isBadSet) {
            System.out.println("GOOD SET");
        }
        sc.close();
    }
}
