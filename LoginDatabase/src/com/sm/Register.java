package com.sm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter pw=response.getWriter();
		
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String rpwd=request.getParameter("rpwd");
		String email=request.getParameter("email");
		long contact=Long.parseLong(request.getParameter("contact"));
		String city=request.getParameter("city");
		
		if(pwd.equals(rpwd)) 
		{
			try
			{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/irotidb","root","root"); 
			
			PreparedStatement ps=con.prepareStatement(  
					"insert into registration values(?,?,?,?,?)");  
					  
					ps.setString(1,name);  
					ps.setString(2,pwd);
					ps.setString(3,email);
					ps.setLong(4,contact);
					ps.setString(5,city);
					int i=ps.executeUpdate();  
					if(i>0)  
					pw.print("<h1>You are successfully registered...</h1>"); 
					else
					pw.println("<h1>Somthing is wrong while inserting data...</h1>");
					
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			pw.println("<h1>User password and re-entered password does not match please enter properly...</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("/Register.html");
			rd.include(request, response);
			
		}
	}

}
