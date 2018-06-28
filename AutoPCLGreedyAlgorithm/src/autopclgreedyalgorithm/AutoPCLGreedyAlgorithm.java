/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autopclgreedyalgorithm;

import static autopclgreedyalgorithm.SensorUtility.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hieu
 */
public class AutoPCLGreedyAlgorithm {

    public static ArrayList<Double> listTotalTime;
    public static long timeRuning;
    public static long timeRunFindPath;
    public static long timeRunCplex;
    public static long timeRunCoppy;
    public static long timeRunCombine;
    public static double timeLifeOn;
    public static String mPath = "E:\\HIEU\\CAO HOC\\Testcase\\";
    
    public static void main(String[] args) {

        // TODO code application logic here

        PCLGreedyAlgorithm algorithm = new PCLGreedyAlgorithm();
        initData();
        //Chay test case tu 6 den 10
        for (int i = 20; i <= 21; i++) {
            try {
                System.out.println("Test case "+i+"---------------------------");
                //Cai dat ten File
                String filename = "test"+i+".INP";
                
                readFile(mPath+filename); //Add URL sensor file with format (
                long begin = System.currentTimeMillis();
                algorithm.run();
                long end = System.currentTimeMillis();
                timeRuning = end - begin;
                timeLifeOn = calculateTotalTime();

            } catch (IOException ex) {
                Logger.getLogger(AutoPCLGreedyAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                writeResultFile(mPath+"PCLGreedyAlgorithm.txt", i, timeRuning, timeLifeOn); //Url luu file input duoc sinh ra
                resetData();
            } catch (IOException ex) {
                Logger.getLogger(AutoPCLGreedyAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Running Finish ");
        JOptionPane.showMessageDialog(null, "AutoPCLGreedyAlgorithm run finished !");
        
    }
    
    public static void writeResultFile(String filename, int postion, double timeRuning, double timLife) throws IOException {
        FileWriter fw = new FileWriter(filename, true); //the true will append the new data
        fw.write("Test case : "+ postion+"\n");
        fw.write("Number Sensor="+mListSensorNodes.size() + "  Rs="+mRsValue +"  L="+Lvalue+"\n");
        fw.write("Time Run = "+ timeRuning+" , Time Life = "+ timLife+"\n");
        fw.write("\n");
        fw.close();

    }
    
    static void initData() {
        listTotalTime = new ArrayList<>();
    }
    
    static void resetData() {
        mListofListTime.clear();
        timeLifeOn = 0;
    }
    
    static double calculateTotalTime() {
     //------------Algorithm 10----------------------------
        double total =0;
        for (int i = 0; i < mListofListTime.size(); i++) {
            double next = mListofListTime.get(i);
            total += next;
        }
        return total;
    }
    
    //fw.write("TimeFindPath = " + AutoNDSTAlgorithm.timeRunFindPath+" , Time Cplex ="+ timeRunCplex+" , TimeCombine ="+timeRunCombine+ ", TimeCoppy= "+timeRunCoppy+" ,Time Run = "+ timeRuning+" , Time Life = "+ timLife+"\n");//appends the string to the file

}

