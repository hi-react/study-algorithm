package boj_s4_2839_설탕배달;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 목표 = 설탕 N kg
		int N = sc.nextInt();

		// 반환 해줄 최소 값 변수
		int min = Integer.MAX_VALUE;
		
		// 5와 3만으로 나눠질 수 있는 수인 지 확인 할 변수 
		boolean isDivisible = false;

		// 1. 일단 5로 나눈 몫, 나머지 구한다.
		int n = N / 5;
		int m = N % 5;

		// 2. 만약 5로 나눈 나머지가 3으로도 나눠 떨어진 다면, 일단 최소 값 갱신
		if (m % 3 == 0) {
			if ((n + m / 3) < min)
				isDivisible = true;
				min = (n + m / 3);
		}

		// 3. 위의 경우가 최소 값이 아닐 수도 있으므로 다음 경우도 따져본다.

		// 5로 나눈 몫 하나씩 줄이면서 3으로 나눠 떨어지는 지 테스트
		for (int i = n - 1; i >= 0; i--) {
			// 만약 하나 줄인 값이 3으로 나눠 떨어진다면, 다시 최소 값 갱신
			if ((N - 5 * i) % 3 == 0) {
				isDivisible = true; // 한 번이라도 3으로 나누어 떨어졌으면 나눠 질 수 있는 숫자!
				if ((i + (N - 5 * i) / 3) < min)
					min = (i + (N - 5 * i) / 3);
			}
		}

		// for문 다 돌고 나왔는데 isDivisible = false (나눌 수 없는 숫자라면) -1 출력
		System.out.println(isDivisible ? min : -1);

		sc.close();
	}

}
