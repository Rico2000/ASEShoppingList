import java.util.UUID;

public class ShoopingListItem {

	private UUID itemId;
	private Name itemName;
	private Quantity quantity;
	private ItemStatus status;
	private Money money;

	public ShoopingListItem(Name itemName, Quantity itemQuantity) {
		this.itemId = UUID.randomUUID();
		this.itemName = itemName;
		this.quantity = itemQuantity;
		this.status = ItemStatus.OFFEN;
		try {
			this.money = new Money(0);
		} catch (IlleaglQuantityException e) {
			e.printStackTrace();
		}

	}

	public ShoopingListItem(UUID uuid, Name itemName, Quantity itemQuantity, ItemStatus itemStatus, Money money) {
		this.itemId = uuid;
		this.itemName = itemName;
		this.quantity = itemQuantity;
		this.status = itemStatus;
		this.money = money;

	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public void setItemId(UUID itemId) {
		this.itemId = itemId;
	}

	public void setItemName(Name itemName) {
		this.itemName = itemName;
	}

	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}

	public UUID getItemId() {
		return itemId;
	}

	public Name getItemName() {
		return itemName;
	}

	public Quantity getQuantity() {
		return quantity;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return itemId + " " + itemName + " " + quantity + " " + status;
	}

}
