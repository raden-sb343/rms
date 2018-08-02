package com.mitrais.rms.controller;

import com.mitrais.rms.dao.DataSourceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println(req.getServletPath()); 
        String path = getTemplatePath(req.getServletPath());
        System.out.println(path);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        try {
            //find by username and password
            //get connection
            Connection con = DataSourceFactory.getConnection();
            
            //create statment
            PreparedStatement ps = con.prepareStatement("select * from user where user_name =? and password =?");
            ps.setString(1, req.getParameter("username"));
            ps.setString(2, req.getParameter("userpass"));
           
            PrintWriter out = new PrintWriter(resp.getWriter());
            ResultSet res = ps.executeQuery();
            if(res.next()){
                System.out.println(res.getString(2));
                if(res.getString(2) != null && res.getString(2).equals(req.getParameter("username"))){
                    out.println("login berhasil");
                    resp.sendRedirect("users/list");
                }else{
                    out.println("login gagal");
                    resp.sendRedirect("login");
                }
            }else{
                resp.sendRedirect("login");
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();;
        }
        
        
    }
}
