import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingListRepository implements ShoppingListRepositiryInterface {
	private static final String FILE_PATH_LIST = "data/shopplist.csv";
	private static final String FILE_PATH_ITEM = "data/items.csv";

	public ArrayList<ShoppingList> getAllShoopingLists() {
		List<String> fileContentList = CSVReader.readFile(FILE_PATH_LIST);
		List<String> fileContentItem = CSVReader.readFile(FILE_PATH_ITEM);
		ArrayList<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();
		ArrayList<ShoopingListItem> items = new ArrayList<ShoopingListItem>();
		for (String row : fileContentList) {
			String[] rowSplitted = row.split(";");
			try {
				UUID uuid = UUID.fromString(rowSplitted[0]);
				Name shoppingListName = new Name(rowSplitted[1]);
				ShoppingList shoppingList = new ShoppingList(uuid, shoppingListName);
				shoppingLists.add(shoppingList);

			} catch (IllegalNameException e) {
				e.printStackTrace();
			}

		}

		shoppingLists = addItemstoList(fileContentItem, shoppingLists);

		return shoppingLists;
	}

	private ArrayList<ShoppingList> addItemstoList(List<String> fileContentItem,
			ArrayList<ShoppingList> shoppingLists) {
		for (String row : fileContentItem) {
			String[] rowSplitted = row.replaceAll("\r", "").split(";");
			try {
				UUID uuidShoppingList = UUID.fromString(rowSplitted[0]);
				UUID uuidItem = UUID.fromString(rowSplitted[1]);
				Name itemName = new Name(rowSplitted[2]);
				String quantityAndUnit = rowSplitted[3];

				String quanatityUnit;
				List<String> allMatches = HelperClass.getallMatches(quantityAndUnit);
				Float quanatityNumber = Float.parseFloat(allMatches.get(0));
				if (allMatches.size() > 1) {
					quanatityUnit = allMatches.get(1);
				} else {
					quanatityUnit = "";
				}
				Quantity quantity = new Quantity(quanatityNumber, quanatityUnit);
				ItemStatus itemStatus = ItemStatus.fromString(rowSplitted[4]);
				Money money = new Money(Float.parseFloat(rowSplitted[5]));

				ShoopingListItem shoopingListItem = new ShoopingListItem(uuidItem, itemName, quantity, itemStatus,
						money);

				for (ShoppingList shoppingList : shoppingLists) {
					if (shoppingList.getShoppingListUuid().equals(uuidShoppingList)) {
						shoppingList.addItem(shoopingListItem);
					}
				}
			} catch (IllegalNameException | NumberFormatException | IlleaglQuantityException | IllegalUnitExeption |
					 ShoopingListItemAlreadyExistException e) {
				e.printStackTrace();
			}

		}
		return shoppingLists;
	}

	public void save(ShoppingList shoopingList) {
		ShoppingList oldList = this.getShoppingListBy(shoopingList.getShoppingListName());
		StringBuilder sb = new StringBuilder();
		if (oldList == null) {

			sb.append(shoopingList.getShoppingListUuid()).append(";").append(shoopingList.getShoppingListName())
					.append("\n");
			CSVWriter.writeLine(FILE_PATH_LIST, sb.toString());
		} else if (oldList != null) {
			for (ShoopingListItem item : shoopingList.getItemList()) {
				saveItem(item, oldList);
			}
		}

	}

	private void saveItem(ShoopingListItem item, ShoppingList oldList) {
		StringBuilder sb = new StringBuilder();
		String newItemString = mapItemtoString(oldList.getShoppingListUuid(), item);

		for (ShoopingListItem itemOldList : oldList.getItemList()) {
			if (itemOldList.getItemId().equals(item.getItemId())) {
				String oldItemString = mapItemtoString(oldList.getShoppingListUuid(), itemOldList);
				CSVWriter.updateLine(FILE_PATH_ITEM, oldItemString, newItemString);
				return;

			}
		}
		CSVWriter.writeLine(FILE_PATH_ITEM, newItemString);
	}

	public ShoppingList getShoppingListBy(Name name) {
		ArrayList<ShoppingList> lists = getAllShoopingLists();
		for (ShoppingList shoppingList : lists) {
			if (shoppingList.getShoppingListName().equals(name))
				return shoppingList;
		}
		return null;

	}

	private String mapItemtoString(UUID shoppingListUuid, ShoopingListItem item) {
		StringBuilder sb = new StringBuilder();
		sb.append(shoppingListUuid).append(";");
		sb.append(item.getItemId()).append(";");
		sb.append(item.getItemName()).append(";");
		sb.append(item.getQuantity()).append(";");
		sb.append(item.getStatus()).append(";");
		sb.append(item.getMoney());
		return sb.toString();
	}

}
