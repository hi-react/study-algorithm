package boj_s2_1182_부분수열의합;

import java.util.Scanner;

public class Main {

	static int N, S; // N개의 정수, 목표 합 S
	static int[] originArr; // N개 정수로 이루어진 전체 수열
	static boolean[] selected; // originArr에서 해당 값을 선택했는 지 나타내는 배열

	static int count = 0; // 목표 합 S를 이루는 경우의 수 count

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// N개의 정수, 목표 합 S
		N = sc.nextInt();
		S = sc.nextInt();

		// 전체 수열 및 부분 수열 선택 boolean 배열 초기화
		originArr = new int[N + 1];
		selected = new boolean[N + 1];

		// 전체 수열 입력 받기
		for (int i = 1; i <= N; i++) {
			originArr[i] = sc.nextInt();
		}

		// 부분 조합
		subsets(1); // 1번 인덱스부터 시작

		System.out.println(count);

		sc.close();
	}

	private static void subsets(int idx) {

		// 1. 기저 조건
		if (idx > N) { // originArr 각각의 값 선택 여부 결정 모두 완료되면,

			int sum = 0; // 이제 모두 더해줘야 해.
			boolean isEmpty = true; // 크기가 양수인 부분 수열에 대해서만 계산하기 위한 flag
			
			// 선택했다면 해당 값 더한다.
			for (int i = 1; i <= N; i++) {
				if (selected[i]) {
					sum += originArr[i]; 
					isEmpty = false;
				}	
			}
			 
			if (sum == S && !isEmpty)
				count++;
			return;
		}

		// 2. 재귀 조건
		selected[idx] = true;// 해당 값 사용 O
		subsets(idx + 1);// 다음으로

		selected[idx] = false;// 해당 값 사용 X
		subsets(idx + 1);// 다음으로

	}

}
