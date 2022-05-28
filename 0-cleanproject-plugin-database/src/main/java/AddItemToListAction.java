import java.util.List;
import java.util.Scanner;

public class AddItemToListAction implements ActionInterface {
	public AddItemToListAction() {
	}

	public void interact() {
		ShoppingListService shoppingListService = new ShoppingListService(new ShoppingListRepository());
		Scanner scanner = new Scanner(System.in);
		CommandLineLogger.getInstance().log("Zu Welcher Liste soll etwas hinzugef�gt werden?");
		String inputNameShoppingList = scanner.nextLine();
		CommandLineLogger.getInstance().log("Weches Item soll hinzugef�gt werden?");
		String inputNameItem = scanner.nextLine();
		CommandLineLogger.getInstance().log("Wie viel soll von diesem Item hinzugef�gt werden (Anzahl und dann m�gliche Einheit)");
		String inputQuantityItem = scanner.nextLine();

		try {
			Name shoppingListName = new Name(inputNameShoppingList);
			Name itemName = new Name(inputNameItem);
			Quantity itemQuantity;
			List<String> matches = HelperFunctions.splidQuantityString(inputQuantityItem);
			try {
				if (matches.size() == 2) {
					itemQuantity = new Quantity(Float.parseFloat(matches.get(0)), matches.get(1));
				} else {
					itemQuantity = new Quantity(Float.parseFloat(matches.get(0)));
				}
				shoppingListService.addItemToList(shoppingListName, itemName, itemQuantity);
			}
			catch(Exception e) {
				e.printStackTrace();
			}

		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

}
