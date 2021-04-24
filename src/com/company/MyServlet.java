package com.company;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        Connection c = null;
        PreparedStatement pstmt;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String ukey = String.valueOf(0);
        String vkey = String.valueOf(0);
        String name = request.getParameter("name");
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String regno = request.getParameter("regno");
        String dpt = request.getParameter("dpt");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        try {
            Class.forName("org.postgresql.Driver");
            c =
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbuni",
                            "postgres", "admin");
            //c = DriverManager.getConnection(database_connection_string,database_user_name, database_user_password);
            pstmt = c.prepareStatement("insert into student(ukey,vkey,sname,username,email,regno,department,password) values (?,?,?,?,?,?,?,?)");
            pstmt.setString(1, ukey);
            pstmt.setString(2, vkey);
            pstmt.setString(3, name);
            pstmt.setString(4, uname);
            pstmt.setString(5, email);
            pstmt.setString(6, regno);
            pstmt.setString(7, dpt);
            pstmt.setString(8, pass);
            pstmt.executeUpdate();
            out.write("Data Successfully Inserted into Database!");
        } catch (Exception ex) {
            out.write(ex + "");
        }
    }
}