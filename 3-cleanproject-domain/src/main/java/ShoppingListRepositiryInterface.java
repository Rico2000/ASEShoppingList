import java.util.ArrayList;

public interface ShoppingListRepositiryInterface {
	public ArrayList<ShoppingList> getAllShoopingLists();

	public void save(ShoppingList shoopingList);

	public ShoppingList getShoppingListBy(Name name);

}
