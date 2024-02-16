package swea_1859_백만장자프로젝트;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/swea_1859_백만장자프로젝트/input.txt"));
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			// 배열 크기(N) 받아와서 물건 배열 초기화
			int N = sc.nextInt();
			int[] goods = new int[N];

			// 물건 배열에 값 넣어주기
			for (int i = 0; i < N; i++) {
				goods[i] = sc.nextInt();
			}

			int start = 0;
			long profit = 0;

			while (start < N) {

				int maxIndex = start;

				for (int i = start + 1; i < N; i++) {
					if (goods[i] > goods[maxIndex]) {
						maxIndex = i;
					}
				}

				for (int j = start; j < maxIndex; j++) {
					profit += (goods[maxIndex] - goods[j]);
				}

				start = maxIndex + 1;
			}

			// 출력
			System.out.println("#" + test_case + " " + profit);

		}
	}
}