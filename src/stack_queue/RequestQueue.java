package stack_queue;

import java.util.*;

class Request {
    private final int startTime;
    private final long timeToProcess;

    public Request(int startTime, int timeToProcess) {
        this.startTime = startTime;
        this.timeToProcess = timeToProcess;
    }

    public int getStartTime() {
        return startTime;
    }

    public long getTimeToProcess() {
        return timeToProcess;
    }
}

public class RequestQueue {

    private static List<Long> solution (List<Request> requests, int queueSize) {
        // map từng request với thời điểm nó được hoàn thành
        Map<Request, Long> requestCompletedTime = new LinkedHashMap<>();
        for (Request request : requests) {
            requestCompletedTime.put(request, 0L);
        }

        // mô phỏng front controller nhận tất cả các request và gửi từng request 1 vào queue tiếp theo để xử lý
        Queue<Request> frontController = new LinkedList<>(requests);

        // queue chính chứa các requests để xử lý
        Queue<Request> requestQueue = new LinkedList<>();

        // trước hết đưa các request đầu tiên vào xử lý
        for (int i = 0; i < (Math.min(queueSize, frontController.size())); i++) {
            requestQueue.add(frontController.remove());
        }
        long endTime = requestQueue.peek().getStartTime();

        // miễn là queue còn request thì vẫn tiếp tục xử lý
        while (!requestQueue.isEmpty()) {
            // lấy ra request đầu tiên trong queue
            Request requestBeingProcessed = requestQueue.remove();
            // cộng end time vào để lấy thời điểm nó hoàn thành và thay giá trị vào Map
            endTime = Math.max(requestBeingProcessed.getStartTime(), endTime) + requestBeingProcessed.getTimeToProcess();
            requestCompletedTime.put(requestBeingProcessed, endTime);
            System.out.print(endTime + " ");

            // trong khi đó, trong khi front controller vẫn còn request và request tiếp theo được sinh ra trong
            // khi đang xử lý dở request trước
            while ((!frontController.isEmpty()) && (frontController.peek().getStartTime() < endTime)) {
                // nếu queue còn chỗ thì đưa vào đợi
                if (requestQueue.size() < queueSize) {
                    requestQueue.add(frontController.remove());
                }
                // nếu không còn chỗ thì chắc chắn không bao giờ được xử lý
                else {
                    requestCompletedTime.put(frontController.remove(), (long) -1);
                }
            }
            // xử lý trường hợp while bên trên không chạy, front controller còn request thì phải đưa tiếp vào
            // queue để xử lý
            if (requestQueue.isEmpty() && !frontController.isEmpty()) {
                requestQueue.add(frontController.remove());
            }
        }
        return new ArrayList<>(requestCompletedTime.values());

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfRequests = Integer.parseInt(sc.next());
        int queueSize = Integer.parseInt(sc.next());
        List<Request> requests = new ArrayList<>();
        for (int i = 0; i < numberOfRequests; i++) {
            Request request = new Request(Integer.parseInt(sc.next()), Integer.parseInt(sc.next()));
            requests.add(request);
        }
        List<Long> results = solution(requests, queueSize);
//        StringBuilder s = new StringBuilder();
//        for (Long result : results) {
//            s.append(result).append(" ");
//        }
//        s = new StringBuilder(s.substring(0, s.length() - 1));
//        System.out.println(s);
        sc.close();

    }
}
