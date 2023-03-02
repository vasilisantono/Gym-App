package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SportsOrGym")
public class SportsOrGym extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SportsOrGym() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String athletic = request.getParameter("athletic");
		
		HttpSession session = request.getSession();
		session.setAttribute("Athletic", athletic);
		
		if (athletic.equalsIgnoreCase("Sports"))
			response.sendRedirect("MakeSportsSchedule.jsp");
		else
			response.sendRedirect("MakeSchedule.jsp");
		
	}
        
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
