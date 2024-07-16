/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.SinhVien;
/**
 *
 * @author ADMIN
 */
public class SinhVienRepos {
    private Connection conn;
    public SinhVienRepos(){
        conn = DbConnect.getConnection();
    }
    public List<SinhVien> getListSinhVien(){
        List<SinhVien> list = new ArrayList<>();
        String select = "SELECT * FROM SinhVien";
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(select);
            while(rs.next()){
                SinhVien sv = new SinhVien();
                sv.setMaSV(rs.getString(1));
                sv.setTenSV(rs.getString(2));
                sv.setGioiTinh(rs.getBoolean(3));
                sv.setNamSinh(rs.getInt(4));
                list.add(sv);
            }
        }catch(Exception e){
            System.out.println("e"+e);
        }
        return list;
    }
    public void them(SinhVien sv) throws Exception{
        String sql = "INSERT INTO SinhVien values (?,?,?,?)";
        try{
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getTenSV());
            ps.setBoolean(3, sv.isGioiTinh());
            ps.setInt(4, sv.getNamSinh());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public void sua(SinhVien sv) throws Exception{
        String sql = "UPDATE SinhVien SET Tên = ?,Gioitinh = ?,NamSinh = ? WHERE Mã = ?";
        try{
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, sv.getTenSV());
            ps.setBoolean(2, sv.isGioiTinh());
            ps.setInt(3, sv.getNamSinh());
            ps.setString(4, sv.getMaSV());
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
}
