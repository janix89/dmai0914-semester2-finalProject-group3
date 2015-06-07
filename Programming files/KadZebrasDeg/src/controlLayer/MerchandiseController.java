package controlLayer;
import modelLayer.*;
import dbLayer.*;
import exceptionsLayer.DatabaseException;

public class MerchandiseController {
	private DBCourse dBCourse;
	private DBMiscellaneous dBMiscellaneous;
	private DBDrink dBDrink;
	private String ingredients;
	private boolean isVegetarian;
	private int quantity;
	private float percent;
	
	public Merchandise createMerchandise(String name, float price, int type) throws DatabaseException{
		switch(type){
		case 1:
			Course m = new Course();
			m.setName(name);
			m.setPrice(price);
			m.setIngredients(ingredients);
			m.setIsVegetarian(isVegetarian);
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
			d.setAlcoholConcetration(percent);
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
			if(name.equals(dBCourse.getAllCourses().get(x))){
				return dBCourse.getAllCourses().get(x);
			}
		}
		for(int x = 0; x < dBMiscellaneous.getAllMiscellaneous().size(); x++){
			if(name.equals(dBMiscellaneous.getAllMiscellaneous().get(x))){
				return dBMiscellaneous.getAllMiscellaneous().get(x);
			}
		}
		for(int x = 0; x < dBDrink.getAllDrinks().size(); x++){
			if(name.equals(dBDrink.getAllDrinks().get(x))){
				return dBDrink.getAllDrinks().get(x);
			}
		}
		
		return null;
	}
	
	public void setIngredients(String ingredients){
		this.ingredients = ingredients;
	}
	
	public void setIsVegetarian(boolean veg){
		this.isVegetarian = veg;
	}
	
	public void setQuantityStock(int quantity){
		this.quantity = quantity;
	}
	
	public void setAlchoholPercent(float percent){
		this.percent = percent;
	}
	
	
}
