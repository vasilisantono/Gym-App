package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class SwimmingBooking extends Booking {
    
    private String memberId;
    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";
    
    public SwimmingBooking(String memberId){
        super(memberId);
        this.memberId = memberId;
    }
    
    public Integer getBookingCount() throws ClassNotFoundException, SQLException {
        
        Integer SwimmingBookings = 0;
    
        if (checkConnection.getConnectionStatus() == true) {
            
            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();

            sql = "SELECT COUNT(*) FROM `sports_trainer_booking` WHERE (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST004') OR (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST008')";

            ResultSet rs = st.executeQuery(sql);
            String SwimmingBookingCount = "";
            while (rs.next()) {
                SwimmingBookingCount = rs.getString(1);
            }
            return SwimmingBookings = Integer.parseInt(SwimmingBookingCount);
        }

        return 404;
    }
    
}
