package dbLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelLayer.Course;
import modelLayer.Merchandise;
import exceptionsLayer.DatabaseException;

public class DBCourse implements IFDBCourse {
	private  Connection con;
	
	public DBCourse(){
		con = DBConnect.getInstance().getDBcon();
	}
	
	@Override
	public int insertCourse(Course course) throws DatabaseException {
		   int rc = -1;
		   String query = "";
		  query = "INSERT INTO COURSE (name, price, ingredients, isVegetarian, mExists) VALUES ('"
				  + course.getName() + "','"
				  + course.getPrice()+ "','"
				  + course.getIngredients() + "','"
				  + course.getIsVegetarian() + "','"
				  + course.getExists() + "')";	                     

	       System.out.println("insert : " + query);
	      try{
	          Statement stmt = con.createStatement();
	          stmt.setQueryTimeout(5);
	     	  rc = stmt.executeUpdate(query);
	          stmt.close();
	      }
	       catch(SQLException ex){
	          System.out.println("Course not inserted");
	          throw new DatabaseException ("Something else is wrong in DBCourse");
	       }
	       return(rc);
	}

	@Override
	public ArrayList<Course> getAllCourses() {
		return miscWhere("");
	}

	@Override
	public Course findCourse(String name) throws DatabaseException {
		String wClause = "  name = '" + name + "'";
        return singleWhere(wClause);
	}

	@Override
	public int updateCourse(String name, Course c) {
		// New: using a prepared statement (note, this prepared statement is not reused, but it could be.)
		String q = "update course set name=?, price=? ingredients=? isVegetarian=? mExists=? where name="+name;
		int res = 0;
		try(PreparedStatement s = DBConnect.getInstance().getDBcon().prepareStatement(q)) {
			s.setString(1, c.getName());
			s.setFloat(2, c.getPrice());
			s.setString(3, c.getIngredients());
			s.setBoolean(4, c.getIsVegetarian());
			s.setBoolean(5, c.getExists());
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
		private Course getCourse(String wClause){
			ResultSet results;
			Course courseObj = new Course();
	                
		        String query =  buildQuery(wClause);
			try{
		 		Statement stmt = con.createStatement();
		 		stmt.setQueryTimeout(5);
		 		results = stmt.executeQuery(query);	 		
		 		if( results.next() ){
		 			courseObj = buildCourse(results);
	                            stmt.close();
				}
	                        else{
	                        	courseObj = null;
	                        }
			}
		 	catch(Exception e){
		 		System.out.println("Query exception: "+e);
		 	}
			return courseObj;
		}
//course misc where
		private ArrayList<Course> miscWhere(String wClause)
		{
	        ResultSet results;
		    ArrayList<Course> list = new ArrayList<Course>();				
		    String query =  buildQuery(wClause);	  
	            try{
			Statement stmt = con.createStatement();
		 	stmt.setQueryTimeout(5);
		 	results = stmt.executeQuery(query);
		 	
		
			while( results.next() ){
				Course courseObj = new Course();
				courseObj = buildCourse(results);	
	                 list.add(courseObj);	
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
		private Course singleWhere(String wClause)
		{
			ResultSet results;
			Course cusObj = new Course();	                
		    String query =  buildQuery(wClause);
	        System.out.println(query);
			try{ 
		 		Statement stmt = con.createStatement();
		 		stmt.setQueryTimeout(5);
		 		results = stmt.executeQuery(query);		 		
		 		if( results.next() ){
	             cusObj = buildCourse(results);	                           
	             stmt.close();
				}else{
					cusObj = null;
	            }
			}//end try	
		 	catch(Exception e){
		 		System.out.println("Query exception: "+e);
		 	}
			return cusObj;
		}
		//method to build the query
		private String buildQuery(String wClause)
		{
		    String query="SELECT *  FROM Course";
			
			if (wClause.length()>0)
				query=query+" WHERE "+ wClause;
				
			return query;
		}
		//method to build an employee object
		private Course buildCourse(ResultSet results)
	      {   
			Course cusObj = new Course();
	          try{     	 
	        	  cusObj.setName(results.getString("name"));
	        	  cusObj.setPrice(results.getFloat("price"));
	        	  cusObj.setIngredients(results.getString("ingredients"));
	        	  cusObj.setIsVegetarian(results.getBoolean("isVegetarian"));
	        	  cusObj.setExists(results.getBoolean("mExists"));
	        	  
	          }
	         catch(Exception e)
	         {
	             System.out.println("Error in building the course object");
	         }
	         return cusObj;
	      }

		public Merchandise findCourseById(int id) {
			String wClause = "  id = '" + id + "'";
	        return singleWhere(wClause);
		}
}
