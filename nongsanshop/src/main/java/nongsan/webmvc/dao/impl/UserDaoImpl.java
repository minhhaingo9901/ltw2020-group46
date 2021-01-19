package nongsan.webmvc.dao.impl;

import nongsan.webmvc.dao.UserDao;
import nongsan.webmvc.jdbc.connectDB;
import nongsan.webmvc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends connectDB implements UserDao {

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO user(roleid,name,email,phone,username,password,status,created) VALUES (?,?, ?, ?, ?, ?,?, ?)";
        Connection con = connectDB.getConnect();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, user.getRoleid());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getStatus());
            ps.setString(8,user.getCreated());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user where id = ?";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User get(int id) {
        User user = new User();
        String sql = "select * from user where id=?";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreated(rs.getString("created"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void edit(User user) {
        String sql = "Update user set roleid=? name=?, email=?, phone=?, username=?, password=?, status=? created=? where id=?";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getRoleid());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setInt(7, user.getStatus());
            ps.setString(8,user.getCreated());
            ps.setInt(9,user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        Connection conn = connectDB.getConnect();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setRoleid(rs.getInt("roleid"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getInt("status"));
                user.setCreated(rs.getString("created"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return users;
    }

    @Override
    public boolean checkLogin(String username, String password, int roleid, int status) {
        Connection conn = connectDB.getConnect();
        String sql = "SELECT * FROM user WHERE username ='" + username + "'and password='" + password
                + "' and roleid='" + 2 + "' and status ='" + 1 + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkLogin1(String username, String password, int roleid, int status) {
        Connection conn = connectDB.getConnect();
        String sql = "SELECT * FROM user WHERE username ='" + username + "'and password='" + password
                + "' and roleid='" + 1 + "' and status ='" + 1 + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

