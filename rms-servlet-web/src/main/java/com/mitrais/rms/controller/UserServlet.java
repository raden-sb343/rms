package com.mitrais.rms.controller;

import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

@WebServlet("/users/*")
public class UserServlet extends AbstractController
{
    
    protected void doFilter(HttpServletRequest req, HttpServletResponse res){
        System.out.println("masuk ko masuk");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {        
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        req.setAttribute("theMsg", "");       

        if ("/list".equalsIgnoreCase(req.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();
            List<User> users = userDao.findAll();
            req.setAttribute("users", users);
        }
        
        if ("/del".equalsIgnoreCase(req.getPathInfo())){
            if(req.getParameter("use_id") != null){
                UserDaoImpl ud = new UserDaoImpl();
                User u = new User(Long.parseLong(req.getParameter("use_id")), null, null);
                if(ud.delete(u)){
                    req.setAttribute("theMsg", "Data berhasil dihapus");
                }else{
                    req.setAttribute("theMsg", "Data gagal dihapus. Silahkan coba lagi");
                }
                resp.sendRedirect("list");
                return;
            }
        }
        if ("/form".equalsIgnoreCase(req.getPathInfo())){
            if(req.getParameter("use_id") != null){
                UserDaoImpl ud = new UserDaoImpl();
                Optional<User> users = ud.find( Long.parseLong(req.getParameter("use_id")) );
                if(users.isPresent()){
                    req.setAttribute("users", users.get());
                }
            }
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        
        String path = getTemplatePath(req.getServletPath()+req.getPathInfo());
        
        if(req.getParameter("use_id") == null || req.getParameter("use_id") == ""){
            User u = new User();
            u.setUserName(req.getParameter("username"));
            u.setPassword(req.getParameter("userpass"));
            
            UserDaoImpl userD = new UserDaoImpl();
            if(userD.save(u)){
                res.sendRedirect("list");
            }else{
                req.setAttribute("theMsg", "User gagal disimpan. Silahkan coba lagi");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, res);
            }
        }else{
            User u = new User(Long.parseLong(req.getParameter("use_id")), req.getParameter("username"), req.getParameter("userpass"));
            
            UserDaoImpl userD = new UserDaoImpl();
            if(userD.update(u)){
                res.sendRedirect("list");
            }else{
                req.setAttribute("theMsg", "User gagal disimpan. Silahkan coba lagi");
        
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
                requestDispatcher.forward(req, res);
            }
        }
    }
}
