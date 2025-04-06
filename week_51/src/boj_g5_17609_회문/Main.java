package boj_g5_17609_회문;

import java.io.*;

public class Main {

    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            str = br.readLine();
            int len = str.length();

            int value = isPalindrome(false, 0, len - 1);
            int ans = (value == 0) ? 0 : (value == 1) ? 1 : 2;

            bw.write(ans + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static int isPalindrome(Boolean passOne, int left, int right) {
        while (left < right) {
            if (str.charAt(left) == str.charAt(right)) {
                left++; right--;
            } else {
                if (!passOne && (isPalindrome(true, left + 1, right) == 0 ||
                                isPalindrome(true, left, right - 1) == 0)) {
                    return 1;
                }
                return 2;
            }
        }
        return 0;
    }
}
