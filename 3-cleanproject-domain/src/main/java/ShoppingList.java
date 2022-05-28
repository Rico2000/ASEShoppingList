import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingList {
	private UUID shoppingListUuid;
	private Name shoppingListName;
	private List<ShoopingListItem> itemList = new ArrayList<ShoopingListItem>();

	public ShoppingList(Name shoppingListName) {
		this.shoppingListUuid = UUID.randomUUID();
		this.shoppingListName = shoppingListName;
	}

	public ShoppingList(UUID uuid, Name shoppingListName) {
		this.shoppingListUuid = uuid;
		this.shoppingListName = shoppingListName;
	}


    public void addItem(ShoopingListItem shoopingListItem) throws ShoopingListItemAlreadyExistException {
		for(ShoopingListItem item: itemList){
			if(item.getItemName().equals(shoopingListItem.getItemName())){
				throw new ShoopingListItemAlreadyExistException("");
			}
		}
		itemList.add(shoopingListItem);

	}



	@Override
	public String toString() {
		return "Name: " + shoppingListName.toString();
	}

	public UUID getShoppingListUuid() {
		return shoppingListUuid;
	}

	public Name getShoppingListName() {
		return shoppingListName;
	}

	public List<ShoopingListItem> getItemList() {
		return itemList;
	}

	public Money getGesamtkosten() throws IlleaglQuantityException {
		Money gesamtkosten = new Money(0);
		for(ShoopingListItem item: itemList) {
			gesamtkosten = gesamtkosten.add(item.getMoney());
		}
		return gesamtkosten;
	}

}
