package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class YogaBooking extends Booking{
    
    private String memberId;
    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";
    
    public YogaBooking(String memberId){
        super(memberId);
        this.memberId = memberId;
    }
    
    public Integer getBookingCount() throws ClassNotFoundException, SQLException {

        Integer yogaBookings;
        if (checkConnection.getConnectionStatus() == true) {
            
            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();

                sql = "SELECT COUNT(*) FROM trainer_booking WHERE member_ID LIKE '"+memberId+"' AND workout_ID LIKE 'W001'";

                ResultSet rs = st.executeQuery(sql);
                String yogaBookingCount = "";
                while (rs.next()) {
                    yogaBookingCount = rs.getString(1);
                }
                return yogaBookings = Integer.parseInt(yogaBookingCount);
        }
        return 404;
    }
    
}
