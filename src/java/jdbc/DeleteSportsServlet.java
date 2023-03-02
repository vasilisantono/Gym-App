package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteSportsServlet")
public class DeleteSportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteSportsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		String value = request.getParameter("yesno");
		out.println(value);
		
		if (value.equals("yes")) {
			String member_ID = request.getParameter("member_ID");
			Date date = Date.valueOf(request.getParameter("Date"));
			String trainer_ID = request.getParameter("trainer_ID");
			String schedule_ID = request.getParameter("schedule_ID");
			
			DeleteSportsBooking del = new DeleteSportsBooking(member_ID,trainer_ID,date,schedule_ID);
			
			try {
				if (del.isDeleted()) {
					response.sendRedirect("Profile.jsp");
					out.println("okay");
				}
				else {
					out.println("cannot be deleted");
				}
			} catch (ClassNotFoundException | SQLException e) {
				out.println(e);
			}
		}
		else if (value.equals("no"))  {
			response.sendRedirect("goBack.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
