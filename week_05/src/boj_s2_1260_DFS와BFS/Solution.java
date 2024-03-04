package boj_s2_1260_DFS와BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	static boolean[] visited; // 방문 배열
	static ArrayList<Integer>[] list; // 인접 리스트

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 입력 값 받
		int N = sc.nextInt(); // 노드 개수
		int M = sc.nextInt(); // 에지 개수
		int Start = sc.nextInt(); // 노드 탐색 시작 지점

		// 노드 순회하면서 인접 리스트 초기화
		list = new ArrayList[N + 1]; // 노드 개수 +1

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}

		// 에지 순회하면서 연결된 노드 인접 리스트에 데이터 넣어주기
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();

			list[start].add(end);
			list[end].add(start);
		}

		// 인접 리스트 순회하면서, 번호 작은 노드부터 탐색하기 위해 오름차순 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(list[i]);
		}

		// 방문 배열 초기화
		visited = new boolean[N + 1];
		DFS(Start);

		System.out.println();
		
		// 방문 배열 초기화
		visited = new boolean[N + 1];
		BFS(Start);

		sc.close();
	}

	static void DFS(int Node) {
		System.out.print(Node + " "); // 일단 들어 온 값부터 탐색 할 거니까 출력

		visited[Node] = true; // 방문 배열 true

		// 인접 리스트에서 해당 노드에 연결된 배열의 요소들을 하나씩 순회하면
		for (int i : list[Node]) {
			// 방문하지 않았다면, DFS 수행(재귀)
			if (!visited[i])
				DFS(i);
		}

	}

	static void BFS(int Node) {

		// 큐 만들어 두고, FIFO 순으로 탐색할 거다!
		Queue<Integer> queue = new LinkedList<Integer>();

		// 일단 처음 들어온 노드 번호 큐에 넣어주자
		queue.add(Node);
		visited[Node] = true; // 방문 배열도 true로 바꿔주고

		// 큐가 비어있지 않을 때까지 반복문 돌릴 건데,
		while (!queue.isEmpty()) {
			// FIFO로 꺼낸다. 먼저 넣은 거 먼저 나옴
			int newNode = queue.poll(); // 큐에서 하나 꺼내서
			System.out.print(newNode + " "); // 탐색 했다고 찍고,

			// 인접 리스트에서 newNode에 연결된 배열 요소를 쭉 돌면서
			for (int i : list[newNode]) {
				// 방문하지 않았다면, 큐에 추가하고, visited 처리 해줌
				if (!visited[i]) {
					queue.add(i);
					visited[i] = true;
				}
			}
		}

	}

}
