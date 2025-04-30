package boj_g3_1939_중량제한;

import java.io.*;
import java.util.*;

public class Main {

    static class Bridge {
        int to, weight;
        Bridge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M, factory1, factory2, maxWeight;
    static int answer = 0;
    static List<Bridge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 섬 개수
        M = Integer.parseInt(st.nextToken()); // 다리 정보

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        maxWeight = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Bridge(B, C));
            graph[B].add(new Bridge(A, C));
            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());
        factory1 = Integer.parseInt(st.nextToken());
        factory2 = Integer.parseInt(st.nextToken());

        br.close();

        binarySearch();

        System.out.println(answer);
    }

    static void binarySearch() {
        int left = 1;
        int right = maxWeight;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canCross(factory1, factory2, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    static boolean canCross(int start, int end, int weightLimit) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == end) return true;

            for(Bridge b : graph[current]) {
                if (!visited[b.to] && b.weight >= weightLimit) {
                    queue.offer(b.to);
                    visited[b.to] = true;
                }
            }
        }

        return false;
    }
}
