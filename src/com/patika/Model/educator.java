package com.patika.Model;
import com.patika.Helper.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class educator extends usertable{
    public static educator getFetch(int id){
        educator obj = null;
        String query = "SELECT * FROM usertable WHERE id = ?";
        try {
            PreparedStatement pr = databaseConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = new educator();
                obj.setId((rs.getInt("id")));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("uname"));
                obj.setPassword(rs.getString("pass"));
                obj.setKullanici_turu(rs.getString("type"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}
