package BST;

import java.util.*;

public class TanyaPostcard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        TreeMap<Character, Integer> tree = new TreeMap<>();
        for (char c : t.toCharArray()) {
            if (tree.containsKey(c)) {
                tree.put(c, tree.get(c) + 1);
            }
            else {
                tree.put(c, 1);
            }
        }
        int yay = 0;
        int whoops = 0;

        Set<Integer> hasBeenRemoved = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (tree.containsKey(s.charAt(i))) {
                hasBeenRemoved.add(i);
                tree.put(s.charAt(i), tree.get(s.charAt(i)) - 1);
                yay++;
                if (tree.get(s.charAt(i)) == 0) {
                    tree.remove(s.charAt(i));
                }
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (!hasBeenRemoved.contains(i)) {
                if (Character.isUpperCase(s.charAt(i))) {
                    char cLower = Character.toLowerCase(s.charAt(i));
                    if (tree.containsKey(cLower)) {
                        hasBeenRemoved.add(i);
                        whoops++;
                        tree.put(cLower, tree.get(cLower) - 1);
                        if (tree.get(cLower) == 0) {
                            tree.remove(cLower);
                        }
                        hasBeenRemoved.add(i);
                    }
                }
                if (Character.isLowerCase(s.charAt(i))) {
                    char cUpper = Character.toUpperCase(s.charAt(i));
                    if (tree.containsKey(cUpper)) {
                        hasBeenRemoved.add(i);
                        whoops++;
                        tree.put(cUpper, tree.get(cUpper) - 1);
                        if (tree.get(cUpper) == 0) {
                            tree.remove(cUpper);
                        }
                    }
                }
            }
        }

        System.out.println(yay + " " + whoops);

        sc.close();
    }
}
