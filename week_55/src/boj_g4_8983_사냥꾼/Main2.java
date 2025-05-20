package boj_g4_8983_사냥꾼;

import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken()); // 사대 수
        int N = Integer.parseInt(st.nextToken()); // 동물 수
        int L = Integer.parseInt(st.nextToken()); // 사정 거리

        st = new StringTokenizer(br.readLine());
        TreeSet<Integer> guns = new TreeSet<>();
        for (int i = 0; i < M; i++) {
            guns.add(Integer.parseInt(st.nextToken()));
        }

        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y > L) continue;

            int range = L - y;
            int left = x - range;
            int right = x + range;

            Integer candidate = guns.ceiling(left); // 가장 왼쪽 범위 안의 사대
            if (candidate !=  null && candidate <= right) count++;
        }

        br.close();
        System.out.println(count);
    }
}
