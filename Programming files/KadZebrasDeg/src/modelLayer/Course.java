package modelLayer;

public class Course extends Merchandise {
	private String ingredients;
	private boolean isVegetarian;

	// constructor
	public Course() {

	}

	// sets
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public void setIsVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	// gets
	public String getIngredients() {
		return ingredients;
	}

	public boolean getIsVegetarian() {
		return isVegetarian;
	}
}
