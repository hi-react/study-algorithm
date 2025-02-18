package boj_g5_17485_진우의달여행;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M][3]; // map 각 칸 마다 왼쪽 위(0), 위(1), 오른쪽 위(2)에서 온 경우 관리

        // dp 초기화
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if (i == 0) {
                    Arrays.fill(dp[0][j], map[0][j]); // 첫 행 세팅
                } else {
                    Arrays.fill(dp[i][j], Integer.MAX_VALUE);
                }
            }
        }

        GoToMoon();

        // map 마지막 행에서 최소 연료 값 세팅
        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++) {
            for (int d = 0; d < 3; d++) {
                answer = Math.min(answer, dp[N - 1][j][d]);
            }
        }

        br.close();
        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }

    // [method] 지구 -> 달로 가는 DP
    static void GoToMoon() {
        // 두 번째 행부터 실행
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 왼쪽 위(0)에서 오는 경우 => 이전 방향 위(1)에서 온 거랑 오른쪽 위(2)에서 온 것만 비교
                if (j > 0) {
                    dp[i][j][0] = Math.min(dp[i][j][0], Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + map[i][j]);
                }
                // 위(1)에서 오는 경우 => 이전 방향 왼쪽 위(0)랑 오른쪽 위(2)에서 온 것만 비교
                dp[i][j][1] = Math.min(dp[i][j][1], Math.min(dp[i-1][j][0], dp[i-1][j][2]) + map[i][j]);
                // 오른쪽 위(2)에서 오는 경우 => 이전 방향 왼쪽 위(0)랑 위(1)에서 온 것만 비교
                if (j < M -1) {
                    dp[i][j][2] = Math.min(dp[i][j][2], Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + map[i][j]);
                }
            }
        }
    }
}
