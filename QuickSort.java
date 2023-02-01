import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final int MAX_SIZE = 10;
    private static final int UPPER_BOUND = 10;

    public static void quickSort(int[] arr) {
        if(arr == null)
            return;

        quickSort(arr, 0 , arr.length);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (end - start == 2) {
            if (arr[start] > arr[start+1]) {
                swap(arr, start, start+1);
            }
        } else if (end - start > 2){
            choosePivot(arr, start, end);
            int pivot = partition(arr, start, end);
            quickSort(arr, start, pivot);
            quickSort(arr,pivot + 1, end);
        }
    }

    private static void choosePivot(int[] arr, int start, int end) {
        int mid = start + (end - start)/2;

        if (arr[start] < arr[mid]) {
            if (arr[mid] < arr[end-1]) {
                swap(arr, start, mid);
            }else if (arr[start] < arr[end-1]) {
                swap(arr, start, end-1);
            }
        } else {
            if (arr[mid] < arr[end-1]) {
                swap(arr, start, mid);
            }else if (arr[end-1] < arr[start]){
                swap(arr, start, end-1);
            }
        }
    }

    private static int partition(int[] arr,int start, int end) {
        int bigStart = start+1;
        int pivot = arr[start];

        for (int curr = bigStart; curr < end; curr++) {
            if (arr[curr] < pivot) {
                swap(arr, bigStart, curr);
                bigStart++;
            }
        }

        swap(arr,start, bigStart-1);

        return bigStart-1;
    }

    //swap positions of two items within an array
    private static void swap(int[] arr, int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[MAX_SIZE];
        for (int i = 0; i < arr.length; i++){
            arr[i] = new Random().nextInt(UPPER_BOUND);
        }
        System.out.println("Original array: " + Arrays.toString(arr) + "/n");
        quickSort(arr);
        System.out.println("Array after sorting: " + Arrays.toString(arr) + "/n");
    }
}
