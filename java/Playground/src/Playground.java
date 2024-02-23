import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Playground {
    public static int sumFunc1(int endNum) {
        if (endNum == 1) return 1;
        return endNum + sumFunc1(endNum - 1);
    }

    public static int sumFunc2(int startNum, int endNum) {
        if (startNum == endNum) return startNum;
        return endNum + sumFunc2(startNum, endNum-1);
    }

    static int N;

    public static void main(String[] args) {
        int result1 = sumFunc1(10);
        System.out.println(result1);

        int result2 = sumFunc2(2, 10);
        System.out.println(result2);

//        System.out.println("Hello");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bufferedReader.readLine());

        

    }
}
