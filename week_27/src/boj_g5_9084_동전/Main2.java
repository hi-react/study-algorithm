package boj_g5_9084_동전;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 1. 문제 분석
 * 주어진 동전 종류들 (N가지) 이용
 * 특정 금액(M원)을 만들 수 있는 경우의 수 반환
 *
 *
 * 2. 문제 풀이
 * 1) 2차원 DP 테이블(N*M) 만들어서 앞선 행의 계산 결과 메모이제이션
 * => dp[i][j] = i개의 동전 종류로 j원을 만들 수 있는 경우의 수
 *
 * 2) 동전 종류 별(k원)로 DP 테이블 업데이트 = 이전 단계에서 구한 값 + (j-k)원 만드는 경우의 수
 * ex) 1원 -> dp[1][j] = dp[0][j] + dp[1][j-1]
 * ex) 2원 -> dp[2][j] = dp[1][j] + dp[2][j-2]
 * ex) 5원 -> dp[3][j] = dp[2][j] + dp[3][j-5]
 *
 * 3) 사실 상 최종 반환 해야 할 값은 DP 테이블의 최종 점인 dp[N][M] 뿐!
 * => 1차원으로 단순화 하고, 바로 바로 그 자리에 갱신!
 * => dp[i] = dp[i] + dp[i-k]
 *
 *
 * 3. 알고리즘: DP(다이나믹 프로그래밍, 동적 계획법)
 * : 큰 문제를 작은 문제로 쪼개서 푸는 방법
 * 메모이제이션 : N번째 결과를 구하기 위해 (N - 1)번째 결과를 구하는 점화식 필요
 * 이미 구한 정답을 작은 문제의 결과로 배열에 저장하고, 필요할 때 재사용 : O(1)
 * => 한 번 푼 것을 여러 번 다시 풀지 않으므로 효율적
 *
 */

public class Main {

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

            // 2. dp 배열 생성
            int[] dp = new int[goal + 1];
            dp[0] = 1; // 0원을 만드는 방법은 1가지

            // 3. dp 배열 업데이트
            for (int coin: coins) {
                for (int i = coin; i <= goal; i++) {
                    dp[i] = dp[i] + dp[i - coin];
                }
            }

            bw.write(dp[goal] + "\n");


        }

        br.close();
        bw.flush();
        bw.close();
    }
}
