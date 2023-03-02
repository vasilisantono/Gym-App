package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class BadmintonBooking extends Booking {
    
    private String memberId;
    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";
    
    public BadmintonBooking(String memberId){
        super(memberId);
        this.memberId = memberId;
    }
    
    public Integer getBookingCount() throws ClassNotFoundException, SQLException {
        
        Integer BadmintonBookings = 0;
        if (checkConnection.getConnectionStatus() == true) {
            
            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();
            sql = "SELECT COUNT(*) FROM `sports_trainer_booking` WHERE (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST005') OR (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST001') OR (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST009')";

                ResultSet rs = st.executeQuery(sql);
                String BadmintonBookingCount = "";
                while (rs.next()) {
                    BadmintonBookingCount = rs.getString(1);
                }
                return BadmintonBookings = Integer.parseInt(BadmintonBookingCount);
            }
        
        return 404;
    }
    
}
