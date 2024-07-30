package boj_g5_2467_용액;

import java.util.Scanner;

/**
 *
 * 1. 문제 분석
 * 주어진 배열에서 두 수의 합이 0에 가까운 집합 구하기
 * 따라서 절대 값을 이용
 * 입력 데이터는 오름차순 정렬되어 주어진다.
 *
 * 2. 알고리즘 : 투 포인터
 * 0과 n-1을 각각 투 포인터로 지정하여, 합이 0에 가까운 구간 탐색
 * 합이 0인 경우 -> 최적 값이므로 바로 반환
 * 합이 0보다 큰 경우 -> 작아져야 하므로 오른쪽 포인터 right--
 * 합이 0보다 작은 경우 -> 커져야 하므로 왼쪽 포인터 left++
 *
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 용액 개수
        int[] liquid = new int[N]; // 용액 배열

        for (int i = 0; i < N; i++) {
            liquid[i] = sc.nextInt();
        }

        int left = 0, right = N - 1;
        int leftResult = 0, rightResult = 0;
        int minSum = Integer.MAX_VALUE;

        while (left < right) {
            int sum = liquid[left] + liquid[right];

            // 최소 합보다 더 작은 두 개의 용액 찾으면, 결과 갱신
            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                leftResult = liquid[left];
                rightResult = liquid[right];
            }

            if (sum == 0)
                break;
            else if (sum > 0)
                right--;
            else
                left++;
        }

        System.out.println(leftResult + " " + rightResult);

        sc.close();

    }

}
