// 신승호님 코드 

package boj_s4_10825_국영수;

import java.util.Arrays;
import java.util.Scanner;

// Comparable Interface를 구현한 Grade 클래스 

class Grade implements Comparable<Grade> {

	String name;
	int kor, eng, math;

	// 생성자
	public Grade(String name, int kor, int eng, int math) {
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	// Comparable Interface의 compareTo method 오버라이
	@Override
	public int compareTo(Grade grade) {
		if (this.kor == grade.kor) {
			if (this.eng == grade.eng) {
				if (this.math == grade.math) {
					// 문자 비교하기
					return this.name.compareTo(grade.name); // 4순위. 이름 사전순: 오름차순 정렬 (a-b)
				} // 내림차순
				return grade.math - this.math; // 3순위. 수학 성적: 내림차순 정렬 (b-a)
			} // 오름차순
			return this.eng - grade.eng; // 2순위. 영어 성적: 오름차순 정렬 (a-b)
		}
		return grade.kor - this.kor; // 1순위. 국어 성적: 내림차순 정렬 (b-a)

	}

}

public class Main2 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		Grade[] arr = new Grade[n];

		for (int i = 0; i < n; i++) {
			arr[i] = new Grade(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		Arrays.sort(arr); // 정렬 ! -> 이렇게 하면 Comparable에서 Override 해 놓은 정렬 기준대로 되나보네.

		for (int i = 0; i < n; i++)
			System.out.println(arr[i].name);

	}

}