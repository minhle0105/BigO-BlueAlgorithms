package algorithm_complexity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ConstantRange {

    private static int solution (List<Integer> list) {
        int maxCanGo = 1;
        int firstPointer = 0;
        int secondPointer = 1;

        HashMap<Integer, Integer> hashSet = new HashMap<>();
        hashSet.put(list.get(firstPointer), 1);

        //Loop bên ngoài để đẩy firstPointer từng bước
        for(firstPointer = 0; firstPointer < list.size(); firstPointer++) {

            // Loop bên trong để đẩy secondPointer đến vị trí lớn nhất mà
            // đến đó thì không thể thêm phần tử vô được

            while(secondPointer < list.size()) {

                //Cố để thêm phần tử hiện tại
                if(hashSet.containsKey(list.get(secondPointer))) {
                    hashSet.put(list.get(secondPointer), hashSet.get(list.get(secondPointer)) + 1);
                }
                else {
                    hashSet.put(list.get(secondPointer), 1);
                }

                //Nếu thêm vô rồi mà vượt quá 2 phần tử thì bỏ nó
                if(hashSet.size()==3) {
                    hashSet.remove(list.get(secondPointer));
                    break;
                }

                // Còn không thì cộng lên để vòng lặp sau thêm
                secondPointer++;
            }
            if(secondPointer - firstPointer > maxCanGo) {
                maxCanGo = secondPointer - firstPointer;
//        		System.out.println(firstPointer + " " + secondPointer);
            }

            if(hashSet.get(list.get(firstPointer)) == 1) {
                hashSet.remove(list.get(firstPointer));
            }
            else {
                hashSet.put(list.get(firstPointer), hashSet.get(list.get(firstPointer)) - 1);
            }
        }

        return maxCanGo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.next());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(sc.next()));
        }
        System.out.println(solution(list));
        sc.close();
    }

    @Test
    void test1() {
        List<Integer> list = Arrays.asList(1,2,3,3,2);
        int expected = 4;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        List<Integer> list = Arrays.asList(5,4,5,5,6,7,8,8,8,7,6);
        int expected = 5;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test3() {
        List<Integer> list = Arrays.asList(2,3,3);
        int expected = 3;
        int actual = solution(list);
        Assertions.assertEquals(expected, actual);
    }
}
