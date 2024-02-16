package swea_1979_어디에단어가들어갈수있을까;

import java.io.File;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(new File("src/swea_1979_어디에단어가들어갈수있을까/input.txt"));
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();
			int K = sc.nextInt();

			int ans = 0;

			// 배열 생성
			int[][] puzzle = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					puzzle[i][j] = sc.nextInt();
				}
			}

			// 1. 가로 줄 확인
			for (int i = 0; i < N; i++) {

				int count = 0;

				for (int j = 0; j < N; j++) {
					if (puzzle[i][j] == 1) // 1 나오는 빈 공간인 경우
						count++;
					else {
						if (count == K)
							ans++; // 현재까지 K 맞췄으면 ++
						count = 0; // 초기화
					}
				}

				if (count == K)
					ans++;
			}

			// 2. 세로 줄 확인
			for (int j = 0; j < N; j++) {

				int count = 0;

				for (int i = 0; i < N; i++) {
					if (puzzle[i][j] == 1) // 1 나오는 빈 공간인 경우
						count++;
					else { // 0 나오는 막힌 공간인 경우
						if (count == K)
							ans++;
						count = 0;
					}
				}
				if (count == K) // 마지막이 1로 끝났을 경우
					ans++;
			}

			// 출력
			System.out.print("#" + test_case + " ");
			System.out.println(ans);

		}
	}
}