package boj_g4_8983_사냥꾼;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사대 수
        int N = Integer.parseInt(st.nextToken()); // 동물 수
        int L = Integer.parseInt(st.nextToken()); // 사정 거리

        st = new StringTokenizer(br.readLine());
        int[] guns = new int[M];
        for (int i = 0; i < M; i++) {
            guns[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(guns); // 이분 탐색 위해 정렬

        List<int[]> animals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (y > L) continue;
            animals.add(new int[] {x, y});
        }

        br.close(); // 입력 끝

        int count = 0;

        for (int[] a : animals) {
            int x = a[0], y = a[1];

            int left = 0, right = M - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int dist = Math.abs(guns[mid] - x) + y;

                if (dist <= L) {
                    count++;
                    break; // 잡았으면 종료하고 다음
                } else if (guns[mid] < x) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        System.out.println(count);
    }
}
