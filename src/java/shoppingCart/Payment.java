package shoppingCart;

import Connection.ServerConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Payment", urlPatterns = {"/Payment"})
public class Payment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ServerConnection checkConnection;
            checkConnection = ServerConnection.getInstance();

            HttpSession session = request.getSession();
            String memberID = session.getAttribute("userID").toString();

            try {
                checkConnection.setConnection();

                Member member = new Member();
                String firstName = member.getFirstName(memberID);
                String membershipType = member.getMembershipType(memberID);

                Invoice invoice = new Invoice();
                Integer invoiceId = invoice.getInvoiceId();

                Calculate calculateMembershipFee = new MemberShipFee(membershipType);
                double membershipFee = calculateMembershipFee.getTotal();

                Booking yogaBooking = new YogaBooking(memberID);
                int yogaBookingTotal = yogaBooking.getBookingCount();

                Booking cardioBooking = new CardioBooking(memberID);
                int cardioBookingTotal = cardioBooking.getBookingCount();

                Booking pilateBooking = new PilateBooking(memberID);
                int pilateBookingTotal = pilateBooking.getBookingCount();

                Booking weightsBooking = new WeightsBooking(memberID);
                int weightsBookingTotal = weightsBooking.getBookingCount();

                Calculate calculateGymTotal = new GymReservationTotal(yogaBookingTotal, cardioBookingTotal, pilateBookingTotal, weightsBookingTotal);
                Double gymTotal = calculateGymTotal.getTotal();

                Booking badmintonBooking = new BadmintonBooking(memberID);
                int badmintonBookingTotal = badmintonBooking.getBookingCount();

                Booking tableTennisBooking = new TableTennisBooking(memberID);
                int tableTennisBookingTotal = tableTennisBooking.getBookingCount();

                Booking futsalBooking = new FutsalBooking(memberID);
                int futsalBookingTotal = futsalBooking.getBookingCount();

                Booking swimmingBooking = new SwimmingBooking(memberID);
                int swimmingBookingTotal = swimmingBooking.getBookingCount();

                Calculate calculateSportsTotal = new SportsReservationTotal(badmintonBookingTotal, tableTennisBookingTotal, futsalBookingTotal, swimmingBookingTotal);
                Double SportsTotal = calculateSportsTotal.getTotal();

                Discount discount = new Discount();
                Integer discountPercentage = discount.getDiscountPerentage(memberID);
                Double discountAmount = discount.getDiscountAmount((membershipFee + gymTotal + SportsTotal), discountPercentage);

                Calculate calculateTotalPayment = new TotalPayment(membershipFee, gymTotal, SportsTotal, discountAmount);
                double totalPayment = calculateTotalPayment.getTotal();

                request.getSession().setAttribute("MemberFirstName", firstName);
                request.getSession().setAttribute("InvoiceId", invoiceId);
                request.getSession().setAttribute("MembershipType", membershipType);

                request.getSession().setAttribute("YogaBookings", yogaBookingTotal);
                request.getSession().setAttribute("CardioBookings", cardioBookingTotal);
                request.getSession().setAttribute("PilatesBookings", pilateBookingTotal);
                request.getSession().setAttribute("WeightsBookings", weightsBookingTotal);
                request.getSession().setAttribute("GymActivityTotal", gymTotal);

                request.getSession().setAttribute("BadmintionBookings", badmintonBookingTotal);
                request.getSession().setAttribute("TennisBookings", tableTennisBookingTotal);
                request.getSession().setAttribute("FutsalBookings", futsalBookingTotal);
                request.getSession().setAttribute("SwimmingBookings", swimmingBookingTotal);
                request.getSession().setAttribute("SportsActivityTotal", SportsTotal);

                request.getSession().setAttribute("MembershipFee", membershipFee);
                request.getSession().setAttribute("gymReservationTotal", gymTotal);
                request.getSession().setAttribute("sportReservationTotal", SportsTotal);
                request.getSession().setAttribute("TotalPayment", totalPayment);
                
                request.getSession().setAttribute("discountPercentage", discountPercentage);
                request.getSession().setAttribute("discountAmount", discountAmount);
                
                request.getRequestDispatcher("checkout.jsp").forward(request, response);

            } catch (Exception e) {
                out.print(e);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
