package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class FutsalBooking extends Booking {
    
    private String memberId;
    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";
    
    public FutsalBooking(String memberId){
        super(memberId);
        this.memberId = memberId;
    }
    
    public Integer getBookingCount() throws ClassNotFoundException, SQLException {
        
        Integer FutsalBookings = 0;
        
        if (checkConnection.getConnectionStatus() == true) {
            
            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();
            sql = "SELECT COUNT(*) FROM `sports_trainer_booking` WHERE (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST003') OR (`member_ID` LIKE '"+memberId+"' AND `sports_trainer_ID` LIKE 'ST007')";

            ResultSet rs = st.executeQuery(sql);
            String FutsalBookingCount = "";
            while (rs.next()) {
                FutsalBookingCount = rs.getString(1);
            }
            return FutsalBookings = Integer.parseInt(FutsalBookingCount);
        }

        return 404;
    }
    
}
