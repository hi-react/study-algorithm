package boj_g4_1915_가장큰정사각형;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);

        int[][] rec = new int[n][m];
        int[][] dp = new int[n][m]; // (i,j)를 오른쪽 아래 꼭지점으로 하는 가장 큰 정사각형 한 변 길이

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                rec[i][j] = line.charAt(j) - '0';

                if (rec[i][j] == 1) {
                    // 첫 행 or 첫 열
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else { // 그 외 점화식
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i-1][j-1]), dp[i][j-1]) + 1;
                    }
                    // 가장 긴 정사각형 길이 갱신
                    maxLen = Math.max(maxLen, dp[i][j]);
                }

            }
        }

        System.out.println(maxLen * maxLen);

        br.close();
    }
}
