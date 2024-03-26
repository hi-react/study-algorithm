package boj_s2_6603_로또;

import java.util.Scanner;

public class Main {

	static int K; // 숫자 집합의 전체 개수
	static int[] S; // 숫자 집합

	static int R = 6; // 조합 개수
	static int[] lotto; // 로또 임시 조합 담을 배열

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 첫 입력 받기
		K = sc.nextInt(); // S 집합 원소 개수

		// 0 나오기 전까지 반복
		while (K != 0) {

			// 1. S 집합
			S = new int[K];

			// S 집합 입력 값 채워넣기
			for (int i = 0; i < K; i++) {
				S[i] = sc.nextInt();
			}

			// 2. lotto 배열 새로 생성
			lotto = new int[R];

			// 조합 !
			combination(0, 0);
			System.out.println(); // 줄 바꿈

			// 다음 K 값 받아두기
			K = sc.nextInt();

		}

		sc.close();

	}

	// idx : S 배열 인덱스
	// lottoIdx : lotto 배열 인덱스
	private static void combination(int idx, int lottoIdx) {

		// 1. 기저 조건 : lottoIdx 다 채우면 출력
		if (lottoIdx == R) {
			for (int i = 0; i < R; i++) {
				System.out.print(lotto[i] + " ");
			}
			System.out.println();

			return;
		}

		// 2. 재귀 부분
		for (int i = idx; i <= K - R + lottoIdx; i++) {
			lotto[lottoIdx] = S[i]; // 로또 번호 뽑았다.
			combination(i + 1, lottoIdx + 1); // 다음 조합으로 재귀 
		}

	}

}
