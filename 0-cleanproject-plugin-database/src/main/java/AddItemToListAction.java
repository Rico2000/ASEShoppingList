import java.util.List;
import java.util.Scanner;

public class AddItemToListAction implements ActionInterface {
	public AddItemToListAction() {
	}

	public void interact() {
		ShoppingListService shoppingListService = new ShoppingListService(new ShoppingListRepository());
		Scanner scanner = new Scanner(System.in, "UTF-8");
		CommandLineLogger.getInstance().log("Zu Welcher Liste soll etwas hinzugefügt werden?");
		String inputNameShoppingList = scanner.nextLine();
		CommandLineLogger.getInstance().log("Weches Item soll hinzugefügt werden?");
		String inputNameItem = scanner.nextLine();
		CommandLineLogger.getInstance().log("Wie viel soll von diesem Item hinzugefügt werden (Anzahl und dann mögliche Einheit)");
		String inputQuantityItem = scanner.nextLine();

		try {
			Name shoppingListName = new Name(inputNameShoppingList);
			Name itemName = new Name(inputNameItem);
			List<String> matches = HelperClass.splitQuantityAndUnitFromString(inputQuantityItem);
			Quantity itemQuantity = getQuantityFromList(matches);
			shoppingListService.addItemToList(shoppingListName, itemName, itemQuantity);
		} catch (NumberFormatException | ShoppingListNotFoundException | ShoopingListItemAlreadyExistException |
				 IllegalNameException e) {
			e.printStackTrace();
		}

	}

	private Quantity getQuantityFromList(List<String> matches) {
		Quantity itemQuantity = null;
		try {
			if (matches.size() == 2) {
				itemQuantity = new Quantity(Float.parseFloat(matches.get(0)), matches.get(1));
			} else {
				itemQuantity = new Quantity(Float.parseFloat(matches.get(0)));
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return itemQuantity;
	}


}
