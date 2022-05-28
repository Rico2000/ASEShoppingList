import java.util.List;
import java.util.Scanner;

public class CheckItemAction implements ActionInterface {

	@Override
	public void interact() {
		ShoppingListService shoppingListService = new ShoppingListService(new ShoppingListRepository());
		Scanner scanner = new Scanner(System.in);
		List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
		for (ShoppingList list : shoppingLists) {
			CommandLineLogger.getInstance().log(list.toString());
		}
		CommandLineLogger.getInstance().log("Welche Einkaufliste möchtest du auswählen");

		String inputName = scanner.next();

		try {
			Name shoppingListName = new Name(inputName);
			ShoppingList shoppingList = shoppingListService.getShoppingListBy(shoppingListName);
			for (ShoopingListItem item : shoppingList.getItemList()) {
				CommandLineLogger.getInstance().log(item.toString());
			}
			CommandLineLogger.getInstance().log("Welches Item möchtest du abhaken");
			String inputNameItem = scanner.next();
			Name itemName = new Name(inputNameItem);
			CommandLineLogger.getInstance().log("Wie viel hast dies in Euro gekostet");
			String inputAmountMoney = scanner.next();
			Money money = new Money(Float.parseFloat(inputAmountMoney));
			shoppingListService.checkItemFromList(shoppingList.getShoppingListName(), itemName, money);

		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (ShoppingListNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IlleaglQuantityException e) {
			e.printStackTrace();
		}

	}

}
