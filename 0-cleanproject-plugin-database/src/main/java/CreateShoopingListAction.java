import java.util.Scanner;

public class CreateShoopingListAction implements ActionInterface {
	public CreateShoopingListAction() {
	}

	public void interact() {
		Scanner scanner = new Scanner(System.in);
		CommandLineLogger.getInstance().log("Wie soll die Einkaufliste hei�en?");
		String input = scanner.next();
		ShoppingListService shoppingListService;
		try {
			Name shoppingListName = new Name(input);
			shoppingListService = new ShoppingListService(new ShoppingListRepository());

			shoppingListService.createShooppingList(shoppingListName);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
