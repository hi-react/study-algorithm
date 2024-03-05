package boj_s2_11725_트리의부모찾기;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static ArrayList<Integer>[] list; // 연결된 노드들의 정보를 저장해주기 위한 인접 리스트들의 배
	static boolean[] visited; // 노드 방문 정보 확인하기 위한 boolean 배열
	static int[] parents; // 부모 노드 저장하기 위한 배열

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 노드 수

		// 인접리스트의 배열 N+1 크기로 초기화
		list = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		// N - 1개의 노드 연결 정보를 확인하면서 인접 리스트의 배열 데이터 채워 넣어주기
		for (int i = 0; i < N - 1; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			list[start].add(end);
			list[end].add(start);
		}

		// 노드 방문 했는 지 확인 하기 위한 visited 배열 초기화
		visited = new boolean[N + 1]; // 노드 N개니까

		// 2번 노드부터 해당 노드의 부모 노드를 저장하기 위한 parents 배열 초기화
		parents = new int[N + 1];

		// 깊이 우선 탐색 시작(DFS) - 루트 노드 = 1
		DFS(1);

		// DFS 모두 끝 마치고 나오면, parents 배열의 2번 index부터 N번 index까지
		// 해당 노드들의 부모 노드가 저장 되어 있겠지!
		for (int i = 2; i <= N; i++) {
			System.out.println(parents[i]);
		}

		sc.close();
	}

	static void DFS(int Node) {

		// 해당 노드를 방문 했으므로, 방문 배열에서 true로 갱신
		visited[Node] = true;

		// 해당 노드의 인접 리스트에 저장된, 즉 연결되어 있는 노드들을 모두 순회하면서
		for (int i : list[Node]) {
			// 만약 아직 방문하지 않은 노드가 있다면,
			if (!visited[i]) {
				parents[i] = Node; // 그 노드의 부모는 해당 노드!
				// 그리고 아직 방문 안 했으니까 DFS 돌려줘야지 (재귀)
				DFS(i);
			}
		}

	}

}
