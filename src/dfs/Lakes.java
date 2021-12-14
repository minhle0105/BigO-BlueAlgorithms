package dfs;

import java.util.*;

public class Lakes {

    static int[][] visited;
    static final char land = '*';
    // lakeInfo là để lưu những hồ trong map và kích thước. Ví dụ có 3 node thì là có 3 hồ, giá trị mỗi node là kích cỡ hồ
    static LinkedList<Integer> lakeInfo;
    static final int[] dR = {-1, 1, 0, 0};
    static final int[] dC = {0, 0, -1, 1};
    // lakePosition là để lưu kích cỡ hồ và vị trí có thể lấp của hồ đó. Giả sử hồ kích cỡ 2 sẽ lấp được ở [1,6] và [1,7]
    static Map<Integer, List<int[]>> lakePosition;

    // kiểm tra xem bao giờ visit hết map
    private static int[] graphIsFull(int[][] visited) {
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                if (visited[i][j] == 0) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // dfs để tìm hồ và kích cỡ
    private static int findLakeSize(int[] startPoint, int numberOfRows, int numberOfColumns) {
        Stack<Integer> stack = new Stack<>();
        List<int[]> positions = new ArrayList<>();
        stack.push(startPoint[1]);
        stack.push(startPoint[0]);
        positions.add(new int[]{startPoint[0], startPoint[1]});
        int count = 1;
        // isOnBound sẽ là true nếu như hồ này nằm trên đường bao, và nếu thế thì không tính
        boolean isOnBound = false;
        while (!stack.isEmpty()) {
            int thisX = stack.pop();
            int thisY = stack.pop();
            if (thisX == 0 | thisX == numberOfRows - 1 | thisY == 0 || thisY == numberOfColumns - 1) {
                isOnBound = true;
            }
            for (int direction = 0; direction < 4; direction++) {
                int nextX = thisX + dR[direction];
                int nextY = thisY + dC[direction];
                boolean inBound = nextX >= 0 && nextX < numberOfRows && nextY >= 0 && nextY < numberOfColumns;
                if (inBound) {
                    if (visited[nextX][nextY] == 0) {
                        visited[nextX][nextY] = 1;
                        stack.push(nextY);
                        stack.push(nextX);
                        count++;
                        positions.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
        if (!isOnBound) {
            // nếu không nằm trên đường bao thì thêm vị trí và kích cỡ hồ vào map
            if (lakePosition.containsKey(count)) {
                for (int[] position : positions) {
                    lakePosition.get(count).add(position);
                }
            } else {
                lakePosition.put(count, positions);
            }
            return count;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // khởi tạo biến static
        lakeInfo = new LinkedList<>();
        lakePosition = new HashMap<>();

        // đọc input
        int numberOfRows = Integer.parseInt(sc.next());
        int numberOfColumns = Integer.parseInt(sc.next());
        int finalLakes = Integer.parseInt(sc.next());
        char[][] map = new char[numberOfRows][numberOfColumns];
        visited = new int[numberOfRows][numberOfColumns];
        for (int i = 0; i < numberOfRows; i++) {
            String thisRow = sc.next();
            map[i] = thisRow.toCharArray();
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (map[i][j] == land) {
                    visited[i][j] = 1;
                }
            }
        }

        // liên tục kiểm tra cho tới khi map hoàn toàn được visit
        while (true) {
            // lấy điểm có hồ tiếp theo để kiểm tra
            int[] nextStartPoint = graphIsFull(visited);
            if (nextStartPoint[0] == -1) {
                break;
            }
            visited[nextStartPoint[0]][nextStartPoint[1]] = 1;
            // nếu lakeSize trả về là 0, tức là nằm trên đường bao, không tính. nếu không thì thêm vào linkedlist
            int lakeSize = findLakeSize(nextStartPoint, numberOfRows, numberOfColumns);
            if (lakeSize == 0) {
                continue;
            }
            lakeInfo.add(lakeSize);
        }
        int count = 0;
        // vì cần lấp ít nhất nên sort để lấp từ nhỏ tới lớn
        // dùng linkedlist là vì liên tục cần dỡ phần tử đầu tiên, sẽ efficient hơn arraylist
        Collections.sort(lakeInfo);
        while (lakeInfo.size() != finalLakes) {
            // liên tục gỡ cho tới khi bằng số hồ cần sót lại
            int lakeSizeToFill = lakeInfo.removeFirst();
            count += lakeSizeToFill;
            // lấy toạ độ hồ cần lấp
            for (int i = 0; i < lakeSizeToFill; i++) {
                int[] pointToFill = lakePosition.get(lakeSizeToFill).get(0);
                lakePosition.get(lakeSizeToFill).remove(0);
                map[pointToFill[0]][pointToFill[1]] = land;
            }
        }
        System.out.println(count);
        for (char[] row : map) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
        sc.close();
    }

}
