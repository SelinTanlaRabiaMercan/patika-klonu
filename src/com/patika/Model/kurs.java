package com.patika.Model;

import com.patika.Helper.Helper;
import com.patika.Helper.databaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class kurs {
    private int id;
    private String name;

    public kurs(int id) {
        this.id = id;
        this.name = name;
    }
    public kurs(){
        this.id=id;
        this.name=name;
    }

    public kurs(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public  int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static kurs fetch(int id) {
        kurs obj = null;
        try {
            PreparedStatement statement = databaseConnector.getInstance().prepareStatement(
                    "SELECT * FROM kurs WHERE id=?"
            );
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                obj = new kurs(id);
                //obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return obj;
    }

    public static ArrayList<kurs> getList() {
        ArrayList<kurs> kursArrayList = new ArrayList<>();
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM kurs"
            );
            kurs obj;
            while (resultSet.next()) {
                obj = new kurs();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                kursArrayList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kursArrayList;
    }

    public static ArrayList<kurs> getList(int id, String name) {
        ArrayList<kurs> kursArrayList = new ArrayList<>();
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM kurs"
            );
            kurs obj;
            while (resultSet.next()) {
                obj = new kurs(id, name);
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                kursArrayList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return kursArrayList;
    }

    public static boolean add(String name) {

        try {
            PreparedStatement prs = databaseConnector.getInstance().prepareStatement(
                    "INSERT INTO kurs (name) values (?)"
            );
            prs.setString(1, name);
            return prs.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }/*
public static kurs getfec(int id){
    kurs obj = null;

    try {
        Statement statement = databaseConnector.getInstance().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM kurs WHERE id = ?" );

        if (resultSet.next()) {

            obj = new kurs(id);
            obj.setId(resultSet.getInt("id"));
            obj.setName(resultSet.getString("name"));
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    return obj;
}
*/
    public static kurs kursMevcutMu1(int id) {
        kurs obj = null;
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "SELECT * FROM kurs " +
                            "WHERE id=?"
            );
            pr.setInt(1, id);
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                obj = new kurs(id);
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static boolean update(String name,int id) {
        try {
            PreparedStatement prs = databaseConnector.getInstance().prepareStatement(
                    "UPDATE kurs SET name = ? WHERE id=?"
            );
            prs.setString(1, name);
            prs.setInt(2,id);
            return prs.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public static boolean delete(int id) {
        ArrayList<dersler> derslerArrayList=dersler.getlistByUser(id);//kullanıcının kursları
        for (dersler d:derslerArrayList){
            dersler.delete(d.getId());
        }
        try {
            PreparedStatement prs = databaseConnector.getInstance().prepareStatement(
                    "DELETE FROM kurs WHERE id=?"
            );
            prs.setInt(1, id);
            return prs.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
