public final class Quantity {
	private final float number;
	private final String unit;

	public Quantity(float number, String unit) throws IlleaglQuantityException, IllegalUnitExeption {
		validateQuantityNumber(number);
		validateQuantityUnit(unit);
		this.number = number;
		this.unit = unit;
	}

	public Quantity(float number) throws IlleaglQuantityException {
		validateQuantityNumber(number);
		this.number = number;
		this.unit = "";
	}

	private void validateQuantityNumber(float number) throws IlleaglQuantityException {
		if (number <= 0) {
			throw new IlleaglQuantityException("Quantity: " + number + " must be bigger than 0");
		}
		
	}
	private void validateQuantityUnit(String unit) throws IllegalUnitExeption {
		if(unit.length() > 20) {
			throw new IllegalUnitExeption("Unit: " + unit + " has to many Characters");
		}
		if(!unit.chars().allMatch(Character::isLetter)) {
			throw new IllegalUnitExeption("Unit: " + unit + " contains illegal Characters");
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(number);
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quantity other = (Quantity) obj;
		if (Float.floatToIntBits(number) != Float.floatToIntBits(other.number))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return number + unit;
	}

}
