package swea_1859_백만장자프로젝트;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/swea_1859_백만장자프로젝트/input.txt"));

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();
			int[] arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			long ans = 0;

			// 뒤에서 부터 지금까지 나온 최댓값 갱신
			int[] max = new int[N];

			max[N - 1] = arr[N - 1]; // 마지막 날의 최대값은 자기 자신의 값

			for (int i = N - 2; i >= 0; i--) {
				if (arr[i] > max[i + 1]) {
					max[i] = arr[i];
				} else {
					max[i] = max[i + 1];
				}

				// 최대값과 현재 날짜 값 뺀 친구를 다 더해주면 답!
				ans += max[i] - arr[i];
			}

			System.out.println("#" + test_case + " " + ans);

		}

		sc.close();
	}

}
