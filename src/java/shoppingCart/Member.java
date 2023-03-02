package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class Member {

    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";

    public String getFirstName(String userId) throws ClassNotFoundException, SQLException {
        
        if (checkConnection.getConnectionStatus() == true) {
            
            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();
            
            sql = "SELECT fname FROM member WHERE ID LIKE '"+userId+"'";

            ResultSet rs = st.executeQuery(sql);
            String firstName = "";
            while (rs.next()) {
                firstName = rs.getString(1);
            }
            returnValue = firstName;
        }
        return returnValue;
    }
    
    public String getMembershipType(String userId) throws ClassNotFoundException, SQLException {

        if (checkConnection.getConnectionStatus() == true) {

            newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();
            
            sql = "SELECT `member_type` FROM `member` WHERE `ID` LIKE '"+userId+"'";

            ResultSet rs = st.executeQuery(sql);
            String memberType = "";
            while (rs.next()) {
                memberType = rs.getString(1);
            }
            returnValue = memberType;
        }
        return returnValue;
    }
} 
