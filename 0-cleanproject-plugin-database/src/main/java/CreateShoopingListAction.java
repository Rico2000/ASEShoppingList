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
			if(shoppingListService.getShoppingListBy(shoppingListName) == null) {
				shoppingListService.createShooppingList(shoppingListName);
			}else{
				throw new ShoppingListAlreadyExistException("ShoppingList: "+ shoppingListName+ "exestiert bereits");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
