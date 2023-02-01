import java.util.Arrays;
import java.util.Random;
/*
* MergeSort with the use of recursion
*/
public class MergeSort {
    private static final int MAX_SIZE = 10;
    private static final int UPPER_BOUND = 10;

    public static void mergeSort(int[] arr) {
        if (arr == null)
            return;

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length, temp);
    }

    private static void mergeSort(int[] arr, int start, int end, int[] temp) {
        if (end-start>1) { //If there is at least three items in the array
            int mid = (start+end)/2;
            mergeSort(arr, start, mid, temp);
            mergeSort(arr, mid, end, temp);
            merge(arr, start, mid, end, temp);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
        int l = start;
        int r = mid;
        int t;

        for (t = start; t < end; t++) {
            if(l < mid && (r >= end || arr[l] < arr[r]) ) {
                temp[t] = arr[l++];

            }else {
                temp[t] = arr[r++];
            }
        }

        for(int i = start; i < end; i++) {
            arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[MAX_SIZE];
        for (int i = 0; i < arr.length; i++){
            arr[i] = new Random().nextInt(UPPER_BOUND);
        }
        System.out.println("Original array: " + Arrays.toString(arr) + "/n");
        mergeSort(arr);
        System.out.println("Array after sorting: " + Arrays.toString(arr) + "/n");
    }

}
