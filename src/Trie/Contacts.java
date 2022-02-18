package Trie;

import java.util.Scanner;

class ContactNode {
    static final int MAX = 26;
    public ContactNode[] child;
    public int countWord;
    public int countPrefix;

    public ContactNode() {
        this.countWord = 0;
        this.countPrefix = 0;
        this.child = new ContactNode[MAX];
    }
}

class ContactTrie {
    public static final int MAX = 26;
    private ContactNode root;

    public ContactTrie() {
        this.root = new ContactNode();
    }

    public void addWord(String s) {
        int ch;
        ContactNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                ContactNode x = new ContactNode();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
            temp.countPrefix += 1;
        }
        temp.countWord++;

    }

    public int findWord(String s) {
        int ch;
        ContactNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                return 0;
            }
            temp = temp.child[ch];
        }
        if (temp.countPrefix == 0) {
            return 0;
        }
        else {
            return temp.countPrefix;
        }
    }

    public boolean containsASubWord(String s) {
        ContactNode temp = root;
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

public class Contacts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int queries = Integer.parseInt(sc.nextLine());
        ContactTrie trie = new ContactTrie();
        for (int i = 0; i < queries; i++) {
            String[] query = sc.nextLine().split(" ");
            if (query[0].equals("add")) {
                String wordToAdd = query[1];
                trie.addWord(wordToAdd);
            }
            else {
                String wordToFind = query[1];
                int result = trie.findWord(wordToFind);
                System.out.println(result);
            }
        }
        sc.close();
    }
}
