// 신승호님 코드 - 비교가 한 개인 경우 

package boj_s4_10825_국영수;

import java.util.Arrays;

class Student {
	int kor, eng, math;

	public Student(int kor, int eng, int math) {
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
};

public class Test {
	public static void main(String[] args) {
		
		Student[] students = new Student[] { new Student(90, 80, 90), // 첫 번째 학생
				new Student(20, 80, 80), // 두 번째 학생
				new Student(90, 30, 60), // 세 번째 학생
				new Student(60, 10, 50), // 네 번째 학생
				new Student(80, 20, 10) // 다섯 번째 학생
		};

		Arrays.sort(students, (a, b) -> a.kor - b.kor); // 국어 점수 기준 오름차순 정렬 (람다)

		for (int i = 0; i < 5; i++)
			System.out.println(students[i].kor + " " + students[i].eng + " " + students[i].math);
	}
}