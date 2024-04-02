package boj_g5_1916_최소비용구하기;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	static int N; // 도시(정점)의 개수
	static List<City>[] adjcity; // 인접 도시 리스트의 배열

	static int end; // 도착 도시

	static int[] dist; // 최소 비용 저장할 배열
	static int INF = Integer.MAX_VALUE;

	static class City implements Comparable<City> {
		int V, W; // 도시 인덱스, 이 도시에 도착하기 위한 버스 비용

		public City(int v, int w) {
			V = v;
			W = w;
		}

		// 버스 비용(W) 최소부터 오름차순 정렬
		@Override
		public int compareTo(City o) {
			return this.W - o.W;
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/boj_g5_1916_최소비용구하기/input.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 도시 (정점) 수 -> 인덱스 1부터 시작
		int M = sc.nextInt(); // 버스 (간선) 수

		// 인접 도시 리스트의 배열 - 초기화
		adjcity = new ArrayList[N + 1]; // 인덱스 1 ~ N
		for (int i = 1; i <= N; i++) {
			adjcity[i] = new ArrayList<>();
		}

		// 간선 쭉 돌면서 인접리스트 연결
		for (int i = 0; i < M; i++) {
			adjcity[sc.nextInt()].add(new City(sc.nextInt(), sc.nextInt()));
		}

		// 출발 도시 번호, 도착 도시 번호
		int start = sc.nextInt();
		end = sc.nextInt();

		// 최소 비용 저장할 배열 - 일단 죄다 무한 대 값으로 초기화
		dist = new int[N + 1]; // 도시 1 ~ N
		Arrays.fill(dist, INF);

		dijkstra(start);

		System.out.println(dist[end]); // 도착 도시 까지 가는 최소 비용 출력

		sc.close();
	}

	private static void dijkstra(int start) {

		// 1. 우선 순위 큐 생성 - 위의 compareTo에서 정한 기준대로 정렬된다.
		PriorityQueue<City> pq = new PriorityQueue<>();

		// 2. 방문 배열
		boolean[] visited = new boolean[N + 1]; // 인덱스 1 ~ N

		// 3. 일단 start 지점부터 queue에 넣는다.
		pq.offer(new City(start, 0)); // 최초 시작 지점은 일단 비용 0

		// 4. 최소 비용 저장하는 배열에도 업데이트 해주자
		dist[start] = 0;

		// 5. queue가 빌 때까지 반복
		while (!pq.isEmpty()) {

			// 1) 일단 하나 꺼내
			City curr = pq.poll();

			// 2) 이미 방문 했으면 패스
			if (visited[curr.V])
				continue;

			// 3) 방문 안했으면 여기로 넘어 오겠지
			visited[curr.V] = true; // 일단 방문 처리

			// 4) 이 놈과 인접한 도시들을 모두 순회하면서
			for (City near : adjcity[curr.V]) {

				// 만약, 방문 하지 않았고 && 이 인접 도시에 direct로 가는 거리(비용) > curr 도시 거쳐서 오는 비용이라면
				if (!visited[near.V] && dist[near.V] > dist[curr.V] + near.W) {
					dist[near.V] = dist[curr.V] + near.W; // 최소 비용 더 작은 걸로 갱신
					pq.offer(new City(near.V, dist[near.V])); // 큐에 넣는다.
				}
			}

			// [백트래킹] 만약 도착 도시(end)까지 다 완료 했으면, 구할 거는 다 구함
			if (curr.V == end)
				break;

		}

	}

}
