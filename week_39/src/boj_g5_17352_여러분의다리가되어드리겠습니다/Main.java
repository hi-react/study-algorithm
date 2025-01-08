package boj_g5_17352_여러분의다리가되어드리겠습니다;

import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 그래프 초기화
        graph = new ArrayList<>(N + 1); // 미리 크기 지정하여 메모리 최적화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 다리 정보 입력
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        visited = new boolean[N + 1];
        int[] results = new int[2]; // 최종적으로 연결 안 된 두 섬 번호
        int resultsIndex = 0;

        // 연결 요소 탐색
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                results[resultsIndex++] = i;
                dfs(i);
            }
        }

        br.close();
        bw.write(results[0] + " " + results[1]);
        bw.flush();
        bw.close();
    }

    private static void dfs(int node) {
        visited[node] = true;
        for (int near : graph.get(node)) {
            if (!visited[near]) {
                dfs(near);
            }
        }
    }
}
