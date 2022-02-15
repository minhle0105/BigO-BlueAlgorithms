package Trie;

public class Trie {
    public static final int MAX = 26;
    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void addWord(String s) {
        int ch;
        TrieNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                TrieNode x = new TrieNode();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        TrieNode temp = root;
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
