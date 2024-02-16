package swea_1954_달팽이숫자;

import java.io.File;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(new File("src/swea_1954_달팽이숫자/input.txt"));
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();

			int[][] arr = new int[N][N];

			// 델타 배열 정의
			int[] dr = { 0, 1, 0, -1 };
			int[] dc = { 1, 0, -1, 0 };

			int r = 0, c = 0, direction = 0, num = 1;

			while (num <= N * N) {
				arr[r][c] = num++;

				int nr = r + dr[direction];
				int nc = c + dc[direction];

				// 경계 조건 및 이미 채워진 위치 확인
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) {
					direction = (direction + 1) % 4;
					nr = r + dr[direction];
					nc = c + dc[direction];
				}

				r = nr;
				c = nc;

			}

			System.out.println("#" + test_case);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}

		}
	}
}