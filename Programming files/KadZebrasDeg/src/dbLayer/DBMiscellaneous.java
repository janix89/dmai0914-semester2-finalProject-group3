package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Course;
import modelLayer.Merchandise;
import modelLayer.Miscellaneous;
import exceptionsLayer.DatabaseException;

public class DBMiscellaneous implements IFDBMiscellaneous {
	private  Connection con;
	
	public DBMiscellaneous() {
		con = DBConnect.getInstance().getDBcon();
	}
	
	@Override
	public int insertMiscellaneous(Miscellaneous miscellaneous) throws DatabaseException{
		  int rc = -1;
		  String query="";
		  query = "INSERT INTO MISCELLANOUS (name, price, quantityInStock, minQuantityInStock) VALUES ('"+
		  miscellaneous.getName() + "','"+
		  miscellaneous.getPrice() + "','" +
		  miscellaneous.getQuantityInStock() + "','"+
		  miscellaneous.getMinQuantityInStock() + "')";
		  
	       System.out.println("insert : " + query);
	      try{
	          Statement stmt = con.createStatement();
	          stmt.setQueryTimeout(5);
	     	  rc = stmt.executeUpdate(query);
	          stmt.close();
	      }
	       catch(SQLException ex){
	          System.out.println("Miscellaneous not inserted");
	          throw new DatabaseException ("Something else is wrong in DBMiscellaneous");
	       }
	       return(rc);
	}

	@Override
	public ArrayList<Miscellaneous> getAllMiscellaneous() {
		return miscWhere("");
	}

	@Override
	public Miscellaneous findMiscellaneous(String name) throws DatabaseException {
		String wClause = "  name = '" + name + "'";
        return singleWhere(wClause);
	}

	@Override
	public int updateMiscellaneous(String name, Miscellaneous m) {
		// New: using a prepared statement (note, this prepared statement is not reused, but it could be.)
		String q = "update miscellaneous set name=?, price=? quantityInStock=? minQuantityInStock=? where name="+name;
		int res = 0;
		try(PreparedStatement s = DBConnect.getInstance().getDBcon().prepareStatement(q)) {
			s.setString(1, m.getName());
			s.setFloat(2, m.getPrice());
			s.setInt(3, m.getQuantityInStock());
			s.setInt(4,m.getMinQuantityInStock());
			res = s.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch(NullPointerException  npe) {
			
		}
		return res;
	}
	//private method
		private Miscellaneous getMiscellaneous(String wClause){
			ResultSet results;
			Miscellaneous miscellaneousObj = new Miscellaneous();
	                
		        String query =  buildQuery(wClause);
			try{
		 		Statement stmt = con.createStatement();
		 		stmt.setQueryTimeout(5);
		 		results = stmt.executeQuery(query);	 		
		 		if( results.next() ){
		 			miscellaneousObj = buildMiscellaneous(results);
	                            stmt.close();
				}
	                        else{
	                        	miscellaneousObj = null;
	                        }
			}
		 	catch(Exception e){
		 		System.out.println("Query exception: "+e);
		 	}
			return miscellaneousObj;
		}
		//course misc where
		private ArrayList<Miscellaneous> miscWhere(String wClause)
		{
	        ResultSet results;
		    ArrayList<Miscellaneous> list = new ArrayList<Miscellaneous>();				
		    String query =  buildQuery(wClause);	  
	            try{
			Statement stmt = con.createStatement();
		 	stmt.setQueryTimeout(5);
		 	results = stmt.executeQuery(query);
		 	
		
			while( results.next() ){
				Miscellaneous miscellaneousObj = new Miscellaneous();
				miscellaneousObj = buildMiscellaneous(results);	
	            list.add(miscellaneousObj);	
			}//end while
	             stmt.close();    				
			}//end try	
		 	catch(Exception e){
		 		System.out.println("Query exception - select: "+e);
				e.printStackTrace();
		 	}
			return list;
		}
		
		// Single where is used when we only select one thing 	
		private Miscellaneous singleWhere(String wClause)
		{
			ResultSet results;
			Miscellaneous miscellaneousObj = new Miscellaneous();	                
		    String query =  buildQuery(wClause);
	        System.out.println(query);
			try{ 
		 		Statement stmt = con.createStatement();
		 		stmt.setQueryTimeout(5);
		 		results = stmt.executeQuery(query);		 		
		 		if( results.next() ){
		 			miscellaneousObj = buildMiscellaneous(results);	                           
	             stmt.close();
				}else{
					miscellaneousObj = null;
	            }
			}//end try	
		 	catch(Exception e){
		 		System.out.println("Query exception: "+e);
		 	}
			return miscellaneousObj;
		}
		//method to build the query
		private String buildQuery(String wClause)
		{
		    String query="SELECT *  FROM Miscellanous";
			
			if (wClause.length()>0)
				query=query+" WHERE "+ wClause;
				
			return query;
		}
		//method to build an employee object
		private Miscellaneous buildMiscellaneous(ResultSet results)
	      {   
			Miscellaneous miscellanousObj = new Miscellaneous();
	          try{     	 
	        	miscellanousObj.setName(results.getString("name"));
	        	miscellanousObj.setPrice(results.getFloat("price"));
	        	miscellanousObj.setQuantityInStock(results.getInt("quantityInStock"));
	        	miscellanousObj.setMinQuantityInStock(results.getInt("minQuantityInStock"));
	          }
	         catch(Exception e)
	         {
	             System.out.println("Error in building the miscellaneous object");
	         }
	         return miscellanousObj;
	      }

		public Merchandise findMiscellaneousById(int id) {
			String wClause = "  id = '" + id + "'";
	        return singleWhere(wClause);
		}

}
