import java.util.ArrayList;

public class MockShoppingListRepository implements ShoppingListRepositiryInterface {
	ArrayList<ShoppingList> listS = new ArrayList<ShoppingList>();

	public ArrayList<ShoppingList> getAllShoopingLists() {
		return listS;
	}

	public void save(ShoppingList shoopingList) {
		ShoppingList r = null;
		for(ShoppingList list:listS) {
			if(list.getShoppingListUuid().equals(shoopingList.getShoppingListUuid())) {
				r = list;
			}

		}
		listS.remove(r);
		listS.add(shoopingList);


	}

	public ShoppingList getShoppingListBy(Name name) {
		for (ShoppingList list : listS) {
			if (list.getShoppingListName().equals(name)) {
				return list;
			}

		}
		return null;
	}

}
