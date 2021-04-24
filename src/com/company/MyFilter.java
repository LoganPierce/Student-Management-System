package com.company;

import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
public class MyFilter implements Filter{
    public void init(FilterConfig arg0) throws ServletException {}
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String uname = req.getParameter("uname");
        String email = req.getParameter("email");
        String regno = req.getParameter("regno");
        String dpt = req.getParameter("dpt");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        Boolean Verification = true;
        if(!(name.length() > 0 && name.length() <= 20)) {
            Verification = false;
            out.println("The length of the name of a student must be less then 20");
        }
        if(!(uname.matches("^[A-Za-z]{1}[a-zA-Z0-9]+$")))
        {
            Verification = false;
            out.println("Username may consist of both alphabet and number.But username must be start from alphabet.");
        }
        if(!(email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")))
        {
            Verification = false;
            out.println("Email should be according to the standard format of email");
        }
        if(!(regno.matches("^[A-Z]{3}[0-9]{6}$")))
        {
            Verification = false;
            out.println("The length of the registration number of a student must be less then 10. It must contain first three capital alphabets and then 6 digits.");
        }
        if(!(pass.length() < 17 && pass.length() > 5)) {
            Verification = false;
            out.println("Length of the password is greater than 5 or not less then 17");
        }
        if(!(pass.matches(repass)))
        {
            Verification = false;
            out.println("Password doesnot match with retype password");
        }
        // After Verification - Insert data into database
        if(Verification)
        {
            out.println("Validation Filter: Data Successfully Verified!");
            out.println("<br>");
            chain.doFilter(req, resp);//sends request to next resource out.print("filter isinvoked after");
        }
        else {
            out.println("Validation Filter: Data Not Successfully Verified!");
            out.println("<br>");
            RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
            rd.include(req, resp);//method may be include or forward
        }
    }
    public void destroy() {}
}

