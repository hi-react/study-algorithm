package boj_g5_2467_용액;

import java.util.Scanner;

/**
 *
 * 1. 문제 분석
 * 주어진 배열에서 두 수의 합이 0에 가까운 집합 구하기
 * 따라서 절대 값을 이용
 * 입력 데이터는 오름차순 정렬되어 주어진다.
 * 10억 + 10억이어도 20억이면 Int 가능
 *
 * 2. 알고리즘 : 이분 탐색
 * 한 용액을 기준으로 정하고, 나머지 용액을 이분 탐색으로 찾는다.
 * 오름차순 정렬되어 있으므로, liquid[0]부터 기준으로 잡고, 우측 용액 이분 탐색
 * for문 돌면서 반복
 *
 */

public class Main2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 용액 개수
        int[] liquid = new int[N]; // 용액 배열

        for (int i = 0; i < N; i++) {
            liquid[i] = sc.nextInt();
        }

        int leftResult = 0, rightResult = 0;
        int minSum = Integer.MAX_VALUE;

        // 이분 탐색
        for (int i = 0; i < N - 1; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = liquid[i] + liquid[mid];

                // 두 용액의 합이 최소 인 경우, 결과 갱신
                if (Math.abs(sum) < minSum) {
                    minSum = Math.abs(sum);
                    leftResult = liquid[i];
                    rightResult = liquid[mid];
                }

                if (sum == 0) break;
                else if (sum > 0) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                } ;
            }

        }

        System.out.println(leftResult + " " + rightResult);

        sc.close();

    }

}


