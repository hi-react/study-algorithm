package boj_g4_14502_연구소;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, M;
	static int[][] factory;
	static int maxSafeArea = 0; // 초기화

	public static void main(String[] args) {

		// 입력 값 받기
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		// 연구소는 N * M 크기의 2차원 배열
		factory = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				factory[i][j] = sc.nextInt();
			}
		}

		// 벽을 세워 볼 건데.. DFS 돌릴 것. 벽 개수 0에서 시작
		buildWalls(0);

		System.out.println(maxSafeArea);

		sc.close();

	}

	// 1. 벽 세우기 method
	private static void buildWalls(int count) {
		// 탈출 조건 - 벽 3개 다 세우면,
		if (count == 3) {
			// factory 배열을 copy 해서 임시 배열 만든다.
			int[][] tmp = new int[N][M];
			for (int i = 0; i < N; i++) {
				tmp[i] = Arrays.copyOf(factory[i], M);
			}
			// maxSafeArea 갱신 - 임시 배열 tmp 가지고 안전 영역 구해서 비교
			maxSafeArea = Math.max(maxSafeArea, getSafeArea(tmp));
			return;
		}

		// 벽 아직 3개 안 세웠으면, 벽 세워야 한다.
		// factory 배열을 돌면서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (factory[i][j] == 0) { // 빈 공간 이라면,
					factory[i][j] = 1; // 벽 세우고
					buildWalls(count + 1); // 다음 벽 세우기 위해 재귀 돌린다. [주의] 이거 count++ (X)
					factory[i][j] = 0; // 재귀 다 돌고 나오면, 세웠던 벽 없애기 (백트래킹)
				}
			}
		}
	}

	// 2. 안전 영역 구하는 method
	private static int getSafeArea(int[][] tmp) {
		// 반환 해줄 안전 영역 값 초기화
		int count = 0;

		// 임시 배열 tmp 돌면서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 만약에 2가 나오면 바이러스 퍼뜨려야 해
				if (tmp[i][j] == 2) {
					spreadVirus(i, j, tmp);
				}
			}
		}

		// 바이러스 퍼뜨리기 끝났으면, 이제 tmp 배열에 남은 0 개수 count 해주자
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tmp[i][j] == 0)
					count++;
			}
		}

		return count;
	}

	// 3. 바이러스 퍼뜨리는 method
	private static void spreadVirus(int x, int y, int[][] tmp) {

		// 바이러스 퍼뜨리기 위한 델타 배열(상, 하, 좌, 우) 정의
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		// 델타 배열 돌면서, 바이러스(2)가 있던 해당 위치(x, y) 기준으로 다음 좌표 생성
		for (int i = 0; i < 4; i++) {
			int newx = x + dx[i];
			int newy = y + dy[i];

			// 경계 조건 확인 & tmp 배열의 해당 위치가 비어 있다면,
			if (newx >= 0 && newy >= 0 && newx < N && newy < M && tmp[newx][newy] == 0) {
				// 바이러스 퍼뜨린다 = 해당 위치 2로 바꾼다.
				tmp[newx][newy] = 2;
				// 해당 위치 기준으로 바이러스 또 퍼뜨린다. (재귀)
				spreadVirus(newx, newy, tmp);
			}
		}

	}

}
