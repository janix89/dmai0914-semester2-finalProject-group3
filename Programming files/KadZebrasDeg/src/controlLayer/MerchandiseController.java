package controlLayer;
import modelLayer.*;
import dbLayer.*;

public class MerchandiseController {
	private DBMerchandise dbMerchandise;
	private String ingredients;
	private boolean isVegetarian;
	private int quantity;
	private float percent;
	
	public Merchandise createMerchandise(name, price, type){
		switch(type){
		case 1:
			Merchandise m = new Course(name,price);
			m.setIngredients(ingredients);
			m.setIsVegetarian(isVegetarian);
			if (dbMerchandise.insertCourse(m))
				return m;
			else return null;
			break;
		case 2:
			Merchandise m = new Miscellaneous(name,price);
			m.setQuantityInStock(quantity);
			if(dbMerchandise.insertMiscellaneous(m))
				return m;
			else return null;
			break;
		case 3:
			Merchandise m = new Miscellaneous(name,price);
			m.alcoholConcentration(percent);
			if(dbMerchandise.insertDrink(m))
				return m;
			else return null;
			break;
		default:
			return null;
			break;
		}
	}
	
	public Merchandise findMerchandise(String name){
		return dbMerchandise.findMerchandise(name);
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
