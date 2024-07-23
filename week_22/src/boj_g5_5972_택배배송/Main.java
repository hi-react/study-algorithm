package boj_g5_5972_택배배송;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * 1. 문제 분석 
 * 헛간 1 -> 헛간 N까지 가는 데 필요한 최소 비용 찾기
 * 그래프 최단 경로 
 * 단, 여기에서 최단 = 경로의 길이가 아닌, 경로에 따라 이동함에 따른 최소 비용 (여물 양)
 * 그래프는 양 방향으로 구성되어 있으며, 각 경로에는 비용이 할당됨
 * 
 * 2. 알고리즘 : 다익스트라
 * 그래프에서 특정 시작점으로 부터 다른 모든 정점까지의 최단 경로 찾기
 * 이 때의 최단 경로 = 모든 간선 가중치의 합이 최소인 경로 
 * 
 * 3. 자료구조 : 우선순위 큐
 * 현재 정점까지의 최소 비용을 기준으로 우선순위 큐 정렬 
 * 최소 비용 배열 dist 생성 
 * 우선순위 큐에서 현재 비용이 가장 작은 정점을 꺼내어 인접 정점 중 최소 비용 경로를 먼저 탐색 
 * 현재까지 확정된 최소 비용보다 더 큰 비용은 무시(continue)
 * 
 * */

public class Main {
	
	static class Edge {
		int node;
		int cost;
		
		Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("src/boj_g5_5972_택배배송/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 정점
		int M = sc.nextInt(); // 간선
		
		// graph 만들기
		List<List<Edge>> graph = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>()); // N + 1 개의 정점 list
		}
		
		for (int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();
			
			graph.get(start).add(new Edge(end, cost)); // M개의 간선과 비용 연결 
			graph.get(end).add(new Edge(start, cost)); // 양방향 그래프 
		}
		
		System.out.println(dijkstra(graph, N));
		
		sc.close();
		
	}

	private static int dijkstra(List<List<Edge>> graph, int n) {
		
		// Edge 최소 비용 순으로 정렬해 줄 우선순위 큐 
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(edge -> edge.cost));
		
		// 해당 정점까지의 최소 거리 배열 
		int[] dist = new int[n+1]; 
		Arrays.fill(dist, Integer.MAX_VALUE); // 최대치로 초기화 
		
		// 시작 점인 1번 정점까지의 최소 거리 0부터 시작 
		dist[1] = 0; 
		pq.add(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll(); // 앞에서 최소 비용 하나 뽑고 
			int currentNode = current.node;
			int currentCost = current.cost;
			
			// 뽑은 정점까지의 최소 비용 배열을 갱신하지 못한 다면 pass
			if (currentCost > dist[currentNode]) continue;
			
			// 현재 정점에 연결된 애들 확인 
			for(Edge edge : graph.get(currentNode)) {
				int nextNode = edge.node;
				int nextCost = edge.cost;
				
				// 다음 정점까지의 최소 비용 배열을 갱신할 수 있다면 갱신하고 큐에 추가 
				if (dist[currentNode] + nextCost < dist[nextNode]) {
					dist[nextNode] = dist[currentNode] + nextCost;
					pq.add(new Edge(nextNode, dist[nextNode])); // 갱신된 최소 비용으로 큐에 추가 
				}
			}
			
		}
		
		return dist[n]; // 헛간 N 까지의 최소 비용
	}

}
