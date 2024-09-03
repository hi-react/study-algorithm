package boj_g5_9084_동전;

import java.io.*;
import java.util.StringTokenizer;

/**
 * dp 배열 1차원으로 안 줄이고, 2차원 그대로 해본 거
 * => [2차원] 메모리: 16028KB / 시간: 124ms
 * => [1차원] 메모리: 14756KB / 시간: 108ms
 *
 */

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int t = 0; t < T; t++) {

            // 1. 입력 값 받기
            int N = Integer.parseInt(br.readLine()); // 동전 종류의 개수
            StringTokenizer st = new StringTokenizer(br.readLine()); // 동전 종류 뭐뭐 있는 지
            int goal = Integer.parseInt(br.readLine()); // 최종 목표 액

            int[] coins = new int[N];
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            // 2. 2차원 dp 배열 생성
            int[][] dp = new int[N + 1][goal + 1];
            for (int i = 0; i <= N; i++) {
                dp[i][0] = 1; // 0원을 만드는 방법은 1가지
            }

            // 3. dp 배열 업데이트
            for (int i = 1; i <= N; i++) { // 세로 : 동전 종류들
               int coin = coins[i - 1];
               for (int j = 1; j <= goal; j++) { // 가로 : 목표 액까지의 모든 금액
                   if (j >= coin) {
                       dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                   } else {
                       dp[i][j] = dp[i - 1][j];
                   }
               }
            }

            bw.write(dp[N][goal] + "\n");


        }

        br.close();
        bw.flush();
        bw.close();
    }
}
