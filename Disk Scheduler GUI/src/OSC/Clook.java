package OSC;


import java.util.Arrays;

public class Clook {

    int[] arr;
    int min, max;

    public static int arrL[] = {80};
    public static int arrM[]= {15,195,120,45,180};
    public static int arrH[] = {20,140,10,180,30,145,5,160,34,199};
    public static int head = 100;

    public Clook(int[] clook, int start) {
        this.arr = clook;
        this.head = start;
    }

    public int[] calculateClookDisk() {

        int[] temp = new int[arr.length];
        int[] temp1 = new int[arr.length];
        int a = 0, b = 0;
        for (int i = 0; i < temp.length; i++) {
            if (arr[i] <= head) {
                temp[a] = arr[i];
                a++;
            } else {
                temp1[b] = arr[i];
                b++;
            }
        }

        int aa[] = new int[a+1];
        int[] bb = new int[b];
        aa[0] = head;
        for (int i = 1; i < aa.length; i++) {
            aa[i] = temp[i-1];
        }
        for (int i = 0; i < bb.length; i++) {
            bb[i] = temp1[i];
        }
        Arrays.sort(aa);
        Arrays.sort(bb);

        min = aa[0];
        max = bb[bb.length-1];

        int c = 0;
        int[] finalArr = new int[arr.length+1];
        for (int i = aa.length-1; i >= 0; i--) {
            finalArr[c] = aa[i];
            c++;
        }
        for (int i = bb.length - 1; i >= 0 ; i--) {
            finalArr[c] = bb[i];
            c++;
        }
        return finalArr;
    }

    public int getTotalMove(int[] arr){
        int temp = arr[0];
        int distance = 0;

        for(int i = 1; i < arr.length;i++){

            if(temp == min && arr[i] == max){
                // do nothing
            } else{
                distance = distance + Math.abs(temp-arr[i]);
            }
            temp = arr[i];
        }
        return distance;
    }


}
