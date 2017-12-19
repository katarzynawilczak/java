package lab8;

import java.io.*;
import java.util.*;
import java.sql.*;

public class DB{
  private Connection conn = null;
  private Statement stmt = null;
  private ResultSet rs = null;
  public void connect(){
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = 
        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/wilczak",
                                    "wilczak","oAY1PKEAkrpMxyUG");
 
      
 
    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }catch(Exception e){e.printStackTrace();}	
  }

public void  listNames(){
    try {
      connect();
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT * FROM books");
 	int i=0;
      while(rs.next()){
    	  i++;
    String isbn = rs.getString(1);
	String title = rs.getString(2);
	String author = rs.getString(3);
	String year = rs.getString(4);

        System.out.println("Pozycja nr " + i+ ": "+isbn+ " " + title+ " " + author + " " + year);
      }
    }catch (SQLException ex){
      // handle any errors
 
    }finally {
      // zwalniamy zasoby, które nie bêd¹ potrzebne
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException sqlEx) { } // ignore
        rs = null;
      }
 
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException sqlEx) { } // ignore
 
        stmt = null;
      }
    }
  }

public void findByAuthor(String author_) {
	try {
	      connect();
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT * FROM books");
	      while(rs.next()){
	  	    String author = rs.getString(3);

	    	if(author.equals(author_)) { 
	    		 String isbn = rs.getString(1);
	    		 String title = rs.getString(2);
	    		 String year = rs.getString(4);

	    		System.out.println("Pozycja znaleziona: "+isbn+ " " + title+ " " + author + " " + year);
	    	}
	     }
	    }catch (SQLException ex){
	      // handle any errors
	 
	    }finally {
	      // zwalniamy zasoby, które nie bêd¹ potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }

public void findByISBN(String isbn_) {
	try {
	      connect();
	      stmt = conn.createStatement();
	      rs = stmt.executeQuery("SELECT * FROM books");
	      while(rs.next()){
	  	    String isbn = rs.getString(1);

	    	if(isbn.equals(isbn_)) { 
	    		 String author = rs.getString(3);
	    		 String title = rs.getString(2);
	    		 String year = rs.getString(4);

	    		System.out.println("Pozycja znaleziona: "+isbn+ " " + title+ " " + author + " " + year);
	    	}
	     }
	    }catch (SQLException ex){
	      // handle any errors
	 
	    }finally {
	      // zwalniamy zasoby, które nie bêd¹ potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }
public void addUser(){
	try {
	connect();
    stmt = conn.createStatement();
    stmt.executeUpdate(
            "INSERT INTO books VALUES ('1234567891251','Nowy wspanialy swiat2', 'Aldous UsuwanyZnowu',2008)");
	}catch (SQLException ex){
	      // handle any errors
		 
	    }finally {
	      // zwalniamy zasoby, które nie bêd¹ potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }

public void deleteByISBN(String isbn_){
	try {
	connect();
    stmt = conn.createStatement();
    stmt.executeUpdate(
    		"DELETE FROM books WHERE isbn = '" +isbn_ + "'");
	}catch (SQLException ex){
	      // handle any errors
		 
	    }finally {
	      // zwalniamy zasoby, które nie bêd¹ potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }


public void deleteByAuthor(String author_){
	try {
	connect();
    stmt = conn.createStatement();
    stmt.executeUpdate(
    		"DELETE FROM books WHERE author = '" +author_ + "'");
	}catch (SQLException ex){
	      // handle any errors
		 
	    }finally {
	      // zwalniamy zasoby, które nie bêd¹ potrzebne
	      if (rs != null) {
	        try {
	          rs.close();
	        } catch (SQLException sqlEx) { } // ignore
	        rs = null;
	      }
	 
	      if (stmt != null) {
	        try {
	          stmt.close();
	        } catch (SQLException sqlEx) { } // ignore
	 
	        stmt = null;
	      }
	    }
	  }


public static void main(String []args){
	DB db = new DB();
	//db.findByAuthor("Haruki Murakami");
	//db.findByISBN("1234567891236");
	//db.addUser();
	//db.deleteByISBN("1234567891250");
	//db.deleteByAuthor("Aldous UsuwanyZnowu");
	db.listNames();

}
}
