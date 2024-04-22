package boj_s1_2667_단지번호붙이기;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int N; // 지도 크기
	static int[][] map; // 지도 배열
	static boolean[][] visited; // 방문 배열

	static int count; // 단지 수
	static List<Integer> aparts = new ArrayList<>(); // 단지 별 집의 수 리스트

	// 4방 탐색을 위한 델타 배열 (상하좌우)
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 지도 크기 (5 <= N <= 25)

		map = new int[N][N];
		visited = new boolean[N][N];

		// 단지 번호 입력 받기 (문자열로 주어진 거 숫자로 바꿔)
		for (int i = 0; i < N; i++) {
			String line = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		count = 0; // 단지 수 초기화

		// map 배열을 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 집을 만났는데, 방문 한 적 없다면
				if (map[i][j] == 1 && !visited[i][j]) {
					count++; // 단지 수 추가 하고
					aparts.add(DFS(i, j)); // DFS 호출해서 반환받은 집의 개수를 aparts 리스트에 추가
				}
			}
		}

		Collections.sort(aparts); // 집의 개수 오름차순 정렬

		// 출력
		System.out.println(count); // 총 단지 수
		for (int apartcount : aparts) {
			System.out.println(apartcount); // 집의 개수
		}

		sc.close();

	}

	private static int DFS(int r, int c) {

		// 1. 기저 조건 : 같은 단지가 아니면 집의 수 return 0
		if (!sameApart(r, c))
			return 0;

		// 2. 재귀 조건
		visited[r][c] = true; // 해당 위치 방문 처리
		int houseCount = 1; // 현재 위치의 집 개수 센다.

		// 델타 배열 (상하좌우) 탐색
		for (int d = 0; d < 4; d++) {
			// 새로운 좌표
			int nr = r + dr[d];
			int nc = c + dc[d];

			houseCount += DFS(nr, nc); // 연결된 모든 집의 개수를 합하자
		}

		return houseCount;
	}

	// 같은 단지 인지 여부 판단
	private static boolean sameApart(int r, int c) {

		// map 내에 있고 && 방문 한 적 없고 && 집이면
		if (r >= 0 && r < N && c >= 0 && c < N && !visited[r][c] && map[r][c] == 1) {
			return true; // 같은 단지
		}
		return false;
	}

}
