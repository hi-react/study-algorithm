package swea_1961_숫자배열회전;

import java.io.File;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(new File("src/swea_1961_숫자배열회전/input.txt"));
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = sc.nextInt();

			// 초기 배열
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			// 90도 회전
			int[][] arr90 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr90[j][N - 1 - i] = arr[i][j];
				}
			}

			// 180도 회전
			int[][] arr180 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr180[N - 1 - i][N - 1 - j] = arr[i][j];
				}
			}

			// 270도 회전
			int[][] arr270 = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr270[N - 1 - j][i] = arr[i][j];
				}
			}

			// 출력
			System.out.println("#" + test_case);

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr90[i][j]);
				}
				System.out.print(" ");
				for (int j = 0; j < N; j++) {
					System.out.print(arr180[i][j]);
				}
				System.out.print(" ");
				for (int j = 0; j < N; j++) {
					System.out.print(arr270[i][j]);
				}
				System.out.println();
			}

		}
	}
}