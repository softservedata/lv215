package com.softserve.edu.oms.data;

import com.softserve.edu.oms.enums.ErrorMessagesEnum;
import com.softserve.edu.oms.enums.SQLQueries;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which is responsible for communication with database.  
 * 
 * @version  1.0
 * @since 15.12.16
 * @author  Roman Raba 
 * 
 */
public class  DBUtils implements IExternalData {

	private String username = System.getenv("db_username");
	private String password = System.getenv("db_password");
	private String url = System.getenv("db_url");

	
	/** Method, which creates connection with Database.
     */
	private Connection createConnection(){
        Connection con;
        try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return con;
    }

    
	/** Method, which closes connection with Database.
	 * @see DBUtils#closeConnection(Connection, Statement, ResultSet)
     */
    private void closeConnection(Connection con, Statement st) throws Exception{
            closeConnection(con, st, null);
    }
    
    
    /** Method, which closes connection with Database.
     * @see DBUtils#closeConnection(Connection, Statement)
     */
    private void closeConnection(Connection con, Statement st, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (st != null) {
            st.close();
        }
        if (con != null) {
            con.close();
        }
    }

    
    /** Method, which creates list of list string type from Database by sqlquery. 
     * @param absoluteFilePath - path to file
     * @param name - sheet name 
     */
	public List<List<String>> getAllCells(String absoluteFilePath, String name) {
		List<List<String>> allCells = new ArrayList<List<String>>();
		List<String> rowCells = null;
		Statement st = null;
		ResultSet rs = null;
		int columnCount = 0;
		Connection con = createConnection();
      
		try {
			st = con.createStatement();
			rs = st.executeQuery(SQLQueries.GET_ALL_USERS_JOIN_ROLE.getQuery());
			columnCount = rs.getMetaData().getColumnCount();
			//
			while (rs.next()) {
				rowCells = new ArrayList<String>();
				for (int i = 1; i <= columnCount; i++) {
					rowCells.add(rs.getString(i));
					System.out.print("+++\t" + rs.getString(i) + "\t");
				}
				allCells.add(rowCells);
				System.out.println();
			}
			closeConnection(con, st, rs);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
		}
		return allCells;
	}
 
	
	/** Method, which deletes user or users from Database by sqlquery. 
     * @param sqlQuery - query 
     * @param value - value which adds to sqlquery and responsibles to Login or FirstName etc.   
     */
	public void deleteUsersFromDB(String sqlQuery, String value){
        Statement st = null;
        Connection con = createConnection();
 
        try {
            st = con.createStatement();
            st.execute(sqlQuery + "\'" + value + "\'");
            closeConnection(con, st);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
    }
 

	/** Method, which gets list of string type from Database by sqlquery
	 * which is responsible for one column from table. 
     * @param sqlQuery - query 
     * @param value1 - value for adding to sqlquery
     * @param value2 - value for adding to sqlquery  
     */
	public List<String> getOneColumn(String sqlQuery, String nameOfColumn, String value1, String value2) {
 		List<String> listOfOneColumn = new ArrayList<>();	 
		Statement st = null;
		ResultSet rs = null;
		Connection con = createConnection();
		try {
			st = con.createStatement();
			switch (nameOfColumn) {
				case ("lastName"):  rs = st.executeQuery(sqlQuery + "\'" + value1 + "%\'");				                                                 				                    
					break;
				case ("login"):  rs = st.executeQuery(sqlQuery + "\'%" + value1 + "%\'");    
					break;
				case ("role"):  rs = st.executeQuery(sqlQuery + "\'%" + value2 + "%\'");   				
					break;
			}

			while (rs.next()) {
				listOfOneColumn.add(rs.getString(1));
			}
		  closeConnection(con, st, rs);
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
		}
		return listOfOneColumn;
	}

	
	/** Method, which gets user from Database by sqlquery. 
     * @param login - value of Login
     */
	public User getUserByLogin(String login) {
		User user = null;
		Statement st = null;
		ResultSet rs = null;
		int columnCount;
		Connection con = createConnection();
	 
		try {
			st = con.createStatement();
			String query=SQLQueries.GET_USER_BY_LOGIN_JOIN_ROLE.getQuery() +"\'"+ login+"\';";
			System.out.println(query);
			rs = st.executeQuery(query);
			columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				user = new User("", "", "", "", "", "", "");
				for (int i = 1; i <= columnCount; i++) {
					switch (i) {
						case 1:
							user.setLoginname(rs.getString(i));
							break;
						case 2:
							user.setFirstname(rs.getString(i));
							break;
						case 3:
							user.setLastname(rs.getString(i));
							break;
						case 4:
							user.setPassword(rs.getString(i));
							break;
						case 5:
							user.setEmail(rs.getString(i));
							break;
						case 6:
							user.setRole(rs.getString(i));
							break;
						case 7:
							user.setRegion(rs.getString(i));
							break;
					}
				}
			}
		  closeConnection(con, st, rs);
//		} catch (java.sql.SQLException e) {
		}  catch (Exception e) {
			throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
		}
		return user;
	}

	
	/** Method, which gets list of string type from Database by sqlquery
	 * which is responsible for column Login from table. 
     * @param sqlQuery - query 
     */
    public List<String> getLogins(String sqlQuery){
        List<String> logins = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        Connection con = createConnection();
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                logins.add(rs.getString("Login"));
                //System.out.println("getting "+rs.getString("login"));
            }
          closeConnection(con, st, rs);
        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }
        return logins;
    }
    
   

    /** Method, which gets number of users from Database by sqlquery.
     */
    @Step("Count all users in DB")
    public int countAllUsers(){ 

        Statement st = null;
        ResultSet rs = null;
        int userCount = 0;

        Connection con = createConnection();

        try {
            st = con.createStatement();
            rs = st.executeQuery(SQLQueries.COUNT_ALL_USERS.getQuery());
            
            rs.next();
            userCount = rs.getInt(1);
            closeConnection(con, st, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return userCount;
    }

    
    /** Method, which gets list of first five users from Database by sqlquery.
     */
    @Step("Get first five users from DB")
    public List<User> getTopFiveUsers() {

        List<User> users = new ArrayList<>();
        Statement st;
        ResultSet rs;

        Connection con = createConnection();

        try {
            st = con.createStatement();
            rs = st.executeQuery(SQLQueries.GET_5_USERS_JOIN_ROLE.getQuery());

            while (rs.next()) {
                User user = new User(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                        );
                users.add(user);
            }
            closeConnection(con, st,rs);

        } catch (Exception e) {
            throw new RuntimeException(ErrorMessagesEnum.SQL_EXCEPTION_MESSAGE.message, e);
        }

        return users;
    }

    
    
    /** Method, which verifies if the user is in Database by sqlquery.
     */
    @Step("Verification - is a user in DB")
    public boolean verifyThatUserIsInDB(String loginOfUser){ 

        Statement st;
        ResultSet rs;
        boolean userIsInDB = false; 
        Connection con = createConnection();

        try{
            st = con.createStatement();
            rs = st.executeQuery(SQLQueries.GET_USER_BY_LOGIN.getQuery() + "\'" + loginOfUser + "\'");
            userIsInDB = rs.next();
            closeConnection(con, st,rs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        return userIsInDB;
    }
}