package com.luv2code.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet( name = "TestDbServlet", urlPatterns = "/TestDbServlet")
public class TestDbServlet extends HttpServlet
{

    protected void doGet( HttpServletRequest request, HttpServletResponse response )
        throws ServletException, IOException
    {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/web_customer_tracker";
        String user = "springstudent";
        String password = "springstudent";

        String driver = "org.postgresql.Driver";

        PrintWriter out = response.getWriter();
        out.println( "Connecting to database: " + jdbcUrl );

        try
        {
            Class.forName( driver );
            Connection myConn = DriverManager.getConnection( jdbcUrl, user, password );
            out.println( "Connected successful!!!" );
            myConn.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
            throw new ServletException( e );
        }

    }
}
