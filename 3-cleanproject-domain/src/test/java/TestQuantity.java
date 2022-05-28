
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class TestQuantity {

	@Test
	public void testNegativeAndZeroValuesThrowException() {
		assertThrows(IlleaglQuantityException.class, () -> new Quantity(-45));
		assertThrows(IlleaglQuantityException.class, () -> new Quantity(0));
	}

	@Test
	public void testToLongUnit() {
		assertThrows(IllegalUnitExeption.class, () -> new Quantity(45, "wwwwwwwwwwwwwwwwwwwww"));
		assertThrows(IllegalUnitExeption.class, () -> new Quantity(45, "weqweqweqweqewqeqweqwe"));
	}

	@Test
	public void testUnitContainsIllegalCharacters() {
		assertThrows(IllegalUnitExeption.class, () -> new Quantity(45, "Kilo1"));
		assertThrows(IllegalUnitExeption.class, () -> new Quantity(45, "$"));
	}

	@Test
	public void testPositiveCases() {
		assertDoesNotThrow(() -> new Quantity(Integer.MAX_VALUE));
		assertDoesNotThrow(() -> new Quantity(Float.MAX_VALUE, "KILO"));
		assertDoesNotThrow(() -> new Quantity(Float.MAX_VALUE, "KG"));

	}
	@Test
	public void testQuantityEquals() throws IlleaglQuantityException, IllegalUnitExeption { 
	assertEquals(new Quantity(4, "KILO"), new Quantity(4, "KILO"));
	}
}
