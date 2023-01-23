import java.util.Arrays;

/**
 * 1 Rotate Array in Java
 * You may have been using Java for a while. Do you think a simple Java array question
 * can be a challenge? Let’s use the following problem to test.
 * Problem: Rotate an array of n elements to the right by k steps. For example, with n = 7
 * and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. How many different
 * ways do you know to solve this problem?
 * */

public class RotateArrayWithKSteps {

    /**
     * Solution 1 - Intermediate Array
     * In a straightforward way, we can create a new array and then copy elements to the
     * new array. Then change the original array by using System.arraycopy().
     * Space is O(n) and time complexity is O(n).
     * You can check out the difference between System.arraycopy() and Arrays.copyOf().
     * */
    public static void rightRotateUsingArrayCopy(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (k > arr.length) {
            // if rotation is greater than size of array
            // after every N rotation array will become the same as the initial array.
            k = k % arr.length;
        }

        // create new array
        int[] result = new int[arr.length];

        for (int i=0; i<k; i++) {
            // add Kth element from rightmost
            // Here is 5,6,7
            result[i] = arr[arr.length + i - k];
        }

        // Using this loop
//        for (int j=0; j<=k; j++) {
//            // add the rest elements in order
//            result[k + j] = nums[j];
//        }

        // or this loop, whatever
        int j=0;
        for(int i=k; i<arr.length; i++){
            result[i] = arr[j];
            j++;
        }

        // change the original array by using System.arraycopy()
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    /**
     * Solution 2 - Bubble Rotate
     * Can we do this in O(1) space?
     * This solution is like a bubble sort.
     *
     * However, the time is O(n*k).
     * Here is an example (length=7, order=3):
     * i=0
     * 0 1 2 3 4 5 6
     * 0 1 2 3 4 6 5
     * ...
     * 6 0 1 2 3 4 5
     * i=1
     * 6 0 1 2 3 5 4
     * ...
     * 5 6 0 1 2 3 4
     * i=2
     * 5 6 0 1 2 4 3
     * ...
     * 4 5 6 0 1 2 3
     * */
    public static void rightRotateUsingBubbleSort(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (k > arr.length) {
            // if rotation is greater than size of array
            // after every N rotation array will become the same as the initial array.
            k = k % arr.length;
        }

        for (int i=0; i<k; i++) {
            // bubble sort, swap element has higher value in leftmost
            // example: 5,6 -> 6,5
            // i = 0 -> 7 1 2 3 4 5 6
            // i = 1 -> 7 6 1 2 3 4 5
            // i = 2 -> 7 6 5 1 2 3 4
            for (int j=arr.length -1; j>0; j--) {
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
            }
        }
    }

    /**
     * Solution 3 - Reversal
     * Can we do this in O(1) space and in O(n) time? The following solution does.
     * Assuming we are given 1,2,3,4,5,6 and k step = 2.
     *
     * The basic idea is:
     * 1. Divide the array two parts: 1,2,3,4 and 5, 6
     * 2. Reverse first part: 4,3,2,1,5,6
     * 3. Reverse second part: 4,3,2,1,6,5
     * 4. Reverse the whole array: 5,6,1,2,3,4
     * */
    public static void rightRotateUsingReversal(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            throw new IllegalArgumentException("Illegal argument!");
        }

        if (k > arr.length) {
            // if rotation is greater than size of array
            // after every N rotation array will become the same as the initial array.
            k = k % arr.length;
        }

        // set first array length
        int firstArr = arr.length-k;

        // reverse first part
        reverseArr(arr, 0, firstArr-1);

        // reverse second part
        reverseArr(arr, firstArr, arr.length-1);

        // reverse whole array
        reverseArr(arr, 0, arr.length-1);
    }

    public static void reverseArr(int[] arr, int left, int right) {
        if(arr == null || arr.length == 1) return;

        while(left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void printArr(int[] originalArr) {
        StringBuffer sb = new StringBuffer("{");
        Arrays.stream(originalArr).forEach(i -> sb.append(i).append(", "));
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        System.out.println(sb);
    }

    public static void main(String[] args) {
        int[] originalArr = new int[]{1,2,3,4,5,6,7};

        // C1
        rightRotateUsingArrayCopy(originalArr, 9);
        printArr(originalArr);

        // C2
        rightRotateUsingBubbleSort(originalArr, 9);
        printArr(originalArr);

        // C3
        rightRotateUsingReversal(originalArr, 9);
        printArr(originalArr);
    }
}
