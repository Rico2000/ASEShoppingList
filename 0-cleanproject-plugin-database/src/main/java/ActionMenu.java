import java.util.HashMap;
import java.util.Scanner;


public class ActionMenu implements ActionInterface {
	HashMap<Integer, ActionInterface> actionMenu = new HashMap<Integer, ActionInterface>();

	public ActionMenu() {
		actionMenu.put(1, new CreateShoopingListAction());
		actionMenu.put(2, new ShowShoppingListAction());
		actionMenu.put(3, new AddItemToListAction());
		actionMenu.put(4, new CheckItemAction());

	}

	public void interact() {
		while (true) {
			CommandLineLogger.getInstance().log("1 Für Anlegen einer neuen Einkaufliste \n"
					+ "2 Zum Anschauen einer ShoppingListe \n"
					+ "3 Zum Hinzufügen eines Items zu einer Liste \n"
					+ "4 Zum Abhaken eines Items einer EinkaufsListe \n");
			Scanner scanner = new Scanner(System.in);
			String inputText = scanner.next();
			// scanner.close();
			try {
				int inputNumber = Integer.parseInt(inputText);
				actionMenu.get(inputNumber).interact();
			} catch (NumberFormatException ex) {
				CommandLineLogger.getInstance().log("Gib eine Zahl ein");
			}

		}

	}

}
