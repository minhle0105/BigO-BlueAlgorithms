package algorithm_complexity;

import java.util.*;

public class ConstantRange {

    private static int solution (List<Integer> list) {
        int maxCanGo = 1;

        // 2 cái pointer này là để đánh dấu các đoạn gần như không đổi
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
                // Theo kĩ thuật đếm frequency của phần tử
                if(hashSet.containsKey(list.get(secondPointer))) {
                    hashSet.put(list.get(secondPointer), hashSet.get(list.get(secondPointer)) + 1);
                }
                else {
                    hashSet.put(list.get(secondPointer), 1);
                }

                //Nếu thêm vô rồi mà vượt quá 2 phần tử thì bỏ nó và cũng không đi tiếp while loop
                // là vì đoạn dữ liệu cho không có bước nhảy lớn giữa các phần tử
                // mà nếu 1 đoạn có quá 2 phần tử thì độ chênh giữa max và min của đoạn
                // chắc chắn sẽ lớn hơn 1 --> không hợp lệ
                if(hashSet.size() == 3) {
                    hashSet.remove(list.get(secondPointer));
                    break;
                }

                // Còn không thì cộng lên để vòng lặp sau thêm
                secondPointer++;
            }
            // cần cập nhật lại cái maxCanGo sau mỗi lần đẩy secondPointer lên xa nhất có thể
            if(secondPointer - firstPointer > maxCanGo) {
                maxCanGo = secondPointer - firstPointer;
            }

            // đẩy lên đoạn tiếp để tìm xem có đoạn nào dài hơn không,
            // thì nếu ví dụ đoạn là 4 5 5 5 (firstPointer ở 0) --> giờ chỉ xét đoạn 5 5 5
            if(hashSet.get(list.get(firstPointer)) == 1) {
                hashSet.remove(list.get(firstPointer));
            }
            // còn nếu là 4 4 5 5 5 (firstPointer ở 0) --> giờ xét đoạn 4 5 5 5
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
}