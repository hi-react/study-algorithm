package swea_1940_가랏RC카;

import java.io.File;
import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(new File("src/swea_1940_가랏RC카/input.txt"));
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {

			// 구현
			int N = sc.nextInt();

			// 변수 초기화
			int speed = 0, distance = 0;
			// final int time = 1; // 변하지 않는 상수값

			// command 개수 만큼 반복
			for (int i = 0; i < N; i++) {

				int command = sc.nextInt();
				int value = 0;

				if (command == 1 || command == 2)
					value = sc.nextInt();

				switch (command) {
				case 1:
					speed += value;
					break;
				case 2:
					speed -= value;
					if (speed < 0)
						speed = 0;
					break;
				}

				distance += speed;

			}

			System.out.println("#" + test_case + " " + distance);

		}
	}
}