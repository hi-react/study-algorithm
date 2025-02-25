package boj_g5_21758_꿀따기;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] honey = new int[N];
        int[] honeySum = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        honey[0] = Integer.parseInt(st.nextToken());
        honeySum[0] = honey[0];

        for (int i = 1; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            honeySum[i] = honeySum[i - 1] + honey[i];
        }

        int maxHoney = 0;

        // 1. 벌1 - 벌2 - 벌통 => 벌1 맨 왼쪽 고정, 벌통 맨 오른쪽 고정
        for (int bee2 = 1; bee2 < N - 1; bee2++) {
            int bee1Honey = honeySum[N - 1] - honey[0] - honey[bee2];
            int bee2Honey = honeySum[N - 1] - honeySum[bee2];
            maxHoney = Math.max(maxHoney, bee1Honey + bee2Honey);
        }

        // 2. 벌1 - 벌통 - 벌2 => 벌1은 맨 왼쪽 고정, 벌2는 맨 오른쪽 고정
        for (int beeHome = 1; beeHome < N - 1; beeHome++) {
            int bee1Honey = honeySum[beeHome] - honey[0];
            int bee2Honey = honeySum[N - 1] - honeySum[beeHome - 1] - honey[N - 1];
            maxHoney = Math.max(maxHoney, bee1Honey + bee2Honey);
        }

        // 3. 벌통 - 벌1 - 벌2 => 벌통 맨 왼쪽, 벌2 맨 오른쪽 고정
        for (int bee1 = 1; bee1 < N - 1; bee1++) {
            int bee1Honey = honeySum[bee1 - 1];
            int bee2Honey = honeySum[N - 2] - honey[bee1];
            maxHoney = Math.max(maxHoney, bee1Honey + bee2Honey);
        }

        System.out.println(maxHoney);
    }
}