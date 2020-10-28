import java.util.*;
// https://yeoulcoding.tistory.com/123
public class B2042 {
    public static void main(String args[]){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int k = input.nextInt();
        long[] matrix = new long[n+1];
        FenwickTree fenwick = new FenwickTree(n);

        // initialize fenwick tree
        for(int i=1;i<=n;i++){
            matrix[i] = input.nextLong();
            fenwick.update(i, matrix[i]);
        }

        for(int i=0;i<m+k;i++) {
            int w = input.nextInt();
            if (w == 1) {
                int pos = input.nextInt();
                long val = input.nextLong();
                fenwick.update(pos, val - matrix[pos]);
                matrix[pos] = val;
            } else if (w == 2) {
                int start = input.nextInt();
                int end = input.nextInt();
                long sum = fenwick.sum(end) - fenwick.sum(start-1);
                System.out.println(sum);
            }
        }
    }

    static class FenwickTree {
        long[] tree;
        int n;
        FenwickTree(int n) {
            this.n = n;
            this.tree = new long[n+1];
        }

        // calculate sum
        public long sum(int pos) {
            long sum = 0;
            int temp = pos;
            while(temp > 0) {
                sum += this.tree[temp];
                temp -= (temp & -temp);
            }
            return sum;
        }

        // update value
        public void update(int pos, long val) {
            int temp = pos;
            while(temp <= this.n) {
                this.tree[temp] += val;
                temp += (temp & -temp);
            }
        }
    }
}
