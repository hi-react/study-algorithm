// 더 깔끔한 그리디 알고리즘

package boj_s4_2839_설탕배달;

import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 목표 = 설탕 N kg
		int N = sc.nextInt();
		sc.close();

		// 반환 해줄 최소 값 변수
		int min = 0;

		while (N > 0) {
			// 1. 5의 배수라면 5kg 설탕 봉지로 다 채우면 됨 
			if (N % 5 == 0) {
				min += N / 5;
				N = 0; // while 문을 빠져나올 수 있도록 조건 추가
			} else { 
				N -= 3; // 그렇지 않다면 3kg 설탕 봉지 사용해 본다.
				min++;
			}
		}

		System.out.println(N >= 0 ? min : -1);

	}

}
