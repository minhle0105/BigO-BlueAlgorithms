package heap;

import java.util.*;

class Widget {
    private int id;
    private int incrementedScore;
    private int zScore;
    private int postCount;
    private int likeCount;
    private int commentCount;
    private int shareCount;

    public Widget(int id, int zScore, int postCount, int likeCount, int commentCount, int shareCount) {
        this.id = id;
        this.zScore = zScore;
        this.postCount = postCount;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
        this.incrementedScore = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getzScore() {
        return zScore;
    }

    public void setzScore(int zScore) {
        this.zScore = zScore;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public void calculateScore() {
        this.incrementedScore = this.postCount * 50 + this.likeCount * 5 + this.commentCount * 10 + this.shareCount * 20 - this.zScore;
        this.zScore += this.incrementedScore;
    }

    public int getIncrementedScore() {
        return incrementedScore;
    }

    @Override
    public String toString() {
        return this.id + " " + this.zScore;
    }
}

public class RoyAndTrendingTopics {

    private static List<Widget> solution (List<Widget> widgets) {
        PriorityQueue<Widget> heap = new PriorityQueue<>(new Comparator<Widget>() {
            @Override
            public int compare(Widget o1, Widget o2) {
                if (o2.getIncrementedScore() == o1.getIncrementedScore()) {
                    return Integer.compare(o2.getId(), o1.getId());
                } else {
                    return Integer.compare(o2.getIncrementedScore(), o1.getIncrementedScore());
                }
            }
        });
        for (Widget widget : widgets) {
            widget.calculateScore();
        }
        List<Widget> results = new ArrayList<>();
        heap.addAll(widgets);
        for (int i = 0; i < 5; i++) {
            results.add(heap.remove());
        }
        return results;
    }
    // 1001 400
    //

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Widget> widgets = new ArrayList<>();
        int numberOfWidgets = Integer.parseInt(sc.next());
        for (int i = 0; i < numberOfWidgets; i++) {
            int id = Integer.parseInt(sc.next());
            int zScore = Integer.parseInt(sc.next());
            int postCount = Integer.parseInt(sc.next());
            int likeCount = Integer.parseInt(sc.next());
            int commentCount = Integer.parseInt(sc.next());
            int shareCount = Integer.parseInt(sc.next());
            Widget widget = new Widget(id, zScore, postCount, likeCount, commentCount, shareCount);
            widgets.add(widget);
        }
        List<Widget> results = solution(widgets);
        for (Widget widget : results) {
            System.out.println(widget);
        }

        sc.close();
    }
}
