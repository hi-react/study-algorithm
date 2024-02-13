package boj_g5_11729_하노이탑이동순서;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("src/boj_g5_11729_하노이탑이동순서/input.txt");
		Scanner sc = new Scanner(file);

		int N = sc.nextInt();

		sb.append((int) Math.pow(2, N) - 1).append('\n');
		hanoi(N, 1, 2, 3);
		System.out.println(sb.toString());

		sc.close();

	}

	private static void hanoi(int n, int start, int middle, int end) {

		// 원판이 1개 있는 경우, 3번으로 옮겨주고 끝
		if (n == 1) {
			sb.append(start).append(" ").append(end).append('\n');
			return;
		}

		// 가장 큰 원판(n) 빼고 나머지 원판(1 ~ n-1)을 2번으로 보내준다.
		hanoi(n - 1, start, end, middle);

		// 가장 큰 원판을 3번으로 보내준다.
		sb.append(start).append(" ").append(end).append('\n');

		// 나머지 원판(1 ~ n-1)을 2번 -> 3번으로 보내준다.
		hanoi(n - 1, middle, start, end);
	}

}
