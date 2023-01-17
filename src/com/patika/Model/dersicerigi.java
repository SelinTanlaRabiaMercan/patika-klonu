package com.patika.Model;

import com.patika.Helper.databaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dersicerigi {
    private int id;
    private String baslik;
    private String aciklama;
    private String quiz;
    private String ytlink;
    private String dersadi;

    public dersicerigi(int id, String baslik, String aciklama, String quiz, String ytlink, String dersadi) {
        this.id = id;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.quiz = quiz;
        this.ytlink = ytlink;
        this.dersadi = dersadi;
    }
    public dersicerigi(){

    }
    public static ArrayList<dersicerigi> getList() {
        ArrayList<dersicerigi> dersicerigiArrayList = new ArrayList<>();
        try {
            Statement statement=databaseConnector.getInstance().createStatement();
            ResultSet resultSet= statement.executeQuery(
                    "select*from dersicerigi"
            );
            dersicerigi obj;
            while (resultSet.next()){
                obj=new dersicerigi();
                obj.setId(resultSet.getInt("id"));
                obj.setBaslik(resultSet.getString("baslik"));
                obj.setAciklama(resultSet.getString("aciklama"));
                obj.setQuiz(resultSet.getString("quiz"));
                obj.setYtlink(resultSet.getString("ytlink"));
                obj.setDersadi(resultSet.getString("dersadi"));
                dersicerigiArrayList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dersicerigiArrayList;
    }

    public static boolean add(String baslik,String aciklama,String quiz,String ytlink,String dersadi){
        try {
            PreparedStatement pr= databaseConnector.getInstance().prepareStatement(
                    "INSERT INTO dersicerigi (baslik,aciklama,quiz,ytlink,dersadi) values (?,?,?,?,?) "
            );
            pr.setString(1,baslik);
            pr.setString(2,aciklama);
            pr.setString(3,quiz);
            pr.setString(4,ytlink);
            pr.setString(5,dersadi);
            return pr.executeUpdate()!=-1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getYtlink() {
        return ytlink;
    }

    public void setYtlink(String ytlink) {
        this.ytlink = ytlink;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }
}
