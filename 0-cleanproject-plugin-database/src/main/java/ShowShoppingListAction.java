import java.util.ArrayList;
import java.util.Scanner;

public class ShowShoppingListAction implements ActionInterface {

	public ShowShoppingListAction() {

	}

	public void interact() {
		ShoppingListService shoppingListService = new ShoppingListService(new ShoppingListRepository());

		ArrayList<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
		for (ShoppingList list : shoppingLists) {
			CommandLineLogger.getInstance().log(list.toString());
		}
		CommandLineLogger.getInstance().log("Welche Einkaufsliste willst du anschauen?");
		CommandLineLogger.getInstance().log("Gib den Namen dieser Liste ein");
		Scanner scanner = new Scanner(System.in);
		String inputName = scanner.next();
		// scanner.close();

		try {
			Name shoppingListName = new Name(inputName);
			ShoppingList shoppingList = shoppingListService.getShoppingListBy(shoppingListName);
			Money gesamtkostem = shoppingList.getGesamtkosten();
			for (ShoopingListItem item : shoppingList.getItemList()) {
				StringBuilder sb = new StringBuilder();
				sb.append(" Item Name: ");
				sb.append(item.getItemName());
				sb.append(" Item Menge: ");
				sb.append(item.getQuantity());
				sb.append(" Item Status: ");
				sb.append(item.getStatus().getText());
				if (item.getStatus() == ItemStatus.GEKAUFT) {
					sb.append(" Kosten: ");
					sb.append(item.getMoney());
				}
				CommandLineLogger.getInstance().log(sb.toString());
			}
			CommandLineLogger.getInstance().log("Gesamtkosten: "+  gesamtkostem+ " �");
		} catch (IllegalNameException e) {
			e.printStackTrace();
		} catch (ShoppingListNotFoundException e) {
			e.printStackTrace();
		} catch (IlleaglQuantityException e) {
			e.printStackTrace();
		}

	}

}
