package finalExam;

import java.util.Scanner;

class PhoneListNode {
    static final int MAX = 10;
    public PhoneListNode[] child;
    public int countWord;

    public PhoneListNode() {
        this.countWord = 0;
        this.child = new PhoneListNode[MAX];
    }
}

class PhoneListTrie {
    public static final int MAX = 10;
    private PhoneListNode root;

    public PhoneListTrie() {
        this.root = new PhoneListNode();
    }

    public void addWord(String s) {
        int ch;
        PhoneListNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - '0';
            if (temp.child[ch] == null) {
                PhoneListNode x = new PhoneListNode();
                temp.child[ch] = x;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        PhoneListNode temp = root;
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
        PhoneListNode temp = root;
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

public class PhoneList {

    private static boolean check(PhoneListTrie trie, String[] signals) {
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
        boolean[] results = new boolean[numberOfTest];
        for (int test = 0; test < numberOfTest; test++) {
            int n = Integer.parseInt(sc.next());
            String[] inputs = new String[n];
            for (int i = 0; i < n; i++) {
                inputs[i] = sc.next();
            }
            PhoneListTrie trie = new PhoneListTrie();
            for (String input : inputs) {
                trie.addWord(input);
            }
            results[test] = check(trie, inputs);
        }
        for (boolean result : results) {
            if (result) {
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
        sc.close();
    }
}
