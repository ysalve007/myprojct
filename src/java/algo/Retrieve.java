/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Vina Kokane
 */
public class Retrieve {
    
    public static ArrayList<String> feature_final_list = new ArrayList<String>();
    public static String class_feature="";
    public static HashMap<String,Double> feature_correlation = new HashMap<String,Double>();
    public static double t_relavance;
    public static long time;
    public static String[] features = new String[]{};
    public static int tot_rows;
    public static HashMap<String,Double> selected_features = new HashMap<String,Double>();
    public static HashMap<String,Double> unselected_features = new HashMap<String,Double>();
//    public static ArrayList<String> s_features = new ArrayList<String>();
    
    public static void setFeatureFinalList(ArrayList<String> feature_fi_list){
        feature_final_list = feature_fi_list;
    }
    
    public static ArrayList<String> getFeatureFinalList(){
        return feature_final_list;
    }
    
    
    public static void setSelectedFeatures(HashMap<String,Double> selected){
        selected_features = selected;
    }
    
    public static HashMap<String,Double> getSelectedFeatures(){
        return selected_features;
    }
    
    public static void setUnSelectedFeatures(HashMap<String,Double> unselected){
        unselected_features = unselected;
    }
    
    public static HashMap<String,Double> getUnSelectedFeatures(){
        return unselected_features;
    }
    
    public static void setClassFeature(String class_fi){
        class_feature =class_fi;
    }
    
    public static String getClassFeature(){
        return class_feature;
    }
    
    public static void setThreshold(double theta){
        t_relavance = theta;
    }
    
    public static double getThreshold(){
        return t_relavance;
    }
    
    public static void setCount(int count){
        tot_rows = count;
    }
    
    public static int getCount(){
        return tot_rows;
    }
    
    public static void setFeatures(String[] feat){
         features = feat;
    }
    
    public static String[] getFeatures(){
        return features;
    }        
    
    public static void setTime(long t){
        time = t;
    }
    
    public static long getTime(){
        return  time;
    }
    public static void setFeatureCorrelation(HashMap<String,Double> f_correlation){
        feature_correlation = f_correlation;
    }
    public static HashMap<String, Double> getFeatureCorrelation(){
        return feature_correlation;
    }
}
