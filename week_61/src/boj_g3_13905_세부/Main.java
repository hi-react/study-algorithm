package boj_g3_13905_세부;

import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M, s, e;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 집 개수
        M = Integer.parseInt(st.nextToken()); // 다리 개수

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 시작 위치
        e = Integer.parseInt(st.nextToken()); // 끝 위치

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxWeight = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, w));
            graph[b].add(new Edge(a, w));
            maxWeight = Math.max(maxWeight, w);
        }

        br.close();

        int left = 1, right = maxWeight;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canGo(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    // s => e 이동 하는 동안, 다리 무게 >= 빼빼로 무게)인 지 BFS
    static boolean canGo(int peperoWeight) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            if (curr == e) return true;
            for (Edge e : graph[curr]) {
                if (!visited[e.to] && e.weight >= peperoWeight) {
                    visited[e.to] = true;
                    q.offer(e.to);
                }
            }
        }

        return false;
    }
}
