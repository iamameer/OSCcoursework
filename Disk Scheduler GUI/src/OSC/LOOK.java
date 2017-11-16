package OSC;


import java.util.Arrays;
public class LOOK {

    int[] arr;
    int[] finalArr;

    public static int arrL[] = {80};
    public static int arrM[] = {15,195,120,45,180};
    public static int arrH[] = {20,140,10,180,30,145,5,160,34,199};
    public static int head = 100;


    public LOOK(int[] scan, int start) {
        arr = scan;
        finalArr = new int[arr.length + 1];
        head = start;
    }
    public int[] calculateLookDisk() {
        int[] sortedArr = new int[arr.length];
        int[] modified;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && arr[i] < head) {
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

        int[] secondPart = new int[finalArr.length];
        int b = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > head) {
                secondPart[b] = arr[i];
                b++;
            }
        }
        int abc[] = new int[b];

        for (int i = 0; i < abc.length; i++) {
            abc[i] = secondPart[i];
        }
        Arrays.sort(abc);
        for (int i = 0; i < b; i++) {
            finalArr[num] = abc[i];
            num++;
        }
        return finalArr;
    }

    public int getTotalMove(int[] arr){
        int temp = arr[0];
        int distance = 0;

        for(int i = 1; i < arr.length;i++){
            distance = distance + Math.abs(temp-arr[i]);
            temp = arr[i];
        }
        return distance;
    }
}