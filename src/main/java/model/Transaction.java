package model;

public class Transaction extends Entity {

	private static final long serialVersionUID = 1L;
	private TypeTransaction typeTransaction;
	private Double value;
	private TypeStatusTransaction statusTransaction = TypeStatusTransaction.PENDING;

	public Transaction(TypeTransaction typeTransaction, Double value) {

		this.typeTransaction = typeTransaction;
		this.setValue(value);
	}

	public Transaction() {

	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getValueForSum() {
		if (this.getTypeTransaction().equals(TypeTransaction.CREDIT)) {
			return getValue();
		}
		return -getValue();
	}

	public TypeTransaction getTypeTransaction() {
		return typeTransaction;
	}

	public TypeStatusTransaction getTypeStatusTransaction() {
		return statusTransaction;
	}

	public Double getValue() {
		return value;
	}

	public void setFinish() {
		this.statusTransaction = TypeStatusTransaction.FINISH;
	}

	public Boolean isNotRejected() {

		return this.statusTransaction != TypeStatusTransaction.REJECTED;
	}

	public void setRejected() {
		this.statusTransaction = TypeStatusTransaction.REJECTED;

	}

}
