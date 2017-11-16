package OSC;

import java.util.Arrays;

public class Cscan {
    public static int arrL[] = {80};
    public static int arrM[] = {15,195,120,45,180};
    public static int arrH[] = {20,140,10,180,30,145,5,160,34,199};
    public static int head = 100;

    int[] arr;
    int[] finalArr;
    //int head;

    public Cscan(int[] scan, int start) {
        arr = scan;
        finalArr = new int[arr.length + 3];
        head = start;
    }

    public int[] calculateScanDisk() {
        int[] sortedArr = new int[arr.length];
        int[] modified;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < head) {
                sortedArr[count] = arr[i];
                count++;
            }
        }

        modified = new int[count];

        for (int i = 0; i < modified.length; i++) {
            modified[i] = sortedArr[i];
        }

        Arrays.sort(modified);
        int num = 0;
        finalArr[num] = head;
        num++;

        for (int i = modified.length - 1; i >= 0; i--) {
            finalArr[num] = modified[i];
            num++;
        }

        finalArr[num] = 0;        //ori = 199
        num++;

        finalArr[num] = 199;        //ori = 0
        num++;
        int[] secondPart = new int[finalArr.length];
        int b = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > head) {
                secondPart[b] = arr[i];
                b++;
            }
        }

        int abc[] = new int[b];

        for (int i = abc.length - 1; i >= 0; i--) {
            abc[i] = secondPart[i];
        }

        Arrays.sort(abc);

        for (int i = b - 1; i >= 0; i--) {
            finalArr[num] = abc[i];
            num++;
        }

        return finalArr;
    }

    public int getTotalMove(int[] arr) {
        int temp = arr[0];
        int distance = 0;

        for (int i = 1; i < arr.length; i++) {

            if (temp == 0 && arr[i] == 199) {
                // do nothing
            } else {
                distance = distance + Math.abs(temp - arr[i]);
            }
            temp = arr[i];
        }
        return distance;
    }

}