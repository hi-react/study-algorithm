package boj_s1_1931_회의실배정;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // N개의 회의 
		
		// 1. 회의 정보 저장할 배열 생성 및 입력 값 넣기
		int[][] meetings = new int[N][2];
		
		for(int i=0; i<N; i++) {
			meetings[i][0] = sc.nextInt();
			meetings[i][1] = sc.nextInt();
		}
		
		sc.close();
		
		// 2. 회의 종료 시간 빠른 순 으로 우선 정렬 (만약 같다면, 회의 시작 시간 빠른 순 정렬)
		// Arrays.sort 메서드 이용하여 Comparator 인터페이스의  compare method Override
		// Integer.compare method: x < y (음수) => x를 y보다 앞에 위치시킨다. 
		// 만약 시간이 느린 순(즉, 내림차순) 정렬하고 싶다면 => x, y 위치 바꿔 ! 
		Arrays.sort(meetings, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				
				// 2순위 : 회의 시작 시간 빠른 순 정렬(오름차순)
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				
				// 1순위 : 회의 종료 시간 빠른 순 정렬 (오름차순) 
				return Integer.compare(o1[1], o2[1]);
			}
			
		});
		
		// 3. 회의 배정  -> 순차 탐색하면서 시간 안 겹치면 선택 
		int meetingCount = 0; // 반환해 줄 회의 최대 수
		int endTime = 0; // 이전 회의의 끝나는 시간 
		
		for(int i=0; i<N; i++) {
			// 현재 고려중인 회의의 시작 시간이 이전 회의의 끝나는 시간과 같거나 이후라면, 
			if (meetings[i][0] >= endTime) {
				endTime = meetings[i][1]; // 회의 끝나는 시간 갱신
				meetingCount++; // 회의 갯수 + 1
			}
		}
		
		System.out.println(meetingCount);
		
	}
}
