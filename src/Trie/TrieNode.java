package Trie;

public class TrieNode {
    static final int MAX = 26;
    public TrieNode[] child;
    public int countWord;

    public TrieNode() {
        this.countWord = 0;
        this.child = new TrieNode[MAX];
    }
}
