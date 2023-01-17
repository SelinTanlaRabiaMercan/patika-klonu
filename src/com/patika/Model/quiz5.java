package com.patika.Model;

import com.patika.Helper.databaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class quiz5 {
    private int id;
    private int ogr_id;
    private int patika_id;

    private String quiz_adi;
    private String soru;
    private String a;
    private String b;
    private String c;
    private String d;
    private String dogrucevap;
    private String dersadi;
    private kurs kurs1;


    public quiz5(int id, int ogr_id, int patika_id, String dersadi, String quiz_adi, String soru, String a, String b, String c, String d, String dogrucevap) {
        this.id = id;
        this.ogr_id = ogr_id;
        this.patika_id = patika_id;
        this.dersadi = dersadi;
        this.quiz_adi = quiz_adi;
        this.soru = soru;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.dogrucevap = dogrucevap;
      //  this.kurs1 = kurs.kursMevcutMu1(patika_id);
    }

    public int getOgr_id() {
        return ogr_id;
    }

    public void setOgr_id(int ogr_id) {
        this.ogr_id = ogr_id;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public static boolean add(String dersadi, String quiz_adi, String soru, String a, String b, String c, String d, String dogrucevap) {
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "INSERT INTO quiz5 (dersadi,quiz_adi,soru,a,b,c,d,dogrucevap) values (?,?,?,?,?,?,?,?)"
            );

            pr.setString(1, (dersadi));
            //  pr.setString(1, dersAdi);
            pr.setString(2, quiz_adi);
            pr.setString(3, soru);
            pr.setString(4, a);
            pr.setString(5, b);
            pr.setString(6, c);
            pr.setString(7, d);
            pr.setString(8, dogrucevap);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<quiz5> getlist() {
        ArrayList<quiz5> quiz5ArrayList = new ArrayList<>();
        quiz5 obj;
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM quiz5"
            );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int ogr_id = resultSet.getInt("ogr_id");
                int patika_id = resultSet.getInt("patika_id");
                String dersadi = resultSet.getString("dersadi");
                String quiz_adi = resultSet.getString("quiz_adi");
                String soru = resultSet.getString("soru");
                String a = resultSet.getString("a");
                String b = resultSet.getString("b");
                String c = resultSet.getString("c");
                String d = resultSet.getString("d");
                String dogrucevap = resultSet.getString("dogrucevap");
                obj = new quiz5(id, ogr_id, patika_id, dersadi, quiz_adi, soru, a, b, c, d, dogrucevap);
                quiz5ArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return quiz5ArrayList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }


    public String getQuiz_adi() {
        return quiz_adi;
    }

    public void setQuiz_adi(String quiz_adi) {
        this.quiz_adi = quiz_adi;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getDogrucevap() {
        return dogrucevap;
    }

    public void setDogrucevap(String dogrucevap) {
        this.dogrucevap = dogrucevap;
    }

    public kurs getKurs1() {
        return kurs1;
    }

    public void setKurs1(kurs kurs1) {
        this.kurs1 = kurs1;
    }
}
