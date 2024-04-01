package boj_g4_1197_최소스패닝트리;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 최소 신장 트리(MST) 중 크루스칼 알고리즘
public class Main {

	static int[] p; // 대표 배열

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g4_1197_최소스패닝트리/input.txt"));
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt(); // 정점 수 (1부터 시작)
		int E = sc.nextInt(); // 간선 수

		// 대표 배열
		p = new int[V + 1];

		// 간선 배열 (2차원 배열 이용)
		int[][] edges = new int[E][3];

		// 입력 받기
		for (int i = 0; i < E; i++) {
			edges[i][0] = sc.nextInt(); // 시작 정점
			edges[i][1] = sc.nextInt(); // 끝 정점
			edges[i][2] = sc.nextInt(); // 가중치
		}

		// 크루스칼 알고리즘 1단계 : 간선 배열 edges를 가중치 기준으로 오름차순 정렬
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2]; // 앞에 꺼가 더 크면 양수 나와서 순서 바꿈 = 오름차순 정렬
			}
		});

		// 크루스칼 알고리즘 2단계 : (V-1)개 간선 뽑기! (상호 배타 집합 이용: path compression)

		// 1) make-set : 대표 배열인 p배열을 일단 나 자신으로 초기화
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}

		// 2) 변수 설정
		int ans = 0; // 반환 할 최소 가중치 합
		int pick = 0; // 선택한 간선의 수 (V-1개 까지 고를 거임)

		// 3) 오름차순 정렬해 놓은 간선 배열 순회하면서 (V-1)개 간선 뽑기
		for (int i = 0; i < E; i++) {

			// 3-1) find-set : 대표 찾기
			int pStart = findset(edges[i][0]); // 시작 정점의 대표
			int pEnd = findset(edges[i][1]); // 끝 정점의 대표

			// 3-2) union : 시작 정점의 대표와 끝 정점의 대표 비교하여 다르다면, 하나의 그룹으로 합친다.
			if (pStart != pEnd) {
				p[pEnd] = pStart; // 이게 union 과정!

				// 3-3) 합쳤으면 반환할 최소 가중치 합 더해주고, 선택한 간선 수 count up
				ans += edges[i][2];
				pick++;
			}

			// 3-4) 백트래킹 : V-1개 간선 골랐으면, 더이상 안 봐도 됨!
			if (pick == V - 1)
				break;
		}

		System.out.println(ans);

		sc.close();
	}

	private static int findset(int x) {

		if (x != p[x]) { // 내가 나 자신의 대표가 아니라면,
			p[x] = findset(p[x]); // 그 대표를 찾아 (재귀 호출)
		}
		return p[x]; // 대표 반환
	}

}
