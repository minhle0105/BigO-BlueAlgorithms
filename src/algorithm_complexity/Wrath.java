package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Wrath {
    private static int solution(List<Integer> list) {
        int currentPerson = list.size() - 1;
        int personKilled = currentPerson - 1;
        List<Integer> isKilled = new ArrayList<>();

        // đề bài là khi chuông reo, tất cả mọi người cùng chém 1 lúc --> mình phải xét từng người 1 xem chém
        // được mấy người (nếu không sẽ bị sót)
        // ví dụ, phải xét người 9 chém được mấy
        // người 8 chém được mấy
        // người 7 chém được mấy
        // ...
        // về tới người 0
        while (currentPerson >= 0) {
            while (personKilled >= 0) {
                int j = personKilled + 1;
                int i = currentPerson + 1;
                int Li = list.get(currentPerson);
                boolean iStandingBehindJ = j < i;
                boolean crawIsLongEnough = j >= i - Li;
                if (iStandingBehindJ && crawIsLongEnough) {
                    isKilled.add(personKilled);
                }
                // nếu tới đây điều kiện này false --> móng vuốt ko đủ dài, chắc chắn sẽ không chém
                // được từ đây nữa nên break luôn
                if (!crawIsLongEnough) {
                    break;
                }
                personKilled--;
            }
            currentPerson--;
        }
        return list.size() - isKilled.size();
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = Integer.parseInt(sc.next());
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            list.add(Integer.parseInt(sc.next()));
//        }
//        System.out.println(solution(list));
//        sc.close();
//    }
    @Test
    void firstTest() {
        List<Integer> list = Arrays.asList(0, 1, 0, 10);
        int expected = 1;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void secondTest() {
        List<Integer> list = Arrays.asList(0, 0);
        int expected = 2;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void thirdTest() {
        List<Integer> list = Arrays.asList(1,1,3,0,0,0,2,1,0,3);
        int expected = 3;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test4() {
        List<Integer> list = Arrays.asList(0,0,2,0,0,3,3,2,2,0);
        int expected = 2;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }
}
