package swea_1974_스도쿠검증;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution2 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("src/swea_1974_스도쿠검증/input.txt"));

		int T = scanner.nextInt();

		// 반복문에 라벨 달아주고
		TC: for (int test_case = 1; test_case <= T; test_case++) {

			System.out.print("#" + test_case + " ");

			int[][] board = new int[9][9];

			// 행 우선순회로 입력 값 받기
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					board[r][c] = scanner.nextInt();
				}
			}

			// 1. 행 체크 - 행 우선순위
			for (int r = 0; r < 9; r++) {
				int[] arr = new int[10];
				for (int c = 0; c < 9; c++) {
					int num = board[r][c];
					if (arr[num] >= 1) {
						// 잘못된 스도쿠 -> TC 라벨로 넘겨주기
						System.out.println(0);
						continue TC;
					}
					arr[num]++;
				}
			}

			// 2. 열 체크
			for (int c = 0; c < 9; c++) {
				int[] arr = new int[10];
				for (int r = 0; r < 9; r++) {
					int num = board[r][c];
					if (arr[num] >= 1) {
						// 잘못된 스도쿠 -> TC 라벨로 넘겨주기
						System.out.println(0);
						continue TC;
					}
					arr[num]++;
				}
			}

			// 3. 격자 체크
			for (int sr = 0; sr < 9; sr += 3) {
				for (int sc = 0; sc < 9; sc += 3) {

					// 각 격자 시작 점들 찾은 후 3x3 탐색
					int[] arr = new int[10];

					for (int r = sr; r < sr + 3; r++) {
						for (int c = sc; c < sc + 3; c++) {
							int num = board[r][c];
							if (arr[num] >= 1) {
								// 잘못된 스도쿠 -> TC 라벨로 넘겨주기
								System.out.println(0);
								continue TC;
							}
							arr[num]++;
						}
					}
				}

			}

			// continue TC; 걸린적 없다면 중복 없는 정확한 스도쿠
			System.out.println(1);

		}

		scanner.close();

	}
}
