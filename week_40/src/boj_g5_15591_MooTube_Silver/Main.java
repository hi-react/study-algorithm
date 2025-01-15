package boj_g5_15591_MooTube_Silver;

import java.io.*;
import java.util.*;

public class Main {

    static class USADO {
        int target, usado;

        USADO(int target, int usado) {
            this.target = target;
            this.usado = usado;
        }
    }

    static List<List<USADO>> graph = new ArrayList<>();
    static int N;
    static boolean[] visited;
    static int count; // 추천 동영상 수

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 동영상 수
        int Q = Integer.parseInt(st.nextToken()); // 질문 수

        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 양방향 그래프 입력
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            graph.get(p).add(new USADO(q, r));
            graph.get(q).add(new USADO(p, r));
        }

        // Q개의 질문에 대한 답변 출력
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 최소 유사도
            int v = Integer.parseInt(st.nextToken()); // 주어진 동영상

            // 방문 배열 및 추천 동영상 수 초기화
            visited = new boolean[N + 1];
            count = 0;

            DFS(v, k);
            bw.write(count + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    // [method] 주어진 동영상에 대한 추천 동영상 수 계산
    private static void DFS(int currentVideo, int targetUsado) {
        visited[currentVideo] = true;

        for (USADO edge : graph.get(currentVideo)) {
            if (!visited[edge.target] && edge.usado >= targetUsado) {
                count++;
                DFS(edge.target, targetUsado);
            }
        }
    };
}
