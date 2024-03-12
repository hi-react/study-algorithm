package boj_s2_1912_연속합;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 입력 받기
		int n = sc.nextInt();
		
		// n 크기의 임의 수열의 배열 생성
		int[] arr = new int[n];
		
		// 임의 수열 담기 
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		// arr 배열 해당 인덱스 까지의 최대 합을 담아줄 새로운 배열 만들기
		int[] dp = new int[n];
		
		dp[0] = arr[0];
		int maxSum = dp[0];
		
		// arr 배열 돌면서 dp에 최대 합 채워 넣어 줄 거임 
		for(int i=1; i<n; i++) {
			// MAX (이전 인덱스에 저장된 해당 위치까지 최대 합계 + 현재 인덱스 값 vs 현재 인덱스 값)
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			
			// 반환해 줄 최대 합계 갱신 
			maxSum = Math.max(maxSum, dp[i]);
		}
		
		System.out.println(maxSum);
		
		sc.close();
		
	}

}
