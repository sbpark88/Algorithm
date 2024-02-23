import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
//        int array[] = {4,3,5,1,2,6,7,8,9};
        int array[] = {4,3,5,1};

        mergeSort(array);

        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    private static void sort(int[] arr, int low, int high) {
        // index 높은 것과 낮은 것의 차이가 2보다 작을 때까지 sort함수를 재귀로 내려가며 반으로 나눈 것을 또 반으로 나눈다.
        // 즉, arr이 2개만 들어올 때까지 쪼갠다. 3개가 들어오면 index 차가 2가 되고 4개가 들어오면 index 차가 3이 된다.
        if (high - low < 2) {
            return;
        }

        // 현재 배열의 index 시작과 index 끝을 2로 나눈다.
        int mid = (low + high) / 2;
        // 반으로 자른 배열의 앞부분을 sort 함수에 재귀로 넣는다.
        sort(arr, 0, mid);
        // 반으로 자른 배열의 뒷부분을 sort 함수에 재귀로 넣는다. (그림으로 보면 위에 sort가 마지막까지 내려가 return 되면 얘가 실행된다.)
        sort(arr, mid, high);
        // 마찬가지로 위에 두 sort가 모두 마지막까지 내려가 return 되면 얘가 실행된다.
        // 즉, Tree 구조에서 DFS가 파고 들어가는 것과 비슷하다. 그 다음 merege를 하며 거슬러 올라온다.
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low];
        int t = 0, l = low, h = mid;

        while (l < mid && h < high) {
            if (arr[l] < arr[h]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[h++];
            }
        }

        while (l < mid) {
            temp[t++] = arr[l++];
        }

        while (h < high) {
            temp[t++] = arr[h++];
        }

        for (int i = low; i < high; i++) {
            arr[i] = temp[i - low];
        }
    }
}
