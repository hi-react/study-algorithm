// 실패 

package boj_s1_2615_오목;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static int bingo; // bingo 값
	static int[][] baduk; // 바둑 배열
	static int victoryR; // 이겼을 때의 빙고 row 시작점
	static int victoryC; // 이겼을 때의 빙고 column 시작점

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s1_2615_오목/input.txt"));

		// 19 * 19 배열 생성
		baduk = new int[19][19];

		// 바둑판 값 넣기
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				baduk[i][j] = sc.nextInt();
			}
		}

		// bingo 최초 값 = 0 (일단 승부가 결정되지 않았다고 가정)
		bingo = 0;

		// 바둑판 값 하나하나 쭉 순회합니다.
		Test: for (int badukR = 0; badukR < 19; badukR++) {
			for (int badukC = 0; badukC < 19; badukC++) {
				// 바둑판 값이 1이라면,
				if (baduk[badukR][badukC] == 1) {
					checkBingo(badukR, badukC, 1);
					if (bingo == 1)
						break Test;
				} else if (baduk[badukR][badukC] == 2) {
					checkBingo(badukR, badukC, 2);
					if (bingo == 2)
						break Test;
				}
			}
		}

		// 승부가 나지 않은 경우
		if (bingo == 0) {
			System.out.println(bingo);
		} else { // 검은 돌 승(1) 또는 하얀 돌 승(2)
			System.out.println(bingo);
			System.out.println((victoryR + 1) + " " + (victoryC + 1)); // 인덱스는 0부터니까 +1 처리
		}

		sc.close();

	}

	static void checkBingo(int r, int c, int n) { // 시작점: 기준이 되는 r과 c, n : 검은 돌인지 흰 돌인지

		// 가로 빙고 체크
		if (c + 4 < 19) { // 경계 범위 내에 있다면,
			int count = 0;
			testR: for (int i = 1; i <= 4; i++) {
				// 들어온 n 값과 다른 값이 있다면,
				if (baduk[r][c + i] != n)
					break testR; // 해당 반복문 끝낸다. 빙고 불가!
				// 들어온 n 값과 같은 값이라면,
				else if (baduk[r][c + i] == n) {
					// 양쪽으로 n 값이 또 있으면 해당 반복문 끝낸다. 빙고 불가!
					if ((c - 1 >= 0 && baduk[r][c - 1] == n) || (c + 5 < 19 && baduk[r][c + 5] == n)) {
						break testR;
					}
					// 그 외의 경우 빙고 count
					count++;
				}

			}
			// 다 돌고 나왔는데, count = 4라면 빙고지.
			if (count == 4) {
				bingo = n; // n이 이김!
				victoryR = r; // 그 때의 r 저장
				victoryC = c; // 그 때의 c 저장
			}
		}

		// 세로 빙고 체크
		if (r + 4 < 19) { // 경계 범위 내에 있다면,
			int count = 0;
			testC: for (int i = 1; i <= 4; i++) {
				// 들어온 n 값과 다른 값이 있다면,
				if (baduk[r + i][c] != n)
					break testC; // 해당 반복문 끝낸다. 빙고 불가!
				// 들어온 n 값과 같은 값이라면,
				else if (baduk[r + i][c] == n) {
					// 양쪽으로 n 값이 또 있으면 해당 반복문 끝낸다. 빙고 불가!
					if ((r - 1 >= 0 && baduk[r - 1][c] == n) || (r + 5 < 19 && baduk[r + 5][c] == n)) {
						break testC;
					}
					// 그 외의 경우 빙고 count
					count++;
				}
			}
			// 다 돌고 나왔는데, count = 4라면 빙고지.
			if (count == 4) {
				bingo = n; // n이 이김!
				victoryR = r; // 그 때의 r 저장
				victoryC = c; // 그 때의 c 저장
			}
		}

		// 왼쪽으로 대각선 빙고 체크
		if (r + 4 < 19 && c - 4 >= 0) { // 경계 범위 내에 있다면,
			int count = 0;
			testX1: for (int i = 1; i <= 4; i++) {
				// 들어온 n 값과 다른 값이 있다면,
				if (baduk[r + i][c - i] != n)
					break testX1; // 해당 반복문 끝낸다. 빙고 불가!
				// 들어온 n 값과 같은 값이라면, 빙고 count
				else if (baduk[r + i][c - i] == n) {
					// 양쪽으로 n 값이 또 있으면 해당 반복문 끝낸다. 빙고 불가!
					if ((r + 5 < 19 && c - 5 >= 0 && baduk[r + 5][c - 5] == n)
							|| (r - 1 >= 0 && c + 1 < 19 && baduk[r - 1][c + 1] == n)) {
						break testX1;
					}
					// 그 외의 경우 빙고 count
					count++;
				}
			}
			// 다 돌고 나왔는데, count = 4라면 빙고지.
			if (count == 4) {
				bingo = n; // n이 이김!
				victoryR = r; // 그 때의 r 저장
				victoryC = c; // 그 때의 c 저장
			}
		}

		// 오른쪽으로 대각선 빙고 체크
		if (r + 4 < 19 && c + 4 < 19) { // 경계 범위 내에 있다면,
			int count = 0;
			testX2: for (int i = 1; i <= 4; i++) {
				// 들어온 n 값과 다른 값이 있다면,
				if (baduk[r + i][c + i] != n)
					break testX2; // 해당 반복문 끝낸다. 빙고 불가!
				// 들어온 n 값과 같은 값이라면, 빙고 count
				else if (baduk[r + i][c + i] == n) {
					// 양쪽으로 n 값이 또 있으면 해당 반복문 끝낸다. 빙고 불가!
					if (r + 5 < 19 && c + 5 < 19 && baduk[r + 5][c + 5] == n
							|| (r - 1 >= 0 && c - 1 >= 0 && baduk[r - 1][c - 1] == n)) {
						break testX2;
					}
					// 그 외의 경우 빙고 count
					count++;
				}
			}
			// 다 돌고 나왔는데, count = 4라면 빙고지.
			if (count == 4) {
				bingo = n; // n이 이김!
				victoryR = r; // 그 때의 r 저장
				victoryC = c; // 그 때의 c 저장
			}
		}

	}

}
