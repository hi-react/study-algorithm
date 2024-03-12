package boj_s3_2579_계단오르기;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 입력 받기
		int n = sc.nextInt(); // 계단 수

		// 계단 배열 값 넣어주기
		int[] stairs = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			stairs[i] = sc.nextInt();
		}

		sc.close();

		// [예외 처리 추가] 계단이 1개 인 경우
		if (n == 1) {
			System.out.println(stairs[1]);
			return;
		}

		// 각 계단 도착 시 얻을 수 있는 최대 값 저장하는 dp 배열 생성
		int[] dp = new int[n + 1];

		// 첫 번째 계단의 최대 점수 넣어준다.
		dp[1] = stairs[1];

		// 두 번째 계단의 최대 점수도 넣어준다.
		dp[2] = stairs[1] + stairs[2];

		// [예외 처리 추가] 계단이 세 개 이상 있는 경우에만 실행하도록 !
		if (n > 2) {
			// 세 번째 계단의 최대 점수도 넣어준다. (이전 계단 밟은 경우 vs 밟지 X)
			dp[3] = Math.max(stairs[2] + stairs[3], stairs[1] + stairs[3]);

			// 네 번째 계단 부터는 점화식을 사용한다.
			for (int i = 4; i <= n; i++) {
				// 이전 계단 밟은 경우 vs 밟지 X 경우 중 더 큰 값으로 dp[i]에 넣어준다.
				dp[i] = Math.max(dp[i - 3] + stairs[i - 1] + stairs[i], dp[i - 2] + stairs[i]);
			}
		}

		// 마지막 계단에 도착했을 때, 총 점수 최대 값 구한 것 반환
		System.out.println(dp[n]);

	}

}
