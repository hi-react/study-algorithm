package boj_g5_12865_평범한배낭;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력 받기 
		int N = sc.nextInt(); // N개의 물건
		int K = sc.nextInt(); // 버틸 수 있는 무게 
		
		// 무게 배열, 가치 배열 만들기 (인덱스 안 헷갈리게 N+1 크기로 만듬)
		int[] wArr = new int[N+1];
		int[] vArr = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			wArr[i] = sc.nextInt();
			vArr[i] = sc.nextInt();
		}
		
		// 최대 가치를 저장할 배열 생성 (물건 개수 & 가방 최대 무게)
		int[][] dp = new int[N+1][K+1];
		
		// 배열을 돌면서 각각의 경우에서 최대 가치를 저장해준다.
		for(int i=1; i<=N; i++) { // 고려하는 물건 개수 (N)
			// 현재 고려중인 물품의 무게 및 가치 
			int weight = wArr[i];
			int value = vArr[i]; 
			
			for (int j=1; j<=K; j++) { // 최대 가능한 가방 무게 (K)
				if (weight > j) { // 현재 물품 무게 > 가방 한도 (못 넣는다) 
					dp[i][j] = dp[i-1][j]; // 이전 물품까지의 최대 가치 
				} else { // 그게 아니라면,
					// Max(해당 물품을 넣을 때의 최대가치, 안 넣을 때 최대가치)
					dp[i][j] = Math.max(dp[i-1][j-weight] + value, dp[i-1][j]);
				}
			}
		}
		
		// 반환 할 최대 값
		int max = Integer.MIN_VALUE;
		
		// dp 배열에 최대 가치 저장 끝나면, 가장 큰 값 출력
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=K; j++) {
				if (dp[i][j] > max) max = dp[i][j];
			}
		}
		
		System.out.println(max);
		
		sc.close();
	}

}
