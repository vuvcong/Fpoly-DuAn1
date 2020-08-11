/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import poly.com.helper.Connect;
import poly.com.model.TheLoaiSach;

/**
 *
 * @author PC
 */
public class TheLoaiSachDAO extends Connect{
    public ArrayList<TheLoaiSach> load(){
        ArrayList<TheLoaiSach> lists = new ArrayList<TheLoaiSach>();
        try{
           String sql="SELECT * FROM THELOAISACH";
           Statement stm = con.createStatement();
           ResultSet rs= stm.executeQuery(sql);
           lists.clear();
            while(rs.next()){
                TheLoaiSach tls = new TheLoaiSach(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3));
                lists.add(tls); 
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return lists;
}

    public ArrayList<String> listcbovitri()
   {
       ArrayList<String> listcbo = new ArrayList<String>();
       try
       {
           String sql="SELECT DISTINCT ViTri FROM TheLoaiSach";
           Statement stm = con.createStatement();
           ResultSet rs =stm.executeQuery(sql);
           while(rs.next())
           {
              listcbo.add(rs.getString(1));
           }
       }
       catch(Exception ex)
       {
          ex.printStackTrace();
       }
       
       return listcbo;
   }
    public int insert(TheLoaiSach tls)
    {
        try
        {
            String sql="Insert into TheLoaiSach values (?,?,?)";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, tls.getMaTheLoai());
                ps.setString(2, tls.getTenTheLoai());
                ps.setString(3, tls.getViTri());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            
        }
        return -1;
    }
    public int update(TheLoaiSach tls)
    {
        try
        {
            String sql="Update TheLoaiSach set TenTheLoai = ? , ViTri = ? Where MaTheLoai = ?";
            PreparedStatement ps =con.prepareStatement(sql);
                ps.setString(1, tls.getTenTheLoai());
                ps.setString(2, tls.getViTri());
                ps.setString(3, tls.getMaTheLoai());
             return ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }
    public boolean delete(String maTheLoaiSach) {
        try {
            String sql = "DELETE FROM THELOAISACH WHERE MATHELOAI=?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, maTheLoaiSach);
            return pstm.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<TheLoaiSach> SearchTen(String tenTheLoaiSach)
    {
        ArrayList<TheLoaiSach> lists = new ArrayList<TheLoaiSach>();
        try
        {
            String sql="SELECT * FROM THELOAISACH WHERE TenTheLoai like N'%" 
                    + tenTheLoaiSach +  "%' or MaTheLoai like '%" + tenTheLoaiSach + "%' or ViTri like N'%" 
                    + tenTheLoaiSach + "%'" ;
            Statement statement= con.createStatement();
            ResultSet rs =statement.executeQuery(sql);
          while(rs.next())
          {
            TheLoaiSach tls = new TheLoaiSach();
            tls.setMaTheLoai(rs.getString(1));
            tls.setTenTheLoai(rs.getString(2));
            tls.setViTri(rs.getString(3));
            lists.add(tls);          
          }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return lists;
    }

    public void indstls() throws JRException{
    try {

        JasperDesign jd = JRXmlLoader.load("src/poly/com/print/XuatDSTheLoai.jrxml");
        JasperReport jr = JasperCompileManager.compileReport("src/poly/com/print/XuatDSTheLoai.jrxml");
        JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap(), con);
        JasperViewer.viewReport(jp);
//        JasperExportManager.exportReportToPdfFile(jp, "test1.pdf");
        } catch (JRException e) {
        
        }
    }
}
