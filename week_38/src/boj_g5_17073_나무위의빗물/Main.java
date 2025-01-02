package boj_g5_17073_나무위의빗물;

import java.io.*;
import java.util.*;

public class Main {

    static int N, W;
    static List<List<Integer>> tree;
    static boolean[] visited;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        tree = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            tree.get(U).add(V);
            tree.get(V).add(U);
        }

        br.close();

        // 리프 노드 개수
        int leafCount = countLeaves();
        double result = (double) W / leafCount;

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();

    }

    // 리프 노드 개수 세는 BFS
    static int countLeaves() {
        int leafCount = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            boolean isLeaf = true;

            for (int near : tree.get(current)) {
                if (!visited[near]) {
                    visited[near] = true;
                    queue.add(near);
                    isLeaf = false;
                }
            }

            if (isLeaf) leafCount++;
        }
        return leafCount;
    }
}
