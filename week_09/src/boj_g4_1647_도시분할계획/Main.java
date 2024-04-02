package boj_g4_1647_도시분할계획;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] p; // 각 정점(집)의 대표 배열

	// Edge 클래스 (간선 정보)
	static class Edge implements Comparable<Edge> {
		int A, B, W;

		public Edge(int a, int b, int w) {
			A = a; // 시작 정점(집)
			B = b; // 끝 정점(집)
			W = w; // 가중치 (길의 유지 비용)
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}

		// 정렬 - 가중치 기준 오름차순 정렬
		@Override
		public int compareTo(Edge o) {
			return this.W - o.W;
		}

	}

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g4_1647_도시분할계획/input.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 정점의 수 (집의 개수) -> 1부터 시작
		int M = sc.nextInt(); // 간선의 수 (길의 수)

		p = new int[N + 1]; // 대표 배열 (정점 1부터 시작하니까, 인덱스 쉽게 N+1)

		// 간선 배열
		Edge[] edges = new Edge[M];

		// 간선 배열 입력 받기
		for (int i = 0; i < M; i++) {
			edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()); // 시작점, 끝점, 가중치
		}

		// 크루스칼 1단계 : 간선 배열을 가중치 기준 오름차순 정렬
		Arrays.sort(edges); // Comparable 인터페이스 구현해 compareTo 메서드에서 정의해 놓음

		// 크루스칼 2단계 : 간선 배열을 순회하면서 하나의 그룹 만들기

		// 1) 각 정점(집) 별로 자신의 대표를 자기 자신으로 초기화
		for (int i = 1; i < N; i++) {
			p[i] = i;
		}

		// 2) 변수 선언
		int ans = 0; // 최종 반환할 최소 비용(길의 유지비 최소 합)
		int pick = 0; // 선택한 간선 수 count
		int max = 0; // edges 간선 배열 순회하면서 가장 큰 가중치 찾아내기 위함

		// 3) 간선 배열 순회하면서
		for (int i = 0; i < M; i++) {
			// findset : 대표 찾기
			int pStart = findset(edges[i].A); // 시작 정점(집)의 대표 찾기
			int pEnd = findset(edges[i].B); // 끝 정점(집)의 대표 찾기

			// union : 대표가 만약 다르다면 그룹 합쳐
			if (pStart != pEnd) {
				p[pEnd] = pStart; // 끝 정점의 대표를 시작 정점의 대표로 변경함으로써

				if (edges[i].W > max)
					max = edges[i].W; // 가장 큰 가중치 갱신

				ans += edges[i].W; // 합쳤으니까 해당 간선의 비용 더하고
				pick++; // 뽑은 간선 수 추가
			}

			// 백트래킹 : N-1개의 간선 뽑으면 모든 집을 연결한 거니까, 더이상 안해도 돼
			if (pick == (N - 1))
				break;

		}

		// 더한 ans에서 가장 큰 간선 제외한 값 반환
		System.out.println(ans - max);

		sc.close();

	}

	// 대표 찾기 method
	private static int findset(int x) {
		if (x != p[x]) { // 자기 자신이 대표가 아니라면,
			p[x] = findset(p[x]); // 대표 찾을 때까지 재귀 돌려서 넣어준다.
		}
		return p[x]; // 대표 반환
	}

}
