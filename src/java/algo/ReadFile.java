/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algo;

import com.login.Sout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Vina Kokane
 */
public class ReadFile {

    static Statement st;
    static ArrayList<String> feature_final_list;
    public static ArrayList<String> final_feature_suset_list;
    static String class_feature = "";
    public static int count = 0;
    static double ad_matrix[][] = new double[][]{};
    static HashMap<String, Double> relavant_features = new HashMap<String, Double>();
    static HashMap<String, Double> irrelavant_features = new HashMap<String, Double>();

    public static void create_table(String path) throws FileNotFoundException, IOException {
//        String s1 = "Dignosys, F1R, F1S, F2R, F2S, F3R, F3S, F4R, F4S, F5R, F5S, F6R, F6S, F7R, F7S, F8R, F8S, F9R, F9S, F10R, F10S, F11R, F11S, F12R, F12S, F13R, F13S, F14R, F14S, F15R, F15S, F16R, F16S, F17R, F17S, F18R, F18S, F19R, F19S, F20R, F20S, F21R, F21S, F22R, F22S";
//        String[] features = s1.split(",");
        String sCurrentLine;
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(path));

        while ((sCurrentLine = br.readLine()) != null) {
            String[] s = sCurrentLine.split(" ");
            System.out.println("Size: " + s.length);
            break;
        }

    }

    public static void reading(String path1, String path2, String class_f) {

        feature_final_list = new ArrayList<String>();
        BufferedReader br = null;
        class_feature = class_f;
        String create_sql = "", insert_sql = "", insert_values = "", insert = "";
        try {
            st = db.dbconn.connect();

            String check_sql = "show tables like 'report'";

            ResultSet rs = st.executeQuery(check_sql);
            if (rs.next()) {
                String drop_sql = "drop table report";
                int rs1 = st.executeUpdate(drop_sql);
            }

            String entireFileText = new Scanner(new File(path1)).useDelimiter("\\A").next();
//                System.out.println(" Actual Text Start here : \n" + entireFileText);
            entireFileText = entireFileText.replaceAll("\n", " ");
            entireFileText = entireFileText.replaceAll("    ", " ");
            entireFileText = entireFileText.replaceAll("   ", " ");
            entireFileText = entireFileText.replaceAll("  ", " ");
//            entireFileText = entireFileText.replaceAll(" ", "|");
            String[] dat = entireFileText.split(" ");
            int count = 0;
            create_sql = "CREATE TABLE report( sr_no INT(10) UNSIGNED AUTO_INCREMENT PRIMARY KEY";
            insert_sql = "INSERT INTO report(";
            for (int i = 0; i < dat.length; i++) {
                if (!dat[i].equals(" ")) {
                    count++;
                    System.out.println(dat[i]);
                    dat[i] = dat[i].trim();
                    feature_final_list.add(dat[i]);
                }
            }


            for (int j = 0; j < feature_final_list.size(); j++) {
                create_sql += "," + feature_final_list.get(j) + " VARCHAR(50)";
                insert_sql += feature_final_list.get(j) + ",";
            }
            insert_sql = insert_sql.substring(0, insert_sql.length() - 1);
            insert_sql += ")";
            feature_final_list.remove(class_feature);
            Retrieve.setFeatureFinalList(feature_final_list);

            create_sql += ",reg_date TIMESTAMP)";
            System.out.println("create_sql " + create_sql);
            st.executeUpdate(create_sql);
            System.out.println("features: " + feature_final_list);

            String entireFileText1 = new Scanner(new File(path2))
                    .useDelimiter("\\A").next();
            System.out.println(" Actual Text Start here : \n" + entireFileText);
            entireFileText1 = entireFileText1.replaceAll("\n", " ");
            entireFileText1 = entireFileText1.replaceAll("    ", " ");
            entireFileText1 = entireFileText1.replaceAll("   ", " ");
            entireFileText1 = entireFileText1.replaceAll("  ", " ");
//        entireFileText = entireFileText.replaceAll(" ", "|");
            String[] data = entireFileText1.split(" ");
            int count1 = 0;
            int j = 0;
            String[] values = new String[dat.length];

            for (int i = 0; i < data.length; i++) {
                if (!data[i].equals(" ")) {
                    count1++;
                    System.out.println(i + " - " + data[i]);
                    if (count1 > dat.length) {
                        values[j] = data[i];
                        j++;
                        if (j == dat.length) {
                            insert = "";
                            insert_values = "";
                            insert_values += "VALUES(";
                            System.out.println("Rows ara as follows");
                            for (int k = 0; k < j; k++) {
                                System.out.print(values[k] + " ");
                                insert_values += "'" + values[k] + "'" + ",";
                            }
                            insert_values = insert_values.substring(0, insert_values.length() - 1);
                            insert_values += ")";
                            insert = insert_sql + " " + insert_values;
                            st.executeUpdate(insert);
                            System.out.println("");
                            j = 0;
                        }
                    }
                }
            }

            System.out.println("HI");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void truncate(String tablename) throws Exception {
        db.dbconn.connect().execute("TRUNCATE " + tablename);
        db.dbconn.connect().close();
    }

    public static double getEntropy(ArrayList<Integer> hs, int count) {
        double entropy = 0.0;
        double prob;
        for (int i = 0; i < hs.size(); i++) {
            prob = (double) hs.get(i) / count;
            Sout.sout("prob", prob);
            double d = (double) Math.log(prob) / (double) Math.log(2);
            entropy += -(double) (prob * d);
        }
        return entropy;
    }

    public static double getSubEntropy_one(HashMap<String, ArrayList<Integer>> cfc, HashMap<String, Integer> cc, int count) {
        double subEntropy = 0.0, probY = 0.0, probXY = 0.0, sumX = 0.0;
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for (Map.Entry<String, Integer> entry : cc.entrySet()) {
            String key = entry.getKey();
            double value = entry.getValue();

            probY = (double) value / count;
            Sout.sout("probY", probY);
            temp = (ArrayList<Integer>) cfc.get(key);
            Sout.sout("key: " + key, "-> " + temp);
            for (int i = 0; i < temp.size(); i++) {
                probXY = (double) (temp.get(i) / value);
                Sout.sout("probXY", probXY);
                sumX += (double) (probXY * (Math.log(probXY) / Math.log(2)));
            }
            Sout.sout("sumX", sumX);
            subEntropy += -(double) (probY * sumX);
            sumX = 0.0;
        }
        return subEntropy;
    }

    public static HashMap<String, Double> select_algo(double[][] adj_matrix, int count, String type) {
        HashMap<String, Double> min = new HashMap<String, Double>();
        if (type.equals("Kruskal")) {
            //Kruskal1 k1 = new Kruskal1();
            Kruskal kk = new Kruskal(count);
            try {
                //min = k1.kruskal(adj_matrix, count);
                min = kk.kruskal(adj_matrix);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Prims pm = new Prims(count);
            min = pm.primsAlgorithm(adj_matrix);
        }

        return min;
    }

    public static void process() throws SQLException, Exception {
        double class_entropy = 0.0, feature_entropy = 0.0, threshold = 0.0, average = 0.0;
        count = 0;
        Statement st = null;
        ArrayList<Integer> class_count = new ArrayList<Integer>();
        ArrayList<Integer> feature_count = new ArrayList<Integer>();
//        ArrayList<String> features = new ArrayList<String>();
//        String class_feature = "";
//        class_feature = (String)Retrieve.getClassFeature();
//        ArrayList<String> rel_features = new ArrayList<String>();
//        ArrayList<String> irrel_features = new ArrayList<String>();

        HashMap<String, Double> symmetric_uncertainty = new HashMap<String, Double>();
        HashMap<String, Integer> hs_class_count = new HashMap<String, Integer>();
        HashMap<String, ArrayList<Integer>> feature_class_count = new HashMap<String, ArrayList<Integer>>();
        HashMap<String, Double> su = new HashMap<String, Double>();

//        String[] features = {"DGN", "PRE4", "PRE5", "PRE6", "PRE7", "PRE8", "PRE9", "PRE10", "PRE11", "PRE14", "PRE17", "PRE19", "PRE25", "PRE30", "PRE32", "AGE"};
        try {
            st = db.dbconn.connect();

            String sql_count = "SELECT count(*) as count FROM report";
            ResultSet rs_count = st.executeQuery(sql_count);
            if (rs_count.next()) {//getting total count of rows in disease_table
                count = rs_count.getInt("count");
                Sout.sout("count", count);
            }

            String sql_class_count = "SELECT " + class_feature + ", count(*) as count FROM report GROUP BY " + class_feature;
            ResultSet rs_class_count = st.executeQuery(sql_class_count);
            while (rs_class_count.next()) {//adding class frequency
                String key = rs_class_count.getString(class_feature);
                Integer value = rs_class_count.getInt("count");
                Sout.sout("key: " + key, "value: " + value);
                class_count.add(value);
                hs_class_count.put(key, value);
            }
            class_entropy = (double)getEntropy(class_count, count);//calculate class entropy
            Sout.sout("class entropy", class_entropy);
            System.out.println("Class entropy: " + class_entropy);
            for (int i = 0; i < feature_final_list.size(); i++) {//loop through all features

                Sout.sout("feature", feature_final_list.get(i));

                String sql_features_count = "SELECT " + feature_final_list.get(i) + ", count(*) as count  FROM report GROUP BY " + feature_final_list.get(i);
                ResultSet rs_feature_count = st.executeQuery(sql_features_count);
//                System.out.println("features count");
                while (rs_feature_count.next()) {//adding features frequency
                    String key = rs_feature_count.getString(feature_final_list.get(i));
                    int value = rs_feature_count.getInt("count");
//                    Sout.sout("key: " + key, "value: " + value);
                    feature_count.add(value);
                }
                feature_entropy = (double)getEntropy(feature_count, count);//calculate feature entropy
                Sout.sout("feature entropy", feature_entropy);
                System.out.println("feature_entropy: " + feature_entropy);

                ArrayList<Integer> temp = null;
                String sql_features_class_count = "SELECT " + feature_final_list.get(i) + ", " + class_feature + ", count(*) as count FROM report GROUP BY " + feature_final_list.get(i) + "," + class_feature;
                ResultSet rs_feature_class = st.executeQuery(sql_features_class_count);
                while (rs_feature_class.next()) {//adding class and features frequency
                    String key = rs_feature_class.getString(class_feature);
                    int value = rs_feature_class.getInt("count");

                    if (feature_class_count.containsKey(key)) {
                        temp = feature_class_count.get(key);
                        temp.add(value);
                        feature_class_count.put(key, temp);
                    } else {
                        temp = new ArrayList<Integer>();
                        temp.add(value);
                        feature_class_count.put(key, temp);
                    }
                }
                double sub_entropy = (double) getSubEntropy_one(feature_class_count, hs_class_count, count);
                Sout.sout("Sub Entropy", sub_entropy);
                System.out.println("sub_entropy: "+sub_entropy);

                double symm_un = (double) ((2 * (feature_entropy - sub_entropy)) / (feature_entropy + class_entropy));//calculate symmetric uncertaintity of each feature
                average += (double)symm_un;//adding all features symmetric uncertaintity calculated with class 
                symm_un = (double) symm_un;
                Sout.sout("Symmetric Uncertainty", symm_un);
                System.out.println("symm_un: "+symm_un);
                symmetric_uncertainty.put(feature_final_list.get(i), symm_un);//adding symmetric uncertaintity of each feature
                feature_count.clear();
                feature_class_count.clear();
            }
            
            System.out.println("average: "+average);

            threshold =  ((double)average / (double) feature_final_list.size());
            //threshold = (double) ((double) features.length/average);
            System.out.println("Threshold:" + threshold);
            Sout.sout("Threshold", threshold);
            for (Map.Entry<String, Double> entry : symmetric_uncertainty.entrySet()) {
                String key = entry.getKey();
                double value = entry.getValue();
                if (value > threshold) {
                    Sout.sout("selected feature is", key);
                    relavant_features.put(key, value);//relevant features are sort out from all features
                } else {
                    irrelavant_features.put(key, value);//irrelevant features are sort out from all features
                }
            }
            Retrieve.setSelectedFeatures(relavant_features);//relevant features set it into bean class
            Retrieve.setUnSelectedFeatures(irrelavant_features);//irrelevant features set into bean class
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            st.close();
        }

//        double[][] ad_matrix = process_one(count);//call to method which generate matrix for passing it to minimum spanning tree
//        return ad_matrix;
    }

    public static void process_one() throws SQLException, Exception {
//        HashMap<String, Double> hmap = new HashMap<String, Double>();
//        hmap = (HashMap<String, Double>) Retrieve.getSelectedFeatures();
        final_feature_suset_list = new ArrayList<String>();

        int count = 0, endcount = -1, flag = 0, pass = 0;

        ArrayList<String> features = new ArrayList<String>();
        ArrayList<String> features_actual = new ArrayList<String>();

        System.out.println("Before Sorting:");
        Set set = relavant_features.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        Map<String, Double> map = (HashMap<String, Double>) sortByValues(relavant_features);
        System.out.println("After Sorting:");
        Set set2 = map.entrySet();
        Iterator iterator2 = set2.iterator();
        while (iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry) iterator2.next();
            System.out.print(me2.getKey() + ": ");
            String ss = (String) me2.getKey();
            System.out.println(me2.getValue());
            features.add(ss);
        }
        System.out.println("Features: " + features);

        for (int i = features.size() - 1; i >= 0; i--) {
            features_actual.add(features.get(i));
        }
        System.out.println("Features Actaul: " + features_actual);
        String fP = "", fQ = "";
        while (flag != 1 && endcount != count) {
            count = endcount;
            Outer:
            for (int j = 0; j < features_actual.size(); j++) {
                fP = features_actual.get(j);
                System.out.println("fP: "+fP);
                while (fP != null && flag != 1) {
                    Inner:
                    for (int k = features_actual.size() - 1; k >= 0; k--) {
                        fQ = features_actual.get(k);
                        System.out.println("fQ: "+fQ);
                        pass = 0;
                        while (fQ != null && pass != 1) {
                            if (fP == fQ) {
                                break;
                            }
                            double su = (double) calculateSU(fP, fQ);
                            System.out.println("su: "+su);
                            double tQ = (double) relavant_features.get(fQ);
                            if (su >= tQ) {
                                System.out.println("Removed fQ: "+fQ);
                                features_actual.remove(fQ);
                                pass = 1;
                                count++;
                                if (features_actual.size() == k) {
                                    flag = 1;
                                }
                            }
                            else{
                                continue Inner;
                            }
                        }
                        
                    }
                    continue Outer;
                }
            }
        }
        
        final_feature_suset_list = features_actual;
        System.out.println("Final Subset of Feature are: " + final_feature_suset_list);
    }

    public static HashMap<String, Double> sortByValues(HashMap map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                        .compareTo(((Map.Entry) (o2)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    public static double calculateSU(String fP, String fQ) throws Exception {
        Statement st = db.dbconn.connect();
        double f_i_entropy, f_j_entropy;
        ResultSet count_f_i, count_f_j, count_f_i_j;
        ArrayList<Integer> cfi = new ArrayList<Integer>();
        ArrayList<Integer> cfj = new ArrayList<Integer>();
        HashMap<String, Integer> cfj_hs = new HashMap<String, Integer>();
        HashMap<String, ArrayList<Integer>> cfij_hs = new HashMap<String, ArrayList<Integer>>();

        String sql_features_i = "SELECT " + fP + ", COUNT(*) as count FROM report GROUP BY " + fP;
        count_f_i = st.executeQuery(sql_features_i);
        while (count_f_i.next()) {//getting count of fetures i
            cfi.add(count_f_i.getInt("count"));
        }
        f_i_entropy = getEntropy(cfi, count);
        String sql_features_j = "SELECT " + fQ + ", COUNT(*) as count FROM report GROUP BY " + fQ;
        count_f_j = st.executeQuery(sql_features_j);
        while (count_f_j.next()) {//getting count of features j
            cfj.add(count_f_j.getInt("count"));
            cfj_hs.put(count_f_j.getString(fQ), count_f_j.getInt("count"));
        }
        f_j_entropy = getEntropy(cfj, count);
        ArrayList<Integer> temp = null;
        String sql_features_i_j = "SELECT " + fP + "," + fQ + ", COUNT(*) as count FROM report GROUP BY " + fP + "," + fQ;
        count_f_i_j = st.executeQuery(sql_features_i_j);
        while (count_f_i_j.next()) {//getting relational count between feature i and feature j
            String key = count_f_i_j.getString(fQ);
            int value = count_f_i_j.getInt("count");

            if (cfij_hs.containsKey(key)) {
                temp = cfij_hs.get(key);
                temp.add(value);
                cfij_hs.put(key, temp);
            } else {
                temp = new ArrayList<Integer>();
                temp.add(value);
                cfij_hs.put(key, temp);
            }
        }
        //calculatin f-correlation between feature i and feature j
        double su = (double) (((2 * (f_i_entropy - getSubEntropy_one(cfij_hs, cfj_hs, count))) / (f_i_entropy + f_j_entropy)));

        return su;
    }

    public static void main(String[] args) throws SQLException, Exception {
//        String path = "D:\\Anurath\\FastCluster\\build\\web\\dataset\\ThoraricSurgery.txt";
        String path = "F:\\daaset.txt";
//        reading(path);
//        test();
//        process();
        process_one();
//        create_table(path);
    }
}
