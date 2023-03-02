package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Update() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		String url = "jdbc:mysql://localhost:3306/project1";
		String user = "root";
		String password = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con= DriverManager.getConnection(url, user, password); 
			
			if(request.getParameter("hide")!=null)
			{
				String fic; 

				fic=request.getParameter("hide"); 
			
				String name=request.getParameter("user");  
				String passw=request.getParameter("passw"); 				
				String EMail = request.getParameter("email");
				String member_type = request.getParameter("member_type");
				String Gender = request.getParameter("gender");
				int age = Integer.parseInt(request.getParameter("age"));
				
				PreparedStatement ps=null;  
				
				ps=con.prepareStatement("update member set fName=?, password=?, Gender=?, Age=?, EMail=? where id=?"); //sql update query 
				ps.setString(1,name);
				ps.setString(2,passw);
				ps.setString(3, Gender);
				ps.setInt(4, age);
				ps.setString(5, EMail);
				ps.setString(6,fic);
				int result = ps.executeUpdate(); 
				if (result > 0)
					response.sendRedirect("UpdateSuccessfull.jsp");
				else
					response.sendRedirect("UpdateUnsuccessfull.jsp");
				
				con.close(); 
			}	
		}
		catch(Exception e)
		{
			out.println("achchige redda");
			out.println(e);
		}
        }
}
