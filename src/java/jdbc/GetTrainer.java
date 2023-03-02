package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GetTrainer")
public class GetTrainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetTrainer() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String trainer = request.getParameter("trainer");
		String athletic = session.getAttribute("Athletic").toString();
		String trainer_ID = null;
		PrintWriter out = response.getWriter();
		
		out.println(trainer);
		
		new ProcessTrainer(trainer);
		
		try {
			if (athletic.equalsIgnoreCase("Sports"))
				trainer_ID = ProcessTrainer.getSportsTrainerID();
			else if (athletic.equalsIgnoreCase("Gym"))
				trainer_ID = ProcessTrainer.getGymTrainerID();
			
			if (trainer_ID != null) {
				session.setAttribute("trainer_ID", trainer_ID);
				session.setAttribute("trainer_name", trainer);
				response.sendRedirect("SelectTime.jsp");
			}
			else
				out.println(trainer_ID);
		} catch (ClassNotFoundException | SQLException e) {
			out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
