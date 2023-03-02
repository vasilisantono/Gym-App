package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Abishaan")
public class Abishaan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Abishaan() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
PrintWriter out = response.getWriter();
		
		String member_name = request.getParameter("Name");
		String member_type = request.getParameter("member_type");
		String member_id = request.getParameter("id");
		DeleteAndUpdate du = new DeleteAndUpdate(member_id,member_name,member_type);
		
		try {
			if (du.isUpdated()) {
				response.sendRedirect("displayTable.jsp");
			}
			else
				out.println("error");
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
