package boj_s1_2615_오목;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main2 {

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

	static void checkBingo(int r, int c, int n) {
		int[] dr = { 0, 1, 1, 1 }; // 가로, 세로, 대각선(우하향), 대각선(좌하향) 방향
		int[] dc = { 1, 0, 1, -1 };

		for (int d = 0; d < 4; d++) { // 모든 방향에 대해 검사
			int count = 1; // 현재 돌을 포함해야 하므로 1부터 시작
			int rr = r + dr[d];
			int cc = c + dc[d];

			// 연속된 돌의 개수 세기
			while (rr >= 0 && rr < 19 && cc >= 0 && cc < 19 && baduk[rr][cc] == n) {
				count++;
				rr += dr[d];
				cc += dc[d];
			}

			// 정확히 5개의 돌이 연속됐는지 확인 (6개 이상 연속되는지도 검사)
			if (count == 5) {
				// 연속된 돌의 양 끝이 경계 내에 있고, 연속되지 않는지 검사
				int prevR = r - dr[d];
				int prevC = c - dc[d];
				int nextR = rr;
				int nextC = cc;

				boolean prevCheck = prevR >= 0 && prevR < 19 && prevC >= 0 && prevC < 19 && baduk[prevR][prevC] == n;
				boolean nextCheck = nextR >= 0 && nextR < 19 && nextC >= 0 && nextC < 19 && baduk[nextR][nextC] == n;

				if (!prevCheck && !nextCheck) {
					bingo = n; // 승리 판정
					victoryR = r; // 가장 왼쪽(또는 가장 위)의 돌 위치 저장
					victoryC = c;
					return; // 승리 조건을 만족하면 검사 종료
				}
			}
		}
	}

}
