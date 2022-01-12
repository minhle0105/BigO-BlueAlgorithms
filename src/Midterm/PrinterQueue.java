package Midterm;

import java.util.*;

class Document implements Comparable<Document> {
    int id;
    int importantLevel;

    public Document(int id, int importantLevel) {
        this.id = id;
        this.importantLevel = importantLevel;
    }


    @Override
    public int compareTo(Document o) {
        return Integer.compare(o.importantLevel, this.importantLevel);
    }
}

public class PrinterQueue {

    private static int print(Queue<Document> documents, int documentId) {
        PriorityQueue<Document> heap = new PriorityQueue<>();
        heap.addAll(documents);
        int time = 0;
        while (!documents.isEmpty()) {
            Document firstDoc = documents.remove();
            if (!heap.isEmpty() && firstDoc.importantLevel < heap.peek().importantLevel) {
                documents.add(firstDoc);
            }
            else {
                heap.remove();
                time++;
                if (firstDoc.id == documentId) {
                    return time;
                }
            }
        }
        return time;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfTests = Integer.parseInt(sc.next());
        for (int test = 0; test < numberOfTests; test++) {
            int n = Integer.parseInt(sc.next());
            int m = Integer.parseInt(sc.next());
            List<Integer> importantIndices = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                importantIndices.add(Integer.parseInt(sc.next()));
            }
            Queue<Document> documents = new LinkedList<>();
            for (int i = 0; i < importantIndices.size(); i++) {
                Document document = new Document(i, importantIndices.get(i));
                documents.add(document);
            }
            System.out.println(print(documents, m));
        }
        sc.close();
    }
}
