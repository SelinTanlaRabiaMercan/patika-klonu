package com.patika.Model;

import com.patika.Helper.Helper;
import com.patika.Helper.databaseConnector;

import java.sql.*;
import java.util.ArrayList;

public class usertable {
    private int id;
    private String name;
    private String username;
    private String password;
    private String kullanici_turu;


    public usertable() {
    }

    public usertable(int id, String name, String username, String password, String kullanici_turu) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.kullanici_turu = kullanici_turu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKullanici_turu() {
        return kullanici_turu;
    }

    public void setKullanici_turu(String kullanici_turu) {
        this.kullanici_turu = kullanici_turu;
    }

    //databasedeki kullanıcıları arrayliste atmak
    public static ArrayList<usertable> getList() {
        ArrayList<usertable> usertableArrayList = new ArrayList<>();
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM usertable"
            );
            usertable obj;
            while (resultSet.next()) {
                obj = new usertable();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setKullanici_turu(resultSet.getString("kullanici_turu"));
                usertableArrayList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usertableArrayList;
    }

    public static boolean delete(int id) {
        ArrayList<dersler> derslerArrayList = dersler.getlistByUser(id);//kullanıcının kursları
        for (dersler d : derslerArrayList) {
            dersler.delete(d.getId());
        }
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "DELETE FROM usertable WHERE id=?"
            );
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    //DATABASEYE VERİ EKLEMEK
    public static boolean add(String name, String username, String password, String kullanici_turu) {
        usertable kullaniciBul = usertable.kullaniciMevcutMu(username);
        if (kullaniciBul != null) {
            Helper.mesajPenceresi("Kullanıcı Adı kullanılıyor", "Hata", "Tamam");
            return false;
        }
        try {
            PreparedStatement prs = databaseConnector.getInstance().prepareStatement(
                    "INSERT INTO usertable(name,username,password,kullanici_turu)" +
                            "VALUES (?,?,?,?)"
            );
            prs.setString(1, name);
            prs.setString(2, username);
            prs.setString(3, password);
            //prs.setString(4,kullanici_turu);
            prs.setObject(4, kullanici_turu, Types.OTHER);
            return prs.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    //tablo üzerinde güncelleme
    public static boolean update(int id, String name, String username, String password) {
        usertable kullaniciBul1 = usertable.kullaniciMevcutMu(username);
        if (kullaniciBul1 != null && kullaniciBul1.getId() != id) {
            Helper.mesajPenceresi("Kullanıcı Adı kullanılıyor", "Hata", "Tamam");
            return true;
        }
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "UPDATE usertable SET name=?,username=?,password=? WHERE id=?"
            );
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, password);
            //pr.setString(4,kullanici_turu);
            pr.setInt(4, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());

        }
        return true;
    }


    //Eklemeye çalıştığımız kullanıcı zaten varsa yada olan kullanıcı adı alınmaya çalışılıyosa mesaj versin
    public static usertable kullaniciMevcutMu(String username) {
        usertable obj = null;
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "SELECT * FROM usertable " +
                            "WHERE username=?"
            );
            pr.setString(1, username);
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                obj = new usertable();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setKullanici_turu(resultSet.getString("kullanici_turu"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static ArrayList<usertable> search(String name, String username) {
        ArrayList<usertable> usertableArrayList = new ArrayList<>();
        String sorgu = "SELECT * FROM usertable " +
                "WHERE name ILIKE '%{{name}}%'  AND " +
                "username ILIKE '%{{username}}%'";
        try {
            Statement statement = databaseConnector.getInstance().createStatement();
            sorgu = sorgu.replace("{{name}}", name);
            sorgu = sorgu.replace("{{username}}", username);
            System.out.println(sorgu);
            ResultSet resultSet = statement.executeQuery(sorgu);

            usertable obj;
            while (resultSet.next()) {
                obj = new usertable();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setKullanici_turu(resultSet.getString("kullanici_turu"));
                usertableArrayList.add(obj);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usertableArrayList;
    }

    public static usertable getfecgirisicin(String username, String password) {
        usertable obj = null;
        String sorgu = "SELECT * FROM usertable WHERE username = ? AND password = ?";
        //sorgu.replace("{username}","username");
        //sorgu.replace("{password}","password");
        try {
            PreparedStatement statement = databaseConnector.getInstance().prepareStatement(sorgu);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            System.out.println(sorgu);
            if (resultSet.next()) {
                switch (resultSet.getString("kullanici_turu")) {
                    case "operator":
                        obj = new operator();
                        break;
                    case "educator":
                        obj=new educator();
                        break;
                    default:
                        obj = new usertable();
                        //break;
                }
              //  obj = new usertable();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setKullanici_turu(resultSet.getString("kullanici_turu"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return   obj;
    }

    public static usertable kullaniciMevcutMu(int id) {
        usertable obj = null;
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(
                    "SELECT * FROM usertable " +
                            "WHERE id=?"
            );
            pr.setInt(1, id);
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                obj = new usertable();
                obj.setId(resultSet.getInt("id"));
                obj.setName(resultSet.getString("name"));
                obj.setUsername(resultSet.getString("username"));
                obj.setPassword(resultSet.getString("password"));
                obj.setKullanici_turu(resultSet.getString("kullanici_turu"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }


}
