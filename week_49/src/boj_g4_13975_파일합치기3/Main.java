package boj_g4_13975_파일합치기3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int K =  Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            for (int i = 0; i < K; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long minCost = 0;

            while (pq.size() > 1) {
                long a = pq.poll();
                long b = pq.poll();
                long sum = a + b;

                minCost += sum;
                pq.offer(sum);
            }

            bw.write(minCost + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}