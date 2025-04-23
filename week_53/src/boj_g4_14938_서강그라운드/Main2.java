package boj_g4_14938_서강그라운드;

import java.io.*;
import java.util.*;

public class Main2 {
    static int n, m, r;
    static int[] items;
    static int[][] dist;
    static final int INF = 15001; // 거리 최대 값 (15지역 * 100거리)

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

        dist = new int[n+1][n+1]; // 거리 배열 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // 자기 자신은 거리 0
        }

        // 간선 정보 입력
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            dist[start][end] = d;
            dist[end][start] = d;
        }

        br.close();

        // 플로이드 워셜
        for (int k = 1; k <=n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <=n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int maxItems = 0;

        // 각 시작점에서 수색 가능 범위 내 아이템 수 계산
        for (int i = 1; i <= n; i++) {
            int totalItems = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] <= m) {
                    totalItems += items[j];
                }
            }
            maxItems = Math.max(maxItems, totalItems);
        }
        System.out.println(maxItems);

    }
}
