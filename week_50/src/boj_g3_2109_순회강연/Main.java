package boj_g3_2109_순회강연;

import java.io.*;
import java.util.*;

public class Main {

    static class Lecture implements Comparable<Lecture> {
        int day, pay;

        Lecture(int day, int pay) {
            this.day = day;
            this.pay = pay;
        }

        @Override
        public int compareTo(Lecture o) {
            return o.pay - this.pay; // 강의료 기준 내림차순
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        int maxDay = 0;

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(d, p));
            if (d > maxDay) maxDay = d;
        }

        Collections.sort(lectures); // 강의료 기준 내림차순

        boolean[] visited = new boolean[maxDay + 1];
        int total = 0;

        for (Lecture lec : lectures) {
            for (int i = lec.day; i >= 1; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    total += lec.pay;
                    break;
                }
            }
        }

        br.close();
        bw.write(total + "\n");
        bw.flush();
        bw.close();
    }
}
