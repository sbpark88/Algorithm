import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.lang.Math;
import java.lang.reflect.Array;

class Solution {

    public static void main(String args[]) {
        String str = "ABCDE";	// 문자열이 주어진다.
        int n = str.length();	// 문자열 길이를 구한다.
        char[] arr = str.toCharArray();	// char 타입의 배열에 문자를 하나씩 넣는다.
        boolean visited[] = new boolean[n];	// 문자열 개수만큼 boolean 생성.
        for(int i=0; i<n; i++) {	// boolean을 모두 false로 초기화.
            visited[i] = false;
        }
        char[] branch = new char[n];
        generatePermutations(arr, n, branch, -1, visited);	// 순열을 구하는 함수에 던져 넣는다.

        char[] output = new char[n];
        combination(arr, 3, 0, output, 0);	// 조합을 구하는 함수에 던져 넣는다.
    }

    static void generatePermutations(char[] arr, int size, char[] branch, int level, boolean[] visited)
    {
        System.out.println("level : " + level);

        if (level >= size-1)	// 처음 level은 -1이 들어옴. size-1은 5가 들어오니까 4가 된다. 즉, 재귀함수가 -1부터 3까지 5번을 들어와 이 if문을 거치지 않고 for문으로 가고, 6번째에는 4가 들어와 이 if문을 거쳐 return을 한다.
        {
            System.out.println(branch);
            return;
        }

        for (int i = 0; i < size; i++)
        {
            if (!visited[i])	// 방문 여부... true, false 검사
            {
                branch[++level] = arr[i];
                visited[i] = true;
                generatePermutations(arr, size, branch, level, visited);
                visited[i] = false;
                level--;
//                System.out.println("level : " + level);
            }
        }
    }

    static void combination(char[] arr, int pick, int startIdx, char[] output, int numElem)
    {
        if (numElem == pick)
        {
            //System.out.println(Arrays.toString(output));
            System.out.println(output);
            return;
        }

        for (int i = startIdx; i < arr.length; i++)
        {
            output[numElem++] = arr[i];
            combination(arr, pick, ++startIdx, output, numElem);
            --numElem;
        }
    }

}