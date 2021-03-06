package builders;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import exception.InvalidAddressException;
import exception.InvalidAreaCodeException;
import exception.InvalidAverageDeliveryTimeOfMenuException;
import exception.InvalidCountryCodeException;
import exception.InvalidLatitudeMapPositionException;
import exception.InvalidLengthMapPositionException;
import exception.InvalidLocalNumberException;
import exception.InvalidLocalityAddressException;
import exception.InvalidMapPositionException;
import exception.InvalidNumberStreetException;
import exception.InvalidServiceException;
import exception.InvalidStreetAddressException;
import exception.InvalidTelephoneNumberException;
import exception.InvalidTimeZoneException;
import menuExceptions.InvalidEndDateOfferMenuException;
import menuExceptions.InvalidMaximumNumberOfMenusSalesPerDay;
import menuExceptions.InvalidMenuCategoryException;
import menuExceptions.InvalidMenuDeliveryPriceException;
import menuExceptions.InvalidMenuDescriptionException;
import menuExceptions.InvalidMenuNameException;
import menuExceptions.InvalidMinimumNumberOfMenusToBuyException;
import menuExceptions.InvalidMinimumPriceOfMenusToBuyException;
import menuExceptions.InvalidPricesException;
import menuExceptions.InvalidStartDateOfferMenuException;
import model.Category;
import model.Menu;
import model.Money;
import model.Price;
import model.Service;
import model.Symbol;
import model.TimeZone;
import serviceException.InvalidServiceDescriptionException;
import serviceException.InvalidServiceEmailException;
import serviceException.InvalidServiceLogoException;
import serviceException.InvalidServiceNameException;
import serviceException.InvalidServiceWorkingHoursException;
import validation.InvalidFormatTimeZoneException;
import validation.InvalidMenuPriceException;

public class MenuBuilder {

	public MenuBuilder() throws InvalidAddressException, InvalidServiceException, InvalidServiceNameException,
			InvalidServiceLogoException, InvalidServiceDescriptionException, InvalidServiceEmailException,
			InvalidServiceWorkingHoursException, InvalidTelephoneNumberException, InvalidNumberStreetException,
			InvalidStreetAddressException, InvalidLocalityAddressException, InvalidLocalNumberException,
			InvalidAreaCodeException, InvalidCountryCodeException, InvalidTimeZoneException,
			InvalidFormatTimeZoneException, InvalidLengthMapPositionException, InvalidLatitudeMapPositionException,
			InvalidMapPositionException {
		this.service = this.getService();
		this.deliveryTimesMenus = this.getDeliveryTimesMenus();
	}

	private Money moneyArg = new Money(Symbol.ARG);
	private String menuName = "Hamburguesa Caseras";
	private String menuDescription = "MenuDescription12345678910";
	private Category menuCategory = Category.BURGER;
	private Price menuDeliveryPrice = new Price(moneyArg, new Double(15));
	private LocalDateTime startDateOfferMenu = new DateTime().plusDays(7).toLocalDateTime();
	private LocalDateTime endDateOfferMenu = new DateTime().plusDays(14).toLocalDateTime();
	private List<TimeZone> deliveryTimesMenus;
	private Integer averageDeliveryTimeOfMenu = 30;
	private Price menuPrice = new Price(moneyArg, new Double(40));
	private Integer firstMinimumNumberOfMenusToBuy = 10;
	private Price firstminimumPriceOfMenusToBuy = new Price(moneyArg, new Double(30));
	private Integer secondMinimumNumberOfMenusToBuy = 50;
	private Price secondMinimumPriceOfMenusToBuy = new Price(moneyArg, new Double(25));;
	private Integer maximumNumberOfMunusSalesPerDay = 100;
	private Service service;

	public Menu build() throws InvalidMenuNameException, InvalidMenuDescriptionException, InvalidMenuCategoryException,
			InvalidStartDateOfferMenuException, InvalidEndDateOfferMenuException, InvalidMenuDeliveryPriceException,
			InvalidAverageDeliveryTimeOfMenuException, InvalidServiceException,
			InvalidMinimumNumberOfMenusToBuyException, InvalidMinimumPriceOfMenusToBuyException,
			InvalidMaximumNumberOfMenusSalesPerDay, InvalidPricesException, InvalidMenuPriceException {
		return new Menu(menuName, menuDescription, menuCategory, menuDeliveryPrice, startDateOfferMenu,
				endDateOfferMenu, deliveryTimesMenus, averageDeliveryTimeOfMenu, menuPrice,
				firstMinimumNumberOfMenusToBuy, firstminimumPriceOfMenusToBuy, secondMinimumNumberOfMenusToBuy,
				secondMinimumPriceOfMenusToBuy, maximumNumberOfMunusSalesPerDay, service);

	}

	private List<TimeZone> getDeliveryTimesMenus() throws InvalidTimeZoneException, InvalidFormatTimeZoneException {
		List<TimeZone> deliveryTimesMenus = new ArrayList<>();
		deliveryTimesMenus.add(new TimeZone("18:00", "22:00"));
		return deliveryTimesMenus;
	}

	public Service getService() throws InvalidAddressException, InvalidServiceNameException,
			InvalidServiceLogoException, InvalidServiceDescriptionException, InvalidServiceEmailException,
			InvalidServiceException, InvalidServiceWorkingHoursException, InvalidTelephoneNumberException,
			InvalidNumberStreetException, InvalidStreetAddressException, InvalidLocalityAddressException,
			InvalidLocalNumberException, InvalidAreaCodeException, InvalidCountryCodeException,
			InvalidTimeZoneException, InvalidFormatTimeZoneException, InvalidLengthMapPositionException,
			InvalidLatitudeMapPositionException, InvalidMapPositionException {

		return new ServiceBuilder().build();
	}

	public MenuBuilder withMenuName(String menuName) {
		this.menuName = menuName;
		return this;
	}

	public MenuBuilder withMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
		return this;
	}

	public MenuBuilder withMenuCategory(Category menuCategory) {
		this.menuCategory = menuCategory;
		return this;
	}

	public MenuBuilder withMenuDeliveryPrice(Double menuDeliveryPrice) {
		Price price = new Price(moneyArg, new Double(menuDeliveryPrice));
		this.menuDeliveryPrice = price;
		return this;
	}

	public MenuBuilder withStartDateOfferMenu(LocalDateTime dateOfferMenu) {
		this.startDateOfferMenu = dateOfferMenu;
		return this;
	}

	public MenuBuilder withEndDateOfferMenu(LocalDateTime dateOfferMenu) {
		this.endDateOfferMenu = dateOfferMenu;
		return this;
	}

	public MenuBuilder withAverageDeliveryTimeOfMenu(Integer averageDeliveryTimeOfMenu) {
		this.averageDeliveryTimeOfMenu = averageDeliveryTimeOfMenu;
		return this;
	}

	public MenuBuilder withFirstMinimumNumberOfMenusToBuy(Integer firstMinimumNumberOfMenusToBuy) {
		this.firstMinimumNumberOfMenusToBuy = firstMinimumNumberOfMenusToBuy;
		return this;
	}

	public MenuBuilder withFirstMinimumPriceOfMenusToBuy(Double firstminimumPriceOfMenusToBuy) {
		Price price = new Price(moneyArg, firstminimumPriceOfMenusToBuy);
		this.firstminimumPriceOfMenusToBuy = price;
		return this;
	}

	public MenuBuilder withSecondMinimumNumberOfMenusToBuy(Integer secondMinimumNumberOfMenusToBuy) {
		this.secondMinimumNumberOfMenusToBuy = secondMinimumNumberOfMenusToBuy;
		return this;
	}

	public MenuBuilder withSecondMinimumPriceOfMenusToBuy(Double secondMinimumPriceOfMenusToBuy) {
		Price price = new Price(moneyArg, secondMinimumPriceOfMenusToBuy);
		this.secondMinimumPriceOfMenusToBuy = price;
		return this;
	}

	public MenuBuilder withMenuService(Service service) {
		this.service = service;
		return this;
	}

	public MenuBuilder withMenuPrice(Double price) {
		this.menuPrice = new Price(moneyArg, price);
		return this;
	}

	public MenuBuilder withMaximumNumberOfMenusSalesPerDay(Integer maximumNumberOfMenusSalesPerDay) {
		this.maximumNumberOfMunusSalesPerDay = maximumNumberOfMenusSalesPerDay;
		return this;
	}

}
