package Trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class DictionaryNode {
    static final int MAX = 26;
    public DictionaryNode[] child;
    public int countWord;
    public boolean isVisited;
    public int distanceFromRoot;

    public DictionaryNode() {
        this.distanceFromRoot = 0;
        this.isVisited = false;
        this.countWord = 0;
        this.child = new DictionaryNode[MAX];
    }
}

class DictionaryTrie {
    public static final int MAX = 26;
    private DictionaryNode root;
    public Map<Integer, Long> map;

    public DictionaryTrie() {
        this.root = new DictionaryNode();
        this.map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(i, 0L);
        }
    }

    public long dfs() {
        long numberOfNodes = 0;
        Stack<DictionaryNode> stack = new Stack<>();
        stack.push(root);
        root.isVisited = true;
        while (!stack.isEmpty()) {
            DictionaryNode thisNode = stack.pop();
            for (int i = 0; i < 26; i++) {
                DictionaryNode nextNode = thisNode.child[i];
                if (nextNode != null && !nextNode.isVisited) {
                    nextNode.isVisited = true;
                    stack.push(nextNode);
                    numberOfNodes++;
                    if (nextNode.distanceFromRoot > 1) {
                        map.put(i, map.get(i) + 1);
                    }
                }
            }
        }
        return numberOfNodes;
    }

    public void addWord(String s) {
        int ch;
        DictionaryNode temp = root;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i) - 'a';
            if (temp.child[ch] == null) {
                DictionaryNode x = new DictionaryNode();
                temp.child[ch] = x;
                temp.child[ch].distanceFromRoot = temp.distanceFromRoot + 1;
            }
            temp = temp.child[ch];
        }
        temp.countWord++;

    }

    public boolean findWord(String s) {
        int ch;
        DictionaryNode temp = root;
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

public class DiccionarioPortunol {

    private static String[] reverseStringInArray(String[] strs) {
        String[] rev = new String[strs.length];
        int index = 0;
        for (String s : strs) {
            StringBuilder sRev = new StringBuilder();
            for (int i = s.length() - 1; i >= 0; i--) {
                sRev.append(s.charAt(i));
            }
            rev[index++] = sRev.toString();
        }
        return rev;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int m = Integer.parseInt(sc.next());
            int n = Integer.parseInt(sc.next());
            if (m == 0 | n == 0) {
                break;
            }
            String[] words1 = new String[m];
            String[] words2 = new String[n];
            for (int i = 0; i < m; i++) {
                words1[i] = sc.next();
            }
            for (int i = 0; i < n; i++) {
                words2[i] = sc.next();
            }
            String[] words2Rev = reverseStringInArray(words2);
            DictionaryTrie prefixTrie = new DictionaryTrie();
            DictionaryTrie suffixTrie = new DictionaryTrie();
            for (String word : words1) {
                prefixTrie.addWord(word);
            }
            for (String word : words2Rev) {
                suffixTrie.addWord(word);
            }
            long numberOfNodesInPrefix = prefixTrie.dfs();
            long numberOfNodesInSuffix = suffixTrie.dfs();
            long duplicate = 0;
            for (int key = 0; key < 26; key++) {
                if (prefixTrie.map.get(key) > 0 && suffixTrie.map.get(key) > 0) {
                    duplicate += prefixTrie.map.get(key) * suffixTrie.map.get(key);
                }
            }

            long result = (numberOfNodesInPrefix * numberOfNodesInSuffix) - duplicate;
            System.out.println(result);
        }

        sc.close();

    }
}
