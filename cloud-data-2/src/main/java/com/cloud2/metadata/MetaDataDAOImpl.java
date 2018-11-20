/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cloud2.metadata;

import com.cloud2.pojo.FileMetaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.PieChart;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author kerch
 */
public class MetaDataDAOImpl implements MetaDateDAO {

    private DataSource ds;

    public MetaDataDAOImpl() {
        try {
            InitialContext context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/cloudData2DataSource");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void createFile(String name, String type, String size, long createdDate, long currentDate, String user) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cloud_data_2.files (name, type, size, createdDate, currentDate, user_id=(SELECT id FROM cloud_data_2.users where name=?) VALUES(?,?,?,?,?)))");
            ps.setString(1, user);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, size);
            ps.setTimestamp(5, new Timestamp(createdDate));
            ps.setTimestamp(6, new Timestamp(currentDate));
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateFile(String name, String type, String size, long currentDate, String user) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO cloud_data_2.files (name, type, size, currentDate, user_id=(SELECT id FROM cloud_data_2.users where name=?) VALUES(?,?,?,?)))");
            ps.setString(1, user);
            ps.setString(2, name);
            ps.setString(3, type);
            ps.setString(4, size);
            ps.setTimestamp(5, new Timestamp(currentDate));
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteFile(int id) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM cloud_data_2.files WHERE user_id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean fileExist(String fileName, String fileMask, String userName) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM cloud_data_2.files WHERE name=? and fileMask=? and userName=(SELECT id FROM cloud_data_2.users WHERE name=?)");
            ps.setString(1, fileName);
            ps.setString(2, fileMask);
            ps.setString(3, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean bucketExist(String userName) {
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM userName=(SELECT id FROM cloud_data_2.users WHERE name=?)");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public FileMetaData getFileMetadataByFileId(int id) {
        FileMetaData fmeta = new FileMetaData();
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT FROM cloud_data_2.files WHERE id=?)");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fmeta.setName(rs.getString("name"));
                fmeta.setName(rs.getString("type"));
            }
            return fmeta;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<FileMetaData> getAll(String userName) {
        List<FileMetaData> metaDataFiles = new ArrayList<>();
        try (Connection con = ds.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cloud_data_2.files WHERE id=(SELECT id FROM cloud_data_2.users WHERE user_name=?))");
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FileMetaData fm = new FileMetaData();
                fm.setName(rs.getString("name"));
                fm.setSize(rs.getLong("size"));
                fm.setType(rs.getString("type"));
                fm.setCreatedDate(rs.getLong("created_date"));
                fm.setId(rs.getInt("id"));
                metaDataFiles.add(fm);
            }
            return metaDataFiles;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
