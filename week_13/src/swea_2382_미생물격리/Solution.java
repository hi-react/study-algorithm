package swea_2382_미생물격리;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

	static int N, M, K; // 한 변에 있는 셀 개수, 격리 시간, 미생물 군집 개수

	static int[][] countVirus; // 미생물 개수 
	static int[][] moveTo; // 이동 방향 
	static int[][] maxVirus; // 최대 미생물 수 

	static int result; // 최종 반환할 M시간 후 남아 있는 미생물 수 총 합

	// 델타 배열 : 빈값 상1 하2 좌3 우4
	static int[] dr = { 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, -1, 1 };

	// 미생물 군집 정보
	static class Virus {
		int r, c; // 좌표
		int cnt; // 미생물 수
		int dir; // 이동 방향

		public Virus(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}

	static List<Virus> viruses; // 모든 미생물 군집 정보 리스트

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("src/swea_2382_미생물격리/input.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {

			// 입력 값 받기
			N = sc.nextInt(); // 한 변에 있는 셀 개수
			M = sc.nextInt(); // 격리 시간
			K = sc.nextInt(); // 미생물 군집 개수

			viruses = new ArrayList<>(); // 모든 미생물 군집 정보 리스트 (초기화)

			// 미생물 군집들의 정보 입력 받기
			for (int i = 0; i < K; i++) {
				int r = sc.nextInt();
				int c = sc.nextInt();
				int cnt = sc.nextInt();
				int dir = sc.nextInt();

				viruses.add(new Virus(r, c, cnt, dir)); // 리스트에 추가해줌
			}

			result = 0; // 최종 반환 할 남은 미생물 합 (초기화)

			// 격리 시간 M번 만큼 이동한다.
			for (int i = 0; i < M; i++) {
				go();
			}

			count();

			System.out.println("#" + tc + " " + result);
		}

		sc.close();

	}

	// [메인 1] 미생물 한 칸씩 이동하는 메서드
	static void go() {

		countVirus = new int[N][N]; // 미생물 개수 (초기화 0) 
		moveTo = new int[N][N]; // 이동 방향 (초기화 0) 
		maxVirus = new int[N][N]; // 최대 미생물 수 (초기화 0) 

		// 1. 미생물 군집 개수만큼 반복하면서 처리
		for (int i = 0; i < viruses.size(); i++) {

			Virus v = viruses.get(i); // 2. viruses 리스트에서 미생물 군집 하나씩 가져옵니다.

			// 3. 해당 미생물 군집 이동시킬 위치 : 델타 배열 이용해, 이동 방향 + 1로 뽑아냅니다.
			int nr = v.r + dr[v.dir];
			int nc = v.c + dc[v.dir];
			int cnt = v.cnt; // 미생물 수
			int dir = v.dir; // 이동 방향

			// 4. 이동
			// 이동한 좌표가 가장자리가 아니라면, 해당 미생물 군집의 위치 이동하고 끝!
			if (!boundary(nr, nc)) {
				v.r = nr;
				v.c = nc;
			} else { // 가장자리라면, 해당 미생물 군집의 위치 이동! 과 함께 
				v.r = nr;
				v.c = nc;
				v.cnt = cnt / 2; // 미생물 수를 반으로 줄이고,
				v.dir = turn(dir); // 이동 방향을 반대로 돌린다.
			}

			// 5. 이동 방향 설정을 위한 moveTo 2차원 배열 갱신 (미생물 군집이 겹치는 경우까지 처리 !!)
			if (maxVirus[v.r][v.c] <= v.cnt) { // 앞서 저장되어 있던 미생물 최대 수보다 < 현재 확인 중인 미생물 군집의 숫자가 더 크면,
				maxVirus[v.r][v.c] = v.cnt; // 최대 미생물 수 갱신
				moveTo[v.r][v.c] = v.dir; // 이동 방향을 현재 미생물 군집(max)의 방향으로 결정
			}
		}

		// 6. 이동이 끝났으면, 미생물 군집 개수만큼 반복하면서
		for (int i = 0; i < viruses.size(); i++) {
			Virus v = viruses.get(i); // 7. viruses 리스트에서 미생물 군집 하나씩 가져옵니다.
			countVirus[v.r][v.c] += v.cnt; // 8. 미생물 수의 합 저장 
		}

		viruses = new ArrayList<>(); // 9. 바이러스 리스트 다시 초기화!

		// 10. 미생물 수의 합이 저장되어 있는 countVirus 배열 돌면서, 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (countVirus[i][j] != 0) { // 11. 미생물이 존재한다면, 미생물 군집 리스트 새로 채우자!
					viruses.add(new Virus(i, j, countVirus[i][j], moveTo[i][j])); // 해당 위치, 합한 미생물 수, 이동 방향
				}
			}
		}
	}

	// [부가 1] 가장 자리인지 확인하는 메서드
	static boolean boundary(int r, int c) {
		return r == 0 || r == N - 1 || c == 0 || c == N - 1;
	}

	// [부가 2] 방향 반대로 바꿔주는 메서드
	static int turn(int d) {
		switch (d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		default:
			return 0;
		}
	}

	// [메인 2] 격리 시간 M시간만큼 다 돌고, 남은 미생물 수 총합 구하기 
	static void count() {
		int sum = 0;
		for (int i = 0; i < viruses.size(); i++) {
			sum += viruses.get(i).cnt;
		}
		result = sum;
	}

}
