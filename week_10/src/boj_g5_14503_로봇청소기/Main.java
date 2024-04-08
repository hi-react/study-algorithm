package boj_g5_14503_로봇청소기;

import java.util.Scanner;

public class Main {

	static int N, M; // 방 크기
	static int[][] room; // 방

	static int maxCount; // 최종 반환할 가장 많이 치운 수

	// 반 시계 방향으로 돌리는 배열
	// 북(0)->서(3), 동(1)->북(0), 남(2)->동(1), 서(3)->남(2)
	static int[] left = { 3, 0, 1, 2 };

	// 이동하기 위한 배열 - 위의 방향 돌리는 배열 순서에 맞춰서 (북, 동, 남, 서)
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		room = new int[N][M];

		int r = sc.nextInt();
		int c = sc.nextInt();
		int d = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				room[i][j] = sc.nextInt();
			}
		}

		dfs(r, c, d); // 청소 시작 좌표 (r, c)와 이동 방향(d)
		System.out.println(maxCount);

		sc.close();
	}

	private static void dfs(int r, int c, int dir) {

		// 현재 좌표 아직 안치웠냐?
		if (room[r][c] == 0) {
			room[r][c] = 2; // 청소했으면 2라고 두자.
			maxCount++; // 치웠으니까 count up
		}

		// 현재 칸 주위의 4방을 모두 확인해야 해 (북, 동, 남, 서)
		for (int i = 0; i < 4; i++) {

			dir = left[dir]; // 현재 바라보고 있는 방향 (왼쪽으로 90도 회전)

			// 새로운 좌표 찍어보자
			int nr = r + dr[dir];
			int nc = c + dc[dir];

			// 새로운 좌표가 경계 조건 내 && 아직 청소 안했으면,
			if (nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] == 0) {
				dfs(nr, nc, dir); // 해당 방향으로 이동하여 청소 하자 (재귀)
				return; // 해당 방향으로 이동해 청소 시작했으면, 기존 dfs에서 다른 방향 탐색 안해도 되니까 해당 dfs 종료
			}

		}

		// 여기로 왔다는 것은, 현재 좌표 기준 4방향을 모두 확인했지만, 청소할 수 있는 칸이 없었다. => 후진!

		// 후진할 방향 설정
		int backdir = (dir + 2) % 4;

		// 후진한 좌표
		int br = r + dr[backdir];
		int bc = c + dc[backdir];

		// 후진한 좌표가 경계 조건 내 && 벽이 아니라면,
		if (br >= 0 && br < N && bc >= 0 && bc < M && room[br][bc] != 1) {
			dfs(br, bc, dir); // 후진해서 이동
		} else {
			return; // 후진했는데, 경계 벗어나거나 벽(1)이면 종료
		}

	}

}
