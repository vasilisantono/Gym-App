package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String user = request.getParameter("admin_name");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		AdminLogin lp = new AdminLogin(user,password);
		
		try {
			if (lp.isLoggedIn()) {
				HttpSession ses = request.getSession();
				ses.setAttribute("admin_name", user);
				response.sendRedirect("admin.jsp");
			}
			else {
				out.println("Mmm...ses");
			}
		}catch(ClassNotFoundException | SQLException e) {
			out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
