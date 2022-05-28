import java.util.ArrayList;

public class ShoppingListService {
	ShoppingListRepositiryInterface shoppingListRepositiry;

	public ShoppingListService(ShoppingListRepositiryInterface shoppingListRepositiry) {
		this.shoppingListRepositiry = shoppingListRepositiry;

	}

	public void createShooppingList(Name shoppingListName) {
		ShoppingList shoopingList = new ShoppingList(shoppingListName);
		this.shoppingListRepositiry.save(shoopingList);

	}

	public ShoppingList getShoppingListBy(Name name) throws ShoppingListNotFoundException {
		ShoppingList shoppingList = shoppingListRepositiry.getShoppingListBy(name);
		if (shoppingList == null)
			throw new ShoppingListNotFoundException("ShoppingList: " + name + " not found");
		return shoppingList;
	}

	public ArrayList<ShoppingList> getAllShoppingLists() {
		ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
		shoppingLists = shoppingListRepositiry.getAllShoopingLists();
		return shoppingLists;
	}

	public void addItemToList(Name shoppingListName, Name itemName, Quantity itemQuantity)
			throws ShoppingListNotFoundException, ShoopingListItemAlreadyExistException {
		ShoppingList shoppingList = getShoppingListBy(shoppingListName);
		ShoopingListItem shoopingListItem = new ShoopingListItem(itemName, itemQuantity);
		shoppingList.addItem(shoopingListItem);
		this.shoppingListRepositiry.save(shoppingList);

	}

	public void checkItemFromList(Name name, Name itemName, Money money) throws ShoppingListNotFoundException {
		ShoppingList shoppingList = this.getShoppingListBy(name);				
		
		for (ShoopingListItem item : shoppingList.getItemList()) {
			if (item.getItemName().equals(itemName)) {
				item.setStatus(ItemStatus.GEKAUFT);
				item.setMoney(money);
			}
		}
		shoppingListRepositiry.save(shoppingList);

	}

}
