package list_string;

public class MuseumNight {

    private static int rotate(String s) {
        char[] sToChar = s.toCharArray();
        int result = 0;
        if (sToChar[0] != 'a') {
            int cToA = Math.abs((int) sToChar[0] - (int) 'a');
            int cToARev = 26 - cToA;
            result += Math.min(cToA, cToARev);
        }
        for (int i = 0; i < sToChar.length - 1; i++) {
            char c = sToChar[i];
            char cNext = sToChar[i+1];
            int c1ToC = Math.abs((int) c - (int) cNext);
            int c2ToC = 26 - c1ToC;
            int turn = Math.min(c1ToC, c2ToC);
            result += turn;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(rotate("map"));
    }
}
