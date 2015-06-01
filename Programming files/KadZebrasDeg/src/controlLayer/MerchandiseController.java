package controlLayer;
import java.util.ArrayList;

import modelLayer.Course;
import modelLayer.Drink;
import modelLayer.Merchandise;
import modelLayer.Miscellaneous;
import dbLayer.DBCourse;
import dbLayer.DBDrink;
import dbLayer.DBMiscellaneous;
import exceptionsLayer.DatabaseException;

public class MerchandiseController {
	private DBCourse dBCourse;
	private DBMiscellaneous dBMiscellaneous;
	private DBDrink dBDrink;
	
	public MerchandiseController(){
		dBCourse = new DBCourse();
		dBDrink = new DBDrink();
		dBMiscellaneous = new DBMiscellaneous();
	}
	
	
	public Merchandise createMerchandise(String name, float price, int type, String ingredients, boolean isVegetarian, int quantity, float ac, String typeOfCourse) throws DatabaseException{
		switch(type){
		case 1:
			Course m = new Course();
			m.setName(name);
			m.setPrice(price);
			m.setIngredients(ingredients);
			m.setIsVegetarian(isVegetarian);
			m.setTypeOfCourse(typeOfCourse);
			if (dBCourse.insertCourse(m) != -1)
				return m;
			else return null;
		case 2:
			Miscellaneous mi = new Miscellaneous();
			mi.setQuantityInStock(quantity);
			mi.setName(name);
			mi.setPrice(price);
			if(dBMiscellaneous.insertMiscellaneous(mi) != -1)
				return mi;
			else return null;
		case 3:
			Drink d = new Drink();
			d.setAlcoholConcetration(ac);
			d.setName(name);
			d.setPrice(price);
			if(dBDrink.insertDrink(d) != -1)
				return d;
			else return null;
		default:
			return null;
		}
	}
	
	public Merchandise findMerchandise(String name){
		for(int x = 0; x < dBCourse.getAllCourses().size(); x++){
			if(name.equals(dBCourse.getAllCourses().get(x).getName())){
				return dBCourse.getAllCourses().get(x);
			}
		}
		for(int x = 0; x < dBMiscellaneous.getAllMiscellaneous().size(); x++){
			if(name.equals(dBMiscellaneous.getAllMiscellaneous().get(x).getName())){
				return dBMiscellaneous.getAllMiscellaneous().get(x);
			}
		}
		for(int x = 0; x < dBDrink.getAllDrinks().size(); x++){
			if(name.equals(dBDrink.getAllDrinks().get(x).getName())){
				return dBDrink.getAllDrinks().get(x);
			}
		}
		
		return null;
	}
	public ArrayList<Merchandise> getAllMerchandise (){
		ArrayList<Merchandise> temp = new ArrayList<>();
		for(Course c: dBCourse.getAllCourses()){
			temp.add(c);
		}
		for(Drink d: dBDrink.getAllDrinks()){
			temp.add(d);
		}
		for(Miscellaneous m: dBMiscellaneous.getAllMiscellaneous()){
			temp.add(m);
		}
		return temp;
	}


	public void updateMerchandise(Merchandise m, int mType) {
			if(mType==1){
				dBCourse.updateCourse(m.getName(), (Course)m);
			}				
			else if(mType==2){
				dBMiscellaneous.updateMiscellaneous(m.getName(), (Miscellaneous)m);
			}
			else{
				dBDrink.updateDrink(m.getName(), (Drink)m);
			}
	}
	
	
}
