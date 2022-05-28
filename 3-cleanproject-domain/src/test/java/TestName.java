

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestName {

	@Test
	public void testNameIsToShort() {
		assertThrows(IllegalNameException.class, () -> new Name("de"));
		assertThrows(IllegalNameException.class, () -> new Name("e"));
		assertThrows(IllegalNameException.class, () -> new Name(""));
	}
	@Test
	public void testNameContainsIllegalCharacters() {
		assertThrows(IllegalNameException.class, () -> new Name("d12"));
		assertThrows(IllegalNameException.class, () -> new Name("  "));
		assertThrows(IllegalNameException.class, () -> new Name("Ric0"));
	}
	@Test
	public void testNamePositiveCases(){
		assertDoesNotThrow(() -> new Name("Rico"));
		assertDoesNotThrow(() -> new Name("Test"));
		assertDoesNotThrow(() -> new Name("Tim"));
	}
	@Test
	public void testNameEquals() throws IllegalNameException {
		assertEquals(new Name("Rico"), new Name("Rico"));
	}

}
