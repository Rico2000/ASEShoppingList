public class Money {
	private String unit;
	private float number ;
	public Money(float number) throws IlleaglQuantityException {
		validateMoney(number);
		this.number = number;
	}

	private void validateMoney(float number) throws IlleaglQuantityException {
		if (number < 0) {
			throw new IlleaglQuantityException("Quantity: " + number + " must be bigger than 0");
		}
	}

	@Override
	public String toString() {
		return number+ "";
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
		Money other = (Money) obj;
		if (Float.floatToIntBits(number) != Float.floatToIntBits(other.number))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	public Money add(Money money) throws IlleaglQuantityException {
		return new Money(this.number + money.number);
	}


}
