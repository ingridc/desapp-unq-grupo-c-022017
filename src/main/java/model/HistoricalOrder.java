package model;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public class HistoricalOrder extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Score score;
	private LocalDateTime deliveryTime;
	private TypeStatusOrder typeStatusOrder;
	private Menu menu;
	private Double price;
	private DateTime dateOfOrder;
	private TypeOfDelivery typeOfDelivery;
	private String delivery;

	public HistoricalOrder(Score score, LocalDateTime localDateTime, TypeStatusOrder typeStatusOrder, Menu menu,
			Double price, DateTime dateOfOrder, TypeOfDelivery typeOfDelivery) {
		this.score = score;
		this.deliveryTime = localDateTime;
		this.typeStatusOrder = typeStatusOrder;
		this.menu = menu;
		this.price = price;
		this.dateOfOrder = dateOfOrder;
		this.setTypeOfDelivery(typeOfDelivery);
	}

	public Score getScore() {
		return score;
	}

	public LocalDateTime getDeliveryTime() {
		return deliveryTime;
	}

	public TypeStatusOrder getTypeStatusOrder() {
		return typeStatusOrder;
	}

	public Menu getMenu() {
		return menu;
	}

	public Double getPrice() {
		return price;
	}

	public DateTime getDateOfOrder() {
		return dateOfOrder;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public void setTypeOfDelivery(TypeOfDelivery typeOfDelivery) {
		this.typeOfDelivery = typeOfDelivery;

		switch (this.typeOfDelivery) {
		case HOMEDELIVERY:
			this.setDelivery("Sí");
			break;
		case LOCALDELIVERY:
			this.setDelivery("No");
			break;
		}
	}
}
