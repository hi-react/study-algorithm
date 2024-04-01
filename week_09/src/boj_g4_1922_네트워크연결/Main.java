package boj_g4_1922_네트워크연결;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// 최소 신장 트리 -> 크루스칼 알고리즘
// 모든 컴퓨터(정점) 연결 + 최소 비용
public class Main {

	static int[] p; // 대표 배열

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g4_1922_네트워크연결/input.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 컴퓨터 수 (정점 수) -> 1부터 시작
		int M = sc.nextInt(); // 연결할 수 있는 선의 수 (간선 수)

		p = new int[N + 1]; // 대표 배열 (1부터 시작하니까 인덱스 편하게 N+1)

		// 간선 배열(2차원)
		int[][] edges = new int[M][3];

		// 간선 배열 입력 받기
		for (int i = 0; i < M; i++) {
			edges[i][0] = sc.nextInt(); // 시작 컴퓨터
			edges[i][1] = sc.nextInt(); // 끝 컴퓨터
			edges[i][2] = sc.nextInt(); // 연결 비용(가중치)
		}

		// 크루스칼 알고리즘 1단계 : 간선의 가중치 기준으로 오름차순 정렬한다.
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] e1, int[] e2) {
				return e1[2] - e2[2]; // 앞이 뒤보다 크면 양수여서 순서를 바꾸므로 = 오름차순
			}
		});

		// 크루스칼 알고리즘 2단계 : 간선을 (M-1)개 뽑는다. (cycle 안됨)
		// 상호 배타 집합 사용 => path compression 이용!

		// 1) make-set : 모든 정점(컴퓨터)에 대해 자기 자신을 대표로 초기화
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}

		int ans = 0; // 최종 반환할 '최소 가중치' 더해줄 변수
		int pick = 0; // (M-1)개의 간선 수 체크용

		// 2) find-set : 모든 간선을 순회하면서, 같은 대표를 공유하고 있는 지 확인
		for (int i = 0; i < M; i++) {

			int pStart = findset(edges[i][0]); // 시작 컴퓨터(정점)의 대표 찾기
			int pEnd = findset(edges[i][1]); // 끝 컴퓨터(정점)의 대표 찾기

			// 3) union : 서로 대표가 다르다면, 하나의 그룹으로 묶어줘야 한다.
			if (pStart != pEnd) {
				p[pEnd] = pStart; // pStart를 대표로 갱신하여 한 그룹 만듬

				// 그룹 만들었으니까 해당 간선 선택한 것임 -> 가중치 더해주고, 간선 count up
				ans += edges[i][2];
				pick++;
			}

			// 4) 백트래킹 : (N-1)개의 간선 모두 골랐으면, 모든 컴퓨터가 연결 끝난거니까 그만 해도 됨~
			if (pick == (N - 1))
				break;
		}

		System.out.println(ans);

		sc.close();

	}

	// path compression 이용 - 내려오면서 모두 갱신
	private static int findset(int x) {
		if (x != p[x]) { // 자기 자신이 대표가 아니라면,
			p[x] = findset(p[x]); // findset 재귀 호출하면서 대표를 찾아서 갱신
		}
		return p[x]; // 대표 반환
	}

}
