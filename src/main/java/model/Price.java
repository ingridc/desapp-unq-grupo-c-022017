package model;

public class Price {
	private Money money;
	private Double value;
	
	public Price(Money money, Double value){
		this.setMoney(money);
		this.setValue(value);
	}
	
	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}