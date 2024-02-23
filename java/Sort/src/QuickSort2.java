import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort2 {
    public static void main(String[] args) {
        int[] array = {4,7,1,3,5,2,6};
//        int[] array = {4,3,1,5,2};


        quickSort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) return;

        int mid = partition(arr, low, high);
        sort(arr, low, mid - 1);
        sort(arr, mid, high);
    }

    // Pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2];
        while (low <= high) {
            // low index의 값과 pivot을 비교해 정렬이 정상이면 비정상인 정렬이 나올 때까지 인덱스를 올리며 갱신한다.
            while (arr[low] < pivot) low++;
            // high index의 값과 pivot을 비교해 정렬이 정상이면 비정상인 정렬니 나올 때까지 인덱스를 내리며 갱신한다.
            while (arr[high] > pivot) high--;
            // 위 2개의 while문이 돌면 정렬해야할 범위가 정해지고, 그 범위만큼만 아래 if문을 타고 돌게 된다.
            if (low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
