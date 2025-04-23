package boj_g4_14938_서강그라운드;

import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to, dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        public int compareTo(Node o) {
            return this.dist - o.dist; // pq에서 거리 작은 순 정렬
        }
    }
    static int n, m, r;
    static int[] items;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 지역 수
        m = Integer.parseInt(st.nextToken()); // 수색 범위
        r = Integer.parseInt(st.nextToken()); // 길 개수

        items = new int[n + 1]; // 각 지역 아이템 수
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <=n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[n + 1]; // 그래프 인접 리스트
        for (int i = 1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            // 양방향 연결
            graph[start].add(new Node(end, d));
            graph[end].add(new Node(start, d));
        }

        br.close();

        int maxItems = 0;
        for (int i = 1; i <=n; i++) {
            maxItems = Math.max(maxItems, dijkstra(i));
        }
        System.out.println(maxItems);

    }

    static int dijkstra(int start) {
        int[] dist = new int[n+1]; // start로부터의 거리 배열
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.dist > dist[cur.to]) continue; // 더 짧은 거리로 이미 방문 했으면 패스

            for (Node next : graph[cur.to]) {
                int nextDist = dist[cur.to] + next.dist;
                if (nextDist < dist[next.to]) {
                    dist[next.to] = nextDist;
                    pq.add(new Node(next.to, nextDist));
                }
            }
        }

        int totalItems = 0;
        for (int i = 1; i<=n; i++) {
            if (dist[i] <= m) { // 수색 범위(m) 내의 아이템만
                totalItems += items[i];
            }
        }
        return totalItems;
    }
}
