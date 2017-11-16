package OSC;

public class FCFS extends Controller{

    public int arrL[] = {80};
    public int arrM[] = {15,195,120,45,180};
    public int arrH[] = {20,140,10,180,30,145,5,160,34,199};

    public static int head = 100;


    public int trace(int load){
        int totalMove = 0;

        switch (load){
            case 1:
                //light
                for (int i = 0;i<arrL.length;i++){
                    totalMove = totalMove + Math.abs(arrL[i]-head);
                    head = arrL[i];
                }
                break;
            case 2:
                //medium
                for (int i = 0;i<arrM.length;i++){
                    totalMove = totalMove + Math.abs(arrM[i]-head);
                    head = arrM[i];
                }
                break;
            case 3:
                //heavy
                for (int i = 0;i<arrH.length;i++){
                    totalMove = totalMove + Math.abs(arrH[i]-head);
                    head = arrH[i];
                }
                break;
        }

        return totalMove;
    } //end trace

}
