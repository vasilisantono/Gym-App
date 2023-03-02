package jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/MakeSchedule")
public class MakeSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MakeSchedule() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		DBConnection db = new DBConnection();
	
		PrintWriter out = response.getWriter();
		
		try {
			if (db.isConnected()) {
				String workout = request.getParameter("workout");
				HttpSession ses = request.getSession();
				ses.setAttribute("workout", workout);
				WorkoutHandler wh = new WorkoutHandler(workout);
				
				if (wh.getWorkout().equals("Yoga")) {
					response.sendRedirect("Yoga.jsp");
				}
				else if (wh.getWorkout().equals("Cardio")) { 
					response.sendRedirect("Cardio.jsp");
				}
				else if (wh.getWorkout().equals("Pilates")) {
					response.sendRedirect("Pilates.jsp");
				}
				else if (wh.getWorkout().equals("Weights")) {
					response.sendRedirect("Weights.jsp");
				}
				else
					response.sendRedirect("MakeSchedule.jsp");
			}
			else {
				out.println("not connected");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
