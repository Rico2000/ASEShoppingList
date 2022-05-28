import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			inputQuantityItem = inputQuantityItem.replaceAll("\\s+","");
			List<String> matches = new ArrayList<String>();
			Matcher m = Pattern.compile("([\\d.]+)|([^\\d.]+)").matcher(inputQuantityItem);
			while (m.find()) {
				matches.add(m.group());
			}

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
