import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestShoppingListService {
	static ShoppingListRepositiryInterface mock;
	static ShoppingListService service;

	@BeforeEach
	void setUpBeforeClass(){
		mock = new MockShoppingListRepository();
		service = new ShoppingListService(mock);
	}

	@Test
	public void testCreateShooppingList() throws IllegalNameException{
		service.createShooppingList(new Name("Test"));
		assertEquals(1, mock.getAllShoopingLists().size());

	}

	@Test
	public void testGetShoppingListByName() throws IllegalNameException, ShoppingListNotFoundException {
		ShoppingList shoppingList = new ShoppingList(new Name("Test"));
		mock.save(shoppingList);
		assertEquals(shoppingList, service.getShoppingListBy((new Name("Test"))));

	}

	@Test
	public void testGetShoppingListByNameNotFound() throws IllegalNameException, ShoppingListNotFoundException {
		assertThrows(ShoppingListNotFoundException.class, () -> service.getShoppingListBy(new Name("sdad")));

	}

	@Test
	public void testAddItemToList()
			throws IllegalNameException, ShoppingListNotFoundException, IlleaglQuantityException, ShoopingListItemAlreadyExistException {
		mock.save(new ShoppingList(new Name("Test")));
		service.addItemToList(new Name("Test"), new Name("Eier"), new Quantity(10));
		System.out.println(mock.getAllShoopingLists().size());
		assertEquals(mock.getAllShoopingLists().get(0).getItemList().size(), 1);
		assertEquals(mock.getAllShoopingLists().get(0).getItemList().get(0).getItemName(), new Name("Eier"));
		assertEquals(mock.getAllShoopingLists().get(0).getItemList().get(0).getQuantity(), new Quantity(10));

	}

	@Test
	public void testCheckItemFromList()
			throws IllegalNameException, ShoppingListNotFoundException, IlleaglQuantityException, ShoopingListItemAlreadyExistException {
		ShoppingListRepositiryInterface mock = new MockShoppingListRepository();
		ShoppingListService service = new ShoppingListService(mock);
		mock.save(new ShoppingList(new Name("Test")));
		service.addItemToList(new Name("Test"), new Name("Eier"), new Quantity(10));
		service.checkItemFromList(new Name("Test"), new Name("Eier"), new Money(10.0F));
		assertEquals(ItemStatus.GEKAUFT, mock.getShoppingListBy(new Name("Test")).getItemList().get(0).getStatus());
	}

}
