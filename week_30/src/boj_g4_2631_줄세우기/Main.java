package boj_g4_2631_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;

/**
 * 1. 문제 분석
 * 임의로 줄 서 있는 N명의 아이들, 번호가 섞여 있다.
 * 번호 순서 대로 줄 세워야 한다. (오름 차순 정렬)
 *
 * 목표 : 위치 옮기는 아이들 수의 최소 값 구하기
 *
 * 2. 문제 풀이
 * 1) 개념
 * 최대한 길게 오름 차순 정렬된 구간을 찾는다. => LIS (최장 증가 부분 수열)
 * 이 구간을 제외한 나머지 아이들 만 이동 시킨다.
 *
 * 최소 이동 해야 할 아이들 수 = 전체 아이들 수 - LIS 길이
 *
 * 2) 과정
 * dp[i] : i번째 아이를 마지막 으로 하는 최장 증가 부분 수열(LIS)의 길이
 *
 * dp 배열 초기화 : 자기 자신 만으로 하나의 부분 수열이 될 수 있으므로, dp[i] = 1
 *
 * 점화 식 : i번째 아이에 대해, 그 이전 아이들(j = 0 ~ i - 1번째)과 비교 하며 부분 수열 확장
 * => j번째 아이 번호 < i번째 아이 번호 => i번째 아이를 마지막으로 하는 LIS를 확장 가능
 * => dp 배열 갱신 : dp[i] = max(dp[i], dp[j] + 1)
 *
 * 결론 : dp 배열 중 가장 큰 값 = LIS 길이
 *
 * 3. 알고리즘 : DP(다이나믹 프로그래밍)
 *
 * 4. 문제 유형 : LIS(최장 증가 부분 수열, longest increasing subsequence)
 * => 풀이 방법으로 2가지 알고리즘 활용해 볼 수 있다.
 *
 * 1) DP : O(N^2) => 지금 이 걸로 품
 * 2) 이분 탐색 : O(NlogN)
 *
 */

public class Main {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
//        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];

        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        // 2. LIS(최장 증가 부분 수열)
        int[] dp = new int[N]; // dp[i] = i번째 아이를 마지막 으로 하는 최장 증가 부분 수열의 길이
        int maxLength = 0;

        for (int i = 0; i <  N; i++) {
            dp[i] = 1; // 자기 자신 만으로 부분 수열을 이룰 수 있으므로, 1로 초기화

            // i번째 아이 기준, 앞쪽에 있는 애들 확인 하면서
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i]) { // 앞쪽 애(j) 번호 < 기준이 되는 i번쨰 아이 번호 라면,
                    // dp[i] : 해당 i번째 아이를 마지막으로 하는 LIS(최장 증가 부분 수열) 길이 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                maxLength = Math.max(maxLength, dp[i]);
            }
        }

        System.out.println(N - maxLength);

        br.close();

    }
}
