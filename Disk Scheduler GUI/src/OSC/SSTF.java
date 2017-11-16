package OSC;


public class SSTF extends Controller{

     int arrL[] = {80};
     public int arrM[] = {15,195,120,45,180};
     int arrH[] = {20,140,10,180,30,145,5,160,34,199};
     int head = 100;

    int nearest = -1;

    public int findNearest(int desiredNum,int[] arr){
        int nearestDistance = 199;
        int d;
        int i;
        for (int k = 0; k < arr.length; k++) {
            if (nearest == arr[k])
                arr[k] = 9999;
        }
        for (i = 0; i < arr.length; i++) {
            d = Math.abs(desiredNum - arr[i]);
            if (d < nearestDistance) {
                nearestDistance = d;
                nearest = arr[i];
            }
        }
        return nearest;
    } //end fintNearest()

    public int[] getsstf( int head,int[] arr) {
        int[] sstf = new int[arr.length + 1];
        sstf[0] = head;
        int desiredNum = head;

        for(int i=0; i < arr.length; i++){
            sstf[i + 1] = findNearest(desiredNum,arr);
            desiredNum = nearest;
        }
        return sstf;
    }//end getSSTF()


    public int getTotalMove(int[] sstf){
        int temp = sstf[0];
        int distance = 0;

        for(int i = 1; i < sstf.length;i++){
            distance = distance + Math.abs(temp-sstf[i]);
            temp = sstf[i];
        }
        return distance;
    }//end getTotalMove

}//end class SSTF
