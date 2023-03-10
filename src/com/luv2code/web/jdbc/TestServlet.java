package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define datasource/connection pool for Resource Injection
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. Set up the printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// 2. Get a connection to database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
	
		// 3. Create SQL statement
		String sql = "select * from student";
		myStmt = myConn.createStatement();
		
		// 4. Execute SQL query
		myRs = myStmt.executeQuery(sql);
		
		// 5. Process the result set
		while (myRs.next()) {
			String email = myRs.getString("email");
			out.println(email);
			}
		} 
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
