/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_systems_v1;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author river
 */
public class CRUD_OP {
    private int counter;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
    
    
    public void addAcc(INFO info) {
    String sql1 = "INSERT INTO banking_system.`customer table` (`First Name`, `Last Name`, `Email`, `Phone Number`) VALUES (?, ?, ?, ?)";
    String sql2 = "INSERT INTO banking_system.`Account table` (`Customer Table_Customer ID`, `Account Type`, `Balance`) VALUES (?, ?, ?)";
    if (counter == 0) {
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
         stmt.setString(1, info.getFname());
         stmt.setString(2, info.getLname());
         stmt.setString(3, info.getEmail());
         stmt.setString(4, info.getPnumber());
         stmt.executeUpdate();
         ResultSet generatedKeys = stmt.getGeneratedKeys();
         int ID = 0; 
         if (generatedKeys.next()) {
             ID = generatedKeys.getInt(1);
         }
         stmt2.setInt(1, ID);
         stmt2.setString(2, info.getAcctype());
         stmt2.setDouble(3, info.getBalance());
         stmt2.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    else if  (counter == 1) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql2)){
            stmt.setInt(1, info.getID());
            stmt.setString(2, info.getAcctype());
            stmt.setDouble(3, info.getBalance());
            stmt.executeUpdate();
            counter = 0;
        } catch (SQLException e) {
        e.printStackTrace();
        } 
    }
}
    public void updAcc(INFO info) {
        String Sql = "UPDATE banking_system.`customer table` SET `First Name` = ?, `Last Name` = ?, `Email` = ?, `Phone Number` = ? WHERE `customer ID` = ?";
        String Sql2 = "UPDATE banking_system.`account table` SET `Balance` = ? WHERE `acount ID` = ?";
    if (counter == 0) {
    try (Connection conn = DBConnection.getConnection();
        PreparedStatement stmt2 = conn.prepareStatement(Sql2);) {
        stmt2.setDouble(1, info.getBalance());
        stmt2.setInt(2, info.getID());
        stmt2.executeUpdate();
       
    } catch (SQLException e) {
         e.printStackTrace();
       }
    } else if (counter == 1) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Sql)) {
             stmt.setString(1, info.getFname());
             stmt.setString(2, info.getLname());
             stmt.setString(3, info.getEmail());
             stmt.setString(4, info.getPnumber());
             stmt.setInt(5, info.getID());
             stmt.executeUpdate();
             counter = 0;
         } catch (SQLException e) {
         e.printStackTrace();
       }
    }}
    
    public void deleteAcc(int ID) {
    String sqlCustomer = "DELETE FROM banking_system.`customer Table` WHERE `Customer ID` = ?";
    String sqlAccountByCust = "DELETE FROM banking_system.`account table` WHERE `Customer Table_Customer ID` = ?";
    String sqlSingleAccount = "DELETE FROM banking_system.`account table` WHERE `Acount ID` = ?";

    try (Connection conn = DBConnection.getConnection()) {
        
        if (this.counter == 0) { 
            try (PreparedStatement stmtAcc = conn.prepareStatement(sqlAccountByCust);
                 PreparedStatement stmtCust = conn.prepareStatement(sqlCustomer)) {
                
                stmtAcc.setInt(1, ID);
                int accountsDeleted = stmtAcc.executeUpdate();

                stmtCust.setInt(1, ID);
                int customerDeleted = stmtCust.executeUpdate();
                
                if (customerDeleted > 0) {
                    System.out.println("Successfully deleted Customer ID: " + ID + " along with " + accountsDeleted + " linked account(s).");
                } else {
                    System.out.println("No Customer found with ID: " + ID + " (No changes made).");
                }
            }
            
        } else if (this.counter == 1) {
            try (PreparedStatement stmt2 = conn.prepareStatement(sqlSingleAccount)) {
                stmt2.setInt(1, ID);
                int rowsDeleted = stmt2.executeUpdate();
                
                if (rowsDeleted > 0) {
                    System.out.println("Successfully deleted Account ID: " + ID);
                    this.counter = 0;
                } else {
                    System.out.println("No Account found with ID: " + ID + " (No changes made).");
                }
            }
        } else {
            System.out.println("Unknown operations state. Counter is currently: " + this.counter);
        }

    } catch (SQLException e) {
        System.err.println("Critical Database Error during deletion operation!");
        e.printStackTrace();
    }
}
    public List<INFO> getAllAccounts(int mode) {
        java.util.List<INFO> list = new java.util.ArrayList<>();
    String query = "";
    
    if (mode == 1) {
        query = "SELECT a.`Acount ID`, c.`First Name`, c.`Last Name`, a.`Account Type`, a.`Balance` " +
                "FROM banking_system.`account table` a " +
                "INNER JOIN banking_system.`customer table` c " +
                "ON a.`Customer Table_Customer ID` = c.`Customer ID`"; 
    } else if (mode == 2) {
        query = "SELECT * FROM banking_system.`transaction table`";
    } else if (mode == 3) {
        query = "SELECT t.`Account Table_Acount ID`, t.`Transaction Type`, t.`Amount`, a.`Balance`, t.`Transaction Date` " +
                "FROM banking_system.`transaction table` t " +
                "INNER JOIN banking_system.`account table` a " +
                "ON t.`Account Table_Acount ID` = a.`Acount ID`";
    }

    try (java.sql.Connection conn = DBConnection.getConnection();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
         java.sql.ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            INFO info = new INFO();
            if (mode == 1) {
                info.setID(rs.getInt("Acount ID"));
                info.setFname(rs.getString("First Name"));
                info.setLname(rs.getString("Last Name"));
                info.setAcctype(rs.getString("Account Type"));
                info.setBalance(rs.getDouble("Balance"));
            } else if (mode == 2) {
                info.setAccountTableAcountID(rs.getInt("Account Table_Acount ID"));
                info.setTransactionType(rs.getString("Transaction Type"));
                info.setTransactionAmount(rs.getDouble("Amount"));
            } else if (mode == 3) {
                info.setAccountTableAcountID(rs.getInt("Account Table_Acount ID"));
                info.setTransactionType(rs.getString("Transaction Type"));
                info.setTransactionAmount(rs.getDouble("Amount"));
                info.setBalance(rs.getDouble("Balance")); 
                info.setTransactionDate(rs.getString("Transaction Date"));       
            }
            list.add(info);
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
    
    public boolean deposit(int accountId, double amount) {
    String updateBalanceSQL = "UPDATE banking_system.`account table` SET `Balance` = `Balance` + ? WHERE `Acount ID` = ?";
    String insertHistorySQL = "INSERT INTO banking_system.`transaction table` (`Account Table_Acount ID`, `Transaction Type`, `Amount`, `Transaction Date`) VALUES (?, 'Deposit', ?, NOW())";

    try (java.sql.Connection conn = DBConnection.getConnection()) {
        conn.setAutoCommit(false);

        try (java.sql.PreparedStatement pstmtUpdate = conn.prepareStatement(updateBalanceSQL);
             java.sql.PreparedStatement pstmtInsert = conn.prepareStatement(insertHistorySQL)) {

            pstmtUpdate.setDouble(1, amount);
            pstmtUpdate.setInt(2, accountId);
            pstmtUpdate.executeUpdate();

            pstmtInsert.setInt(1, accountId);
            pstmtInsert.setDouble(2, amount);
            pstmtInsert.executeUpdate();

            conn.commit();
            return true;
        } catch (java.sql.SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return false;
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public int withdraw(int accountId, double amount) {
    String checkBalanceSQL = "SELECT `Balance` FROM banking_system.`account table` WHERE `Acount ID` = ?";
    String updateBalanceSQL = "UPDATE banking_system.`account table` SET `Balance` = `Balance` - ? WHERE `Acount ID` = ?";
    String insertHistorySQL = "INSERT INTO banking_system.`transaction table` (`Account Table_Acount ID`, `Transaction Type`, `Amount`, `Transaction Date`) VALUES (?, 'Withdrawal', ?, NOW())";

    try (java.sql.Connection conn = DBConnection.getConnection()) {
        conn.setAutoCommit(false);

        double currentBalance = 0;
        try (java.sql.PreparedStatement pstmtCheck = conn.prepareStatement(checkBalanceSQL)) {
            pstmtCheck.setInt(1, accountId);
            try (java.sql.ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next()) {
                    currentBalance = rs.getDouble("Balance");
                } else {
                    return -1; 
                }
            }
        }

        if (amount > currentBalance) {
            return 0; 
        }

        try (java.sql.PreparedStatement pstmtUpdate = conn.prepareStatement(updateBalanceSQL);
             java.sql.PreparedStatement pstmtInsert = conn.prepareStatement(insertHistorySQL)) {

            pstmtUpdate.setDouble(1, amount);
            pstmtUpdate.setInt(2, accountId);
            pstmtUpdate.executeUpdate();

            pstmtInsert.setInt(1, accountId);
            pstmtInsert.setDouble(2, amount);
            pstmtInsert.executeUpdate();

            conn.commit();
            return 1; 
        } catch (java.sql.SQLException e) {
            conn.rollback();
            e.printStackTrace();
            return -1;
        }
    } catch (java.sql.SQLException e) {
        e.printStackTrace();
        return -1;
    }
}
}
