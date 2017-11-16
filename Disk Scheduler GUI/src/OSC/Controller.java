package OSC;

import javafx.event.ActionEvent;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javax.swing.*;
import java.util.Arrays;

public class Controller extends Main{

    private int loads = 1;

    //checkbox flag
    private boolean bfcfs,bsstf,bscan,bcscan,blook,bclook = false;

        @FXML
        private ToggleGroup load;
        @FXML
        private Label desc;
        @FXML
        private RadioButton L,M,H;
        @FXML
        private Button generate,exit,clearX;
        @FXML
        private CheckBox fcfs,sstf,scan,cscan,look,clook;

        //chart
        @FXML
        public LineChart<String,Integer> lineChart;
        @FXML
        private CategoryAxis x;
        @FXML
        private NumberAxis y;

        @FXML
        public void select(){
            RadioButton selectedRB = (RadioButton)load.getSelectedToggle();

            if (selectedRB.getId().equals("l")){
                desc.setText(" Light load : 0 or 1 request waiting for the entire disk");
                loads = 1;
            }else if (selectedRB.getId().equals("m")){
                desc.setText(" Medium load : 0 or 1 request waiting for the entire cylinder");
                loads = 2;
            }else if (selectedRB.getId().equals("h")){
                desc.setText(" Heavy load : Many requests waiting for many cylinders");
                loads = 3;
            }else {
                JOptionPane.showMessageDialog(null, "Error, no radiobutton selected","Error",JOptionPane.ERROR_MESSAGE);
                L.setSelected(true);
                loads = 1;
            }
        }

        @FXML
        public void ex(){
            System.exit(0);
        }

        @FXML
        public void check(ActionEvent event){
            CheckBox isChecked = (CheckBox)event.getSource();

            //fcfs flag
            if (isChecked.getId().equals("fcfs") && isChecked.isSelected()){
                System.out.println("fcfs");
                bfcfs = true;
            }else if(isChecked.getId().equals("fcfs") && !isChecked.isSelected()){
                System.out.println("bfcfs");
                bfcfs = false;
            }

            //sstf flag
            if (isChecked.getId().equals("sstf") && isChecked.isSelected()){
                System.out.println("sstf");
                bsstf = true;
            }else if(isChecked.getId().equals("sstf") && !isChecked.isSelected()){
                System.out.println("bsstf");
                bsstf = false;
            }

            //scan flag
            if (isChecked.getId().equals("scan") && isChecked.isSelected()){
                System.out.println("scan");
                bscan = true;
            }else if(isChecked.getId().equals("scan") && !isChecked.isSelected()){
                System.out.println("bscan");
                bscan = false;
            }

            //cscan flag
            if (isChecked.getId().equals("cscan") && isChecked.isSelected()){
                System.out.println("cscan");
                bcscan = true;
            }else if(isChecked.getId().equals("cscan") && !isChecked.isSelected()){
                System.out.println("bcscan");
                bcscan = false;
            }

            //look flag
            if (isChecked.getId().equals("look") && isChecked.isSelected()){
                System.out.println("look");
                blook = true;
            }else if(isChecked.getId().equals("look") && !isChecked.isSelected()){
                System.out.println("blook");
                blook = false;
            }

            //clook flag
            if (isChecked.getId().equals("clook") && isChecked.isSelected()){
                System.out.println("clook");
                bclook = true;
            }else if(isChecked.getId().equals("clook") && !isChecked.isSelected()){
                System.out.println("bclook");
                bclook = false;
            }

        }

        @FXML
        public void generate(ActionEvent event) throws Exception {

            if (bfcfs && !fcfs.isDisabled()) {
                fcfs.setDisable(true);
                FCFS first = new FCFS();
                String total = Integer.toString(first.trace(loads));
                switch (loads){
                    case 1:
                        int[] arrN = {100,first.arrL[0]};
                        gen(arrN,"FCFS (L) ["+total+"]");
                        break;
                    case 2:
                        int[] arrNM = new int[6];
                        arrNM[0] =100;
                        for (int i=1;i<first.arrM.length+1;i++){
                            arrNM[i] = first.arrM[i-1];
                        }
                        gen(arrNM,"FCFS (M) ["+total+"]");
                        break;
                    case 3:
                        int[] arrNH = new int[11];
                        arrNH[0] =100;
                        for (int i=1;i<first.arrH.length+1;i++){
                            arrNH[i] = first.arrH[i-1];
                        }
                        gen(arrNH,"FCFS (H) ["+total+"]");
                        break;
                }
            }

            if (bsstf && !sstf.isDisabled()){
                sstf.setDisable(true);
                SSTF second = new SSTF();

                switch (loads){
                    case 1:
                        int[] tempL = second.getsstf(second.head,second.arrL);
                        int totL = second.getTotalMove(tempL);
                        gen(second.getsstf(second.head,second.arrL),"SSTF (L) ["+totL+"]");
                        break;
                    case 2:
                        int[] tempM = second.getsstf(second.head,second.arrM);
                        int totM = second.getTotalMove(tempM);
                        gen(tempM,"SSTF (M) ["+totM+"]");
                        break;
                    case 3:
                        int[] tempH = second.getsstf(second.head,second.arrH);
                        int totH = second.getTotalMove(tempH);
                        gen(tempH,"SSTF (H) ["+totH+"]");
                        break;
                }
            }

            if (bscan && !scan.isDisabled()){
                scan.setDisable(true);
                switch  (loads){
                    case 1:
                        Scan scanL = new Scan(Scan.arrL,Scan.head);
                        int[] scanDiskL = scanL.calculateScanDisk();
                        int totalMoveL = scanL.getTotalMove(scanDiskL);
                        gen(scanDiskL,"SCAN (L) ["+totalMoveL+"]");
                        break;
                    case 2:
                        Scan scanM = new Scan(Scan.arrM,Scan.head);
                        int[] scanDiskM = scanM.calculateScanDisk();
                        int totalMoveM = scanM.getTotalMove(scanDiskM);
                        gen(scanDiskM,"SCAN (M) ["+totalMoveM+"]");
                        break;
                    case 3:
                        Scan scanH = new Scan(Scan.arrH,Scan.head);
                        int[] scanDiskH = scanH.calculateScanDisk();
                        int totalMoveH = scanH.getTotalMove(scanDiskH);
                        gen(scanDiskH,"SCAN (H) ["+totalMoveH+"]");
                        break;
                }
            }

            if (bcscan && !cscan.isDisabled()){
                cscan.setDisable(true);
                switch (loads){
                    case 1:
                        Cscan cscanL = new Cscan(Cscan.arrL,Cscan.head);
                        int[] cscanDiskL = cscanL.calculateScanDisk();
                        int cscanMoveL = cscanL.getTotalMove(cscanDiskL);
                        genCircular(cscanDiskL,"CSCAN (L) ["+cscanMoveL+"]",1);
                        break;
                    case 2:
                        Cscan cscanM = new Cscan(Cscan.arrM,Cscan.head);
                        int[] cscanDiskM = cscanM.calculateScanDisk();
                        int cscanMoveM = cscanM.getTotalMove(cscanDiskM);
                        genCircular(cscanDiskM,"CSCAN (L) ["+cscanMoveM+"]",2);
                        break;
                    case 3:
                        Cscan cscanH = new Cscan(Cscan.arrH,Cscan.head);
                        int[] cscanDiskH = cscanH.calculateScanDisk();
                        int cscanMoveH = cscanH.getTotalMove(cscanDiskH);
                        genCircular(cscanDiskH,"CSCAN (L) ["+cscanMoveH+"]",3);
                        break;
                }
            }

            if (blook && !look.isDisabled()){
                look.setDisable(true);
                switch (loads){
                    case 1:
                        LOOK lookL = new LOOK(LOOK.arrL,LOOK.head);
                        int[] lookDiskL = lookL.calculateLookDisk();
                        int totalLookL = lookL.getTotalMove(lookDiskL);
                        gen(lookDiskL,"LOOK (L) ["+totalLookL+"]");
                        break;
                    case 2:
                        LOOK lookM = new LOOK(LOOK.arrM,LOOK.head);
                        int[] lookDiskM = lookM.calculateLookDisk();
                        int totalLookM = lookM.getTotalMove(lookDiskM);
                        gen(lookDiskM,"LOOK (M) ["+totalLookM+"]");
                        break;
                    case 3:
                        LOOK lookH = new LOOK(LOOK.arrH,LOOK.head);
                        int[] lookDiskH = lookH.calculateLookDisk();
                        int totalLookH = lookH.getTotalMove(lookDiskH);
                        gen(lookDiskH,"LOOK (H) ["+totalLookH+"]");
                        break;
                }
            }

            if (bclook && !clook.isDisabled()){
                clook.setDisable(true);
                switch (loads){
                    case 1:
                        FCFS clookL = new FCFS();
                        int[] arrLn = {100,clookL.arrL[0]};
                        String total = Integer.toString(clookL.trace(1));
                        System.out.println(Arrays.toString(arrLn));
                        gen(arrLn,"CLOOK (L) ["+total+"]");
                        break;
                    case 2:
                        Clook clookM = new Clook(Clook.arrM,Clook.head);
                        int[] clookDiskM = clookM.calculateClookDisk();
                        int totalcLookM = clookM.getTotalMove(clookDiskM);
                        genCLook(clookDiskM,"CLOOK (M) ["+totalcLookM+"]",2);
                        break;
                    case 3:
                        Clook clookH = new Clook(Clook.arrH,Clook.head);
                        int[] clookDiskH = clookH.calculateClookDisk();
                        int totalcLookH = clookH.getTotalMove(clookDiskH);
                        genCLook(clookDiskH,"CLOOK (H) ["+totalcLookH+"]",3);
                        break;
                }
            }

            L.setDisable(true);
            M.setDisable(true);
            H.setDisable(true);
        }//end generate()

        public void gen(int[] arr,String name) {
                System.out.println(Arrays.toString(arr));
                XYChart.Series<String,Integer> series = new XYChart.Series<String, Integer>();

                for (int n=0;n<arr.length;n++){
                    series.getData().add(new XYChart.Data<String, Integer>(Integer.toString(n+1),arr[n]));
                }

                series.setName(name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(series);
                x.setAnimated(false);

        }

    public void genCircular(int[] arr,String name,int loads){
        switch (loads){
            case 1:
                XYChart.Series<String,Integer> seriesL = new XYChart.Series<String, Integer>();
                int l = 0;
                while (arr[l]!=199){
                    seriesL.getData().add(new XYChart.Data<String, Integer>(Integer.toString(l+1),arr[l]));
                    l++;
                }
                seriesL.setName(name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesL);
                x.setAnimated(false);
                break;
            case 2:
                XYChart.Series<String,Integer> seriesM1 = new XYChart.Series<String, Integer>();
                XYChart.Series<String,Integer> seriesM2 = new XYChart.Series<String, Integer>();
                int m = 0;
                while (arr[m]!=199){
                    seriesM1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(m+1),arr[m]));
                    m++;
                }
                seriesM1.setName("i."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesM1);
                x.setAnimated(false);

                while(m!=arr.length){
                    seriesM2.getData().add(new XYChart.Data<String, Integer>(Integer.toString(m+1),arr[m]));
                    m++;
                }
                seriesM2.setName("ii."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesM2);
                x.setAnimated(false);
                break;
            case 3:
                XYChart.Series<String,Integer> seriesH1 = new XYChart.Series<String, Integer>();
                XYChart.Series<String,Integer> seriesH2 = new XYChart.Series<String, Integer>();
                int h = 0;
                while (arr[h]!=199){
                    seriesH1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(h+1),arr[h]));
                    h++;
                }
                seriesH1.setName("i."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesH1);
                x.setAnimated(false);

                while(h!=arr.length){
                    seriesH2.getData().add(new XYChart.Data<String, Integer>(Integer.toString(h+1),arr[h]));
                    h++;
                }
                seriesH2.setName("ii."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesH2);
                x.setAnimated(false);
                break;
        }

    }

    public void genCLook(int[] arr, String name, int loads){
        switch (loads){
            case 1:
                //unnecessary
                break;
            case 2:
                XYChart.Series<String,Integer> seriesM1 = new XYChart.Series<String, Integer>();
                XYChart.Series<String,Integer> seriesM2 = new XYChart.Series<String, Integer>();
                int n = 1;
                while (arr[n]<arr[n-1]){
                    seriesM1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(n),arr[n-1]));
                    n++;
                }
                seriesM1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(n),arr[n-1]));
                n++;
                seriesM1.setName("i."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesM1);
                x.setAnimated(false);

                while(n!=arr.length){
                    seriesM2.getData().add(new XYChart.Data<String, Integer>(Integer.toString(n),arr[n-1]));
                    n++;
                }
                seriesM2.setName("ii."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesM2);
                x.setAnimated(false);
                break;
            case 3:
                XYChart.Series<String,Integer> seriesH1 = new XYChart.Series<String, Integer>();
                XYChart.Series<String,Integer> seriesH2 = new XYChart.Series<String, Integer>();
                int h = 1;
                while (arr[h]<arr[h-1]){
                    seriesH1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(h),arr[h-1]));
                    h++;
                }
                seriesH1.getData().add(new XYChart.Data<String, Integer>(Integer.toString(h),arr[h-1]));
                h++;
                seriesH1.setName("i."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesH1);
                x.setAnimated(false);

                while(h!=arr.length){
                    seriesH2.getData().add(new XYChart.Data<String, Integer>(Integer.toString(h),arr[h-1]));
                    h++;
                }
                seriesH2.setName("ii."+name);
                lineChart.setLegendSide(Side.RIGHT);
                lineChart.getData().add(seriesH2);
                x.setAnimated(false);
                break;
        }
    }



    @FXML
        public void clearC(){
        try {
            Stage stage = (Stage) clearX.getScene().getWindow();
            stage.close();

            start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}//end class
