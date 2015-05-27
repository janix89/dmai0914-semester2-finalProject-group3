package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Drink;
import modelLayer.Merchandise;
import exceptionsLayer.DatabaseException;

public class DBDrink implements IFDBDrink {
	private  Connection con;
	
	public DBDrink(){
		con = DBConnect.getInstance().getDBcon();
	}
	
	@Override
	public int insertDrink(Drink drink) throws DatabaseException{
		  int rc = -1;
		  String query = "";
		  query = "INSERT INTO DRINK(name, price, quantityInStock, alcoholConcentration, minQuantityInStock) VALUES ('"+
				  drink.getName() + "','" +
				  drink.getPrice()+ "','" +
				  drink.getQuantityInStock() + "','" +
				  drink.getAlcoholConcetration() + "','" +
				  drink.getMinQuantityInStock() + "')";	                     

	       System.out.println("insert : " + query);
	      try{
	          Statement stmt = con.createStatement();
	          stmt.setQueryTimeout(5);
	     	  rc = stmt.executeUpdate(query);
	          stmt.close();
	      }
	       catch(SQLException ex){
	          System.out.println("Drink not inserted");
	          throw new DatabaseException ("Something else is wrong in DBDrink");
	       }
	       return(rc);
	}

	@Override
	public ArrayList<Drink> getAllDrinks() {
		return miscWhere("");
	}

	@Override
	public Drink findDrink(String name) throws DatabaseException {
		String wClause = "  name = '" + name + "'";
        return singleWhere(wClause);
	}
	

	@Override
	public int updateDrink(String name, Drink d) {
		// New: using a prepared statement (note, this prepared statement is not reused, but it could be.)
		String q = "update drink set name=?, price=? quantityInStock=? alcoholConcentration=? minQuantityInStock=? where name="+name;
		int res = 0;
		try(PreparedStatement s = DBConnect.getInstance().getDBcon().prepareStatement(q)) {
			s.setString(1, d.getName());
			s.setFloat(2, d.getPrice());
			s.setInt(3, d.getQuantityInStock());
			s.setDouble(4, d.getAlcoholConcetration());
			s.setInt(5, d.getMinQuantityInStock());
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
	private Drink getDrink(String wClause){
		ResultSet results;
		Drink drinkObj = new Drink();
                
	        String query =  buildQuery(wClause);
		try{
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);	 		
	 		if( results.next() ){
                            drinkObj = buildDrink(results);
                            stmt.close();
			}
                        else{
                        	drinkObj = null;
                        }
		}
	 	catch(Exception e){
	 		System.out.println("Query exception: "+e);
	 	}
		return drinkObj;
	}
	//course misc where
			private ArrayList<Drink> miscWhere(String wClause)
			{
		        ResultSet results;
			    ArrayList<Drink> list = new ArrayList<Drink>();				
			    String query =  buildQuery(wClause);	  
		            try{
				Statement stmt = con.createStatement();
			 	stmt.setQueryTimeout(5);
			 	results = stmt.executeQuery(query);
			 	
			
				while( results.next() ){
					Drink drinkObj = new Drink();
					drinkObj = buildDrink(results);	
		            list.add(drinkObj);	
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
			private Drink singleWhere(String wClause)
			{
				ResultSet results;
				Drink drinkObj = new Drink();	                
			    String query =  buildQuery(wClause);
		        System.out.println(query);
				try{ 
			 		Statement stmt = con.createStatement();
			 		stmt.setQueryTimeout(5);
			 		results = stmt.executeQuery(query);		 		
			 		if( results.next() ){
		             drinkObj = buildDrink(results);	                           
		             stmt.close();
					}else{
						drinkObj = null;
		            }
				}//end try	
			 	catch(Exception e){
			 		System.out.println("Query exception: "+e);
			 	}
				return drinkObj;
			}
			//method to build the query
			private String buildQuery(String wClause)
			{
			    String query="SELECT *  FROM Drink";
				
				if (wClause.length()>0)
					query=query+" WHERE "+ wClause;
					
				return query;
			}
			//method to build an employee object
			private Drink buildDrink(ResultSet results)
		      {   
				Drink drinkObj = new Drink();
		          try{     	 
		        	  drinkObj.setName(results.getString("name"));
		        	  drinkObj.setPrice(results.getFloat("price"));
		        	  drinkObj.setQuantityInStock(results.getInt("quantityInStock"));
		        	  drinkObj.setAlcoholConcetration(results.getFloat("alcoholConcentration"));
		        	  drinkObj.setMinQuantityInStock(results.getInt("minQuantityInStock"));
		          }
		         catch(Exception e)
		         {
		             System.out.println("Error in building the drink object");
		         }
		         return drinkObj;
		      }

			public Merchandise findDrinkById(int id) {
				String wClause = "  id = '" + id + "'";
		        return singleWhere(wClause);
			}

}
