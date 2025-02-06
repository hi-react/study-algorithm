package boj_s5_9655_돌게임;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String res = N % 2 == 0 ? "CY" : "SK";
        System.out.println(res);
    }
}
