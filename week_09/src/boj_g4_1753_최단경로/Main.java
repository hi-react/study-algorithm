package boj_g4_1753_최단경로;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int V; // 정점 수
	static List<Node>[] adj; // 인접 리스트의 배열 (정점에 대한)

	static int[] dist; // 최단 경로 저장할 배열
	static int INF = Integer.MAX_VALUE;

	static class Node implements Comparable<Node> {
		int v; // 정점 번호
		int w; // 해당 정점에 도착하기 위한 가중치

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		// 가중치 기준, 오름차순 정렬
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g4_1753_최단경로/input.txt"));
		Scanner sc = new Scanner(System.in);

		V = sc.nextInt(); // 정점 수 (1 ~ V)
		int E = sc.nextInt(); // 간선 수

		int start = sc.nextInt(); // 시작 정점

		// 인접 리스트의 배열 초기화
		adj = new ArrayList[V + 1]; // 1부터 시작하므로 인덱스 편하게 V+1
		for (int i = 1; i <= V; i++) {
			adj[i] = new ArrayList<>();
		}

		// 간선 순회하면서 인접 리스트의 배열에 인접 정점 입력 넣기
		for (int i = 0; i < E; i++) {
			adj[sc.nextInt()].add(new Node(sc.nextInt(), sc.nextInt()));
		}

		// 최단 경로의 배열인 dist (가장 큰 값으로 초기화)
		dist = new int[V + 1];
		Arrays.fill(dist, INF);

		// 시작 정점부터 다익스트라 돌리자
		dijkstra(start);

		// 다익스트라 돌고 나오면, 최단 경로 배열(dist) 모두 업데이트 되었다.
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}

		sc.close();
	}

	private static void dijkstra(int start) {

		// 1. 우선 순위 큐 생성 - Node Class에서 정의한 대로 가중치 기준 오름차순 정렬됨
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 2. 방문 배열
		boolean[] visited = new boolean[V + 1];

		// 3. start 지점의 dist 배열(최단 경로) 업데이트
		dist[start] = 0;

		// 4. pq에 start 넣어준다.
		pq.offer(new Node(start, 0)); // 시작 지점은 최단 경로 0

		// 5. queue가 빌 때까지 반복
		while (!pq.isEmpty()) {

			// 1) 일단 하나 뽑아
			Node curr = pq.poll();

			// 2) 방문 했으면 pass
			if (visited[curr.v])
				continue;

			// 3) 방문 안했으면
			visited[curr.v] = true; // 일단 방문 처리

			// 해당 정점과 인접한 애들 돌면서
			for (Node near : adj[curr.v]) {

				// 방문 안했고 && 해당 near 정점으로 direct 가는 거리 > curr 정점 거쳐서 돌아가는 거리라면,
				if (!visited[near.v] && dist[near.v] > dist[curr.v] + near.w) {
					dist[near.v] = dist[curr.v] + near.w; // 최소 값(최단 거리) 갱신
					pq.offer(new Node(near.v, dist[near.v])); // 큐에 넣어준다.
				}
			}

		}

	}

}
