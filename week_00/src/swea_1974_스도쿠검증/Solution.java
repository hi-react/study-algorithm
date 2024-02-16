package swea_1974_스도쿠검증;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/swea_1974_스도쿠검증/input.txt"));
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			// 스도쿠 데이터 저장할 2차원 배열
			int[][] sudoku = new int[9][9];

			// 스도쿠 데이터 읽기
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sudoku[i][j] = sc.nextInt();
				}
			}

			// 로직 구현
			int testRow = 0;
			int testCol = 0;
			int testRect = 0;

			int ans = 0;

			// 0. 검증용 배열 초기화
			List<Integer> test = new ArrayList<>();
			for (int k = 1; k < 10; k++) {
				test.add(k);
			}

			// 1. 가로 줄 검증
			for (int i = 0; i < 9; i++) {

				// 검증용 배열 초기화
				test.clear();
				for (int k = 1; k < 10; k++) {
					test.add(k);
				}

				// 비교
				for (int j = 0; j < 9; j++) {
					if (test.contains(sudoku[i][j])) {
						test.remove(Integer.valueOf(sudoku[i][j]));
					}
				}
				if (test.isEmpty())
					testRow++;
			}

			// 2. 세로 줄 검증
			for (int j = 0; j < 9; j++) {

				// 검증용 배열 초기화
				test.clear();
				for (int k = 1; k < 10; k++) {
					test.add(k);
				}

				// 비교
				for (int i = 0; i < 9; i++) {
					if (test.contains(sudoku[i][j])) {
						test.remove(Integer.valueOf(sudoku[i][j]));
					}
				}
				if (test.isEmpty())
					testCol++;
			}

			// 3. 정사각형 검증
			for (int totalRow = 0; totalRow < 9; totalRow += 3) {
				for (int totalCol = 0; totalCol < 9; totalCol += 3) {

					// 검증용 배열 초기화
					test.clear();
					for (int k = 1; k < 10; k++) {
						test.add(k);
					}

					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if (test.contains(sudoku[totalRow + i][totalCol + j])) {
								test.remove(Integer.valueOf(sudoku[totalRow + i][totalCol + j]));
							}
						}
					}

					if (test.isEmpty())
						testRect++;
				}
			}

			// 결과값 출력
			System.out.print("#" + test_case + " ");
			if (testRow == 9 && testCol == 9 && testRect == 9)
				ans++;
			System.out.println(ans);

		}
	}
}