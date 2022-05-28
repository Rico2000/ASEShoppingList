import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMoney {

	@Test
	public void testNumberIsNegative() {
		assertThrows(IlleaglQuantityException.class, () -> new Money(-1));
		assertThrows(IlleaglQuantityException.class, () -> new Money(-2));
	}
	@Test
	public void testAddMoney() throws IlleaglQuantityException {
		Money money = new Money(4);
		Money addedMoney = money.add(new Money(5.5F));
		assertEquals(new  Money(9.5F), addedMoney);
		Money money2 = new Money(0);
		Money addedMoney2 = money2.add(new Money(1.4F));
		assertEquals(new  Money(1.4F), addedMoney2);
	}

}
