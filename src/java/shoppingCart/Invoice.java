package shoppingCart;

import Connection.ServerConnection;
import java.sql.*;

public class Invoice {
    
    ServerConnection checkConnection;
    private Connection newCon = null;
    private String returnValue = "Database Not Connected";
    private String sql = "";
    
    public Integer getInvoiceId() throws ClassNotFoundException, SQLException {
        
        Integer newInvoiceId = 0;
        
        if (checkConnection.getConnectionStatus() == true) {

             newCon = checkConnection.getConnection();
            Statement st = newCon.createStatement();

            sql = "SELECT invoice_ID FROM invoice ORDER BY invoice_ID DESC LIMIT 1";

            ResultSet rs = st.executeQuery(sql);
            String invoiceId = "";
            while (rs.next()) {
                invoiceId = rs.getString(1);
            }
            
            if(invoiceId == ""){
                newInvoiceId = 1000;
            }else
                
            newInvoiceId = (Integer.parseInt(invoiceId)+1);
        }
        return newInvoiceId;
    }
    
}
