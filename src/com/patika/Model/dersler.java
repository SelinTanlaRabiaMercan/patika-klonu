package com.patika.Model;

import com.patika.Helper.databaseConnector;
import com.patika.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class dersler {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;
    private usertable egitmen;
    private kurs kurs12;

    public dersler(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.kurs12 = kurs.kursMevcutMu1(this.patika_id);
        this.egitmen = usertable.kullaniciMevcutMu(this.user_id);
    }

    public dersler(int id, int patika_id,String name) {
        this.id = id;
        this.patika_id = patika_id;
        this.kurs12 = kurs.kursMevcutMu1(this.patika_id);
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public usertable getEgitmen() {
        return egitmen;
    }

    public void setEgitmen(usertable egitmen) {
        this.egitmen = egitmen;
    }

    public kurs getKurs12() {
        return kurs12;
    }

    public void setKurs12(kurs kurs12) {
        this.kurs12 = kurs12;
    }

    public static boolean update( int user_id, int patika_id, String name, String lang,int id) {
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "UPDATE dersler SET user_id = ? , patika_id = ? , name = ? , lang = ? WHERE id = ? "
            );
          //  int patikaid= kurs.getfec((patika_id)).getId();
            pr.setInt(1, user_id);
            pr.setInt(2, (patika_id));
            pr.setString(3, name);
            pr.setString(4, lang);
            pr.setInt(5, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean delete(int id) {
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "DELETE FROM dersler WHERE id=?"
            );
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean add(int user_id, int patika_id, String name, String lang) {
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "INSERT INTO dersler (user_id,patika_id,name,lang) values (?,?,?,?)"
            );
            pr.setInt(1, user_id);
            pr.setInt(2, patika_id);
            pr.setString(3, name);
            pr.setString(4, lang);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static ArrayList<dersler> getlist() {
        ArrayList<dersler> derslerArrayList = new ArrayList<>();
        dersler obj;
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM dersler"
            );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                obj = new dersler(id, user_id, patika_id, name, lang);
                derslerArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return derslerArrayList;
    }
    public static ArrayList<dersler> getlistByUser(int user_id) {
        ArrayList<dersler> derslerArrayList = new ArrayList<>();
        dersler obj;
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM dersler WHERE user_id="+user_id
            );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int user_id1 = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");
                obj = new dersler(id, user_id1, patika_id, name, lang);
                derslerArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return derslerArrayList;
    }
    public static ArrayList<dersler> getlisteducatoricin() {
        ArrayList<dersler> derslerArrayList = new ArrayList<>();
        dersler obj;
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id,patika_id,name FROM dersler"
            );
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
               // int user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                //String lang = resultSet.getString("lang");
                obj = new dersler(id,  patika_id,name);
                derslerArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return derslerArrayList;
    }
    public static ArrayList<dersler> getlisteducatoricin1(int user_id) {
        ArrayList<dersler> derslerArrayList = new ArrayList<>();
        dersler obj;
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, patika_id,name FROM dersler where user_id= "+user_id
            );
            while (resultSet.next()) {
               int  id = resultSet.getInt("id");
//                user_id = resultSet.getInt("user_id");
                int patika_id = resultSet.getInt("patika_id");
                String name = resultSet.getString("name");
                //String lang = resultSet.getString("lang");
                obj = new dersler(id, patika_id,name);
                derslerArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return derslerArrayList;
    }

}
