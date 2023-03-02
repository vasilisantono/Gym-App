package jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SportsServlet")
public class SportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SportsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sport = request.getParameter("sports");
		
		if (sport.equalsIgnoreCase("Badminton"))
			response.sendRedirect("Badminton.jsp");
		else if (sport.equalsIgnoreCase("Futsal"))
			response.sendRedirect("Futsal.jsp");
		else if (sport.equalsIgnoreCase("Table Tennis"))
			response.sendRedirect("TableTennis.jsp");
		else if (sport.equalsIgnoreCase("Swimming"))
			response.sendRedirect("Swimming.jsp");
		
		HttpSession ses = request.getSession();
		ses.setAttribute("workout", sport);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
