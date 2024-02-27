package boj_s1_2615_오목;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	// 바둑 배열 (19 * 19) 생성
	static int[][] baduk = new int[19][19];;

	// 델타 배열 -> 아래로 세로, 오른쪽 가로, 오른쪽 아래 대각선, 오른쪽 위 대각선 
	static int[] dr = { 1, 0, 1, -1 };
	static int[] dc = { 0, 1, 1, 1 };

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("src/boj_s1_2615_오목/input.txt"));

		// 바둑판 값 넣기
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				baduk[i][j] = sc.nextInt();
			}
		}

		// 바둑판 쭉 2차원 배열 순회하면서
		for (int r = 0; r < 19; r++) {
			for (int c = 0; c < 19; c++) {

				// 바둑판 값 하나하나를 player라고 하고,
				int player = baduk[r][c];

				// 만약 player = 검은 돌(1)이나 흰 돌(2) 나오면,
				if (player == 1 || player == 2) {
					// 델타 배열의 방향(아래로 세로, 오른쪽 가로, 오른쪽 아래 대각선, 오른쪽 위 대각선)대로 5개 빙고인지 확인
					for (int d = 0; d < 4; d++) {
						// check 함수 돌린 값이 true 라면 = 검은 돌 승(1) or 흰 돌 승(2)
						if (check(r, c, d, player)) {
							System.out.println(player); // 승리한 돌 숫자 찍고
							System.out.println((r + 1) + " " + (c + 1)); // r, c는 인덱스라 0부터 시작하므로 +1씩 해준다.
							return; // 함수 종료
						}
					}
				}
			}
		}

		// check 함수 돌린 값이 false 라면, 조건 문 빠져 나왔겠지 = 승부 나지 않은 상황
		System.out.println(0);

		sc.close();

	}

	// 매개변수 : 기준(시작점)인 (r,c), 델타 배열 순회할 방향(dir), 검은 돌인지 하얀 돌인지(player)
	static boolean check(int r, int c, int dir, int player) {

		// 5개 연속인 지 확인 해줄 count 변수
		int count = 1; // 일단 (r,c) 위치는 빙고 한 칸 확보 되어서 들어옴

		// 델타 배열 이용해 탐색할 다음 값
		int newr = r + dr[dir];
		int newc = c + dc[dir];

		// 경계 조건 내에 있으면서, player 값과 동일한 동안 반복
		while (newr >= 0 && newc >= 0 && newr < 19 && newc < 19 && baduk[newr][newc] == player) {
			// 같은 player값 나오면 카운트 올려주고,
			count++;

			// 델타 배열 이용한 다음 탐색 값
			newr += dr[dir];
			newc += dc[dir];
		}

		// count = 5가 아니라면 false 반환
		if (count != 5)
			return false;

		// 만약 count = 5 채웠으면, 앞선 값에 player가 또 있지는 않은 지 확인
		int prevr = r - dr[dir];
		int prevc = c - dc[dir];

		// 해당 이전 값이 경계 조건 내에 존재하면서, player 값과 동일하다면 -> 빙고 실패
		if ((prevr >= 0 && prevc >= 0 && prevr < 19 && prevc < 19 && baduk[prevr][prevc] == player))
			return false;

		// 이외의 경우, 빙고 성공
		return true;

	}

}
