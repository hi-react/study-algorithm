package boj_g4_2631_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

/**
 * 4. 문제 유형 : LIS(최장 증가 부분 수열, longest increasing subsequence)
 * => 풀이 방법으로 2가지 알고리즘 활용해 볼 수 있다.
 *
 * 1) DP : O(N^2)
 * 2) 이분 탐색 : O(NlogN) => 지금 이 걸로 품
 *
 */

public class Main2 {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
//        br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];

        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        // 2. LIS(최장 증가 부분 수열)
        // smallestEndOfLIS : 현재 까지 발견된 LIS 의 (각 길이 별로 가능한 가장 작은) 마지막 값들을 저장 하는 리스트
        // 예를 들어, smallestEndOfLIS에 [1, 4, 6]이 들어가 있다면,
        // 이는 길이 1의 LIS는 1로 끝나고, 길이 2의 LIS는 4로 끝나며, 길이 3의 LIS는 6으로 끝날 수 있다는 것
        ArrayList<Integer> smallestEndOfLIS = new ArrayList<>();

        for (int i = 0; i <  N; i++) {
            // [이분 탐색] i번째 아이가 들어갈 위치를 찾는다.
            int position = binarySearch(smallestEndOfLIS, children[i]);

            // 만약, 리스트 해당 위치에 값이 있으면,
            if (position < smallestEndOfLIS.size()) {
                smallestEndOfLIS.set(position, children[i]); // 리스트 해당 위치에 값 갱신
            } else {
                // 해당 위치가 리스트 끝이면, 값을 추가하여 리스트 길이 늘림
                smallestEndOfLIS.add(children[i]);
            }
        }

        System.out.println(N - smallestEndOfLIS.size()); // 최소 이동해야 할 아이 수

        br.close();
    }

    // 이분 탐색 메서드
    private static int binarySearch(ArrayList<Integer> smallestEndOfLIS, int target) {
        int low = 0;
        int high = smallestEndOfLIS.size();

        while (low < high) {
            int mid = (low + high) / 2;

            if (smallestEndOfLIS.get(mid) < target) {
                low = mid + 1; // target이 더 크면, 오른쪽으로
            } else {
                high = mid; // target이 더 작거나 같으면, 왼쪽으로
            }
        }

        return low; // target = children[i] 가 들어갈 위치 반환
    }


}
