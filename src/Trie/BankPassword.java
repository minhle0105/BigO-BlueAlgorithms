package Trie;

import java.util.Arrays;
import java.util.Scanner;

class BankTrieNode {
    static final int MAX = 26;
    public BankTrieNode[] child;
    public int countWord;

    public BankTrieNode() {
        this.countWord = 0;
        this.child = new BankTrieNode[MAX];
    }
}

class BankTrie {
    public static final int MAX = 26;
    private BankTrieNode root;

    public BankTrie() {
        this.root = new BankTrieNode();
    }

    public void addWord(String s) {
        int ch;
        BankTrieNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                BankTrieNode x = new BankTrieNode();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        BankTrieNode temp = root;
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
        BankTrieNode temp = root;
        int ch;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] != null && temp.child[ch].countWord > 0) {
                count ++;
            }
            temp = temp.child[ch];
        }
        return count > 1;
    }
}

public class BankPassword {

    private static boolean check(BankTrie bankTrie, String[] passwords) {
        for (String password : passwords){
            if (bankTrie.containsASubWord(password)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            passwords[i] = sc.next();
        }
        BankTrie bankTrie = new BankTrie();
        for (String password : passwords) {
            bankTrie.addWord(password);
        }
        boolean isVulnerable = check(bankTrie, passwords);
        if (isVulnerable) {
            System.out.println("vulnerable");
        }
        else {
            System.out.println("non vulnerable");
        }
        sc.close();
    }
}
