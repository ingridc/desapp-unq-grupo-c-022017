/**
 *
 */
package rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import builders.MenuBuilder;
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
import exception.InvalidRegisterException;
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
import model.Address;
import model.Category;
import model.Locality;
import model.MapPosition;
import model.Menu;
import model.Provider;
import model.Service;
import model.Telephone;
import model.TimeZone;
import model.WorkingTime;
import serviceException.InvalidServiceDescriptionException;
import serviceException.InvalidServiceEmailException;
import serviceException.InvalidServiceLogoException;
import serviceException.InvalidServiceNameException;
import serviceException.InvalidServiceWorkingHoursException;
import services.MenuService;
import services.ProviderService;
import services.UserService;
import userExceptions.InvalidCuitException;
import userExceptions.InvalidEmailAddressException;
import userExceptions.InvalidFirstNameException;
import userExceptions.InvalidLastNameException;
import validation.InvalidFormatTimeZoneException;
import validation.InvalidMenuPriceException;

@Path("/")
public class DataFakeRest {

	public static final int NUMBER_OF_POST = 1;
	private MenuService menuService;
	private ProviderService providerService;
	private UserService userService;

	@GET
	@Path("/load")
	@Produces("application/json")
	public Response load() throws NumberFormatException, InvalidLocalNumberException, InvalidAreaCodeException,
			InvalidCountryCodeException, InvalidAddressException, InvalidTelephoneNumberException,
			InvalidMapPositionException, InvalidLengthMapPositionException, InvalidLatitudeMapPositionException,
			InvalidNumberStreetException, InvalidStreetAddressException, InvalidLocalityAddressException,
			InvalidCuitException, InvalidFirstNameException, InvalidLastNameException, InvalidEmailAddressException,
			InvalidServiceException, InvalidServiceNameException, InvalidServiceLogoException,
			InvalidServiceDescriptionException, InvalidServiceEmailException, InvalidServiceWorkingHoursException,
			InvalidAverageDeliveryTimeOfMenuException, InvalidMenuNameException, InvalidMenuDescriptionException,
			InvalidMenuCategoryException, InvalidStartDateOfferMenuException, InvalidMenuDeliveryPriceException,
			InvalidMinimumNumberOfMenusToBuyException, InvalidMinimumPriceOfMenusToBuyException,
			InvalidMaximumNumberOfMenusSalesPerDay, InvalidPricesException, InvalidMenuPriceException,
			InvalidEndDateOfferMenuException, InvalidTimeZoneException, InvalidFormatTimeZoneException,
			InvalidRegisterException {

		userService.newUser("password", "Maximiliano", "Diaz", "12345678", "diazmaxi@gmail.com", "54", "011",
				"43511464", "AVELLANEDA", "BOGOTA", "4040", "1", "1", "1");
		MapPosition mapPosition = new MapPosition(new Double(1), new Double(1));

		Telephone telephone = new Telephone("54", "011", "43511111");

		Address address = new Address(Locality.ALMIRANTEBROWN, "street", "numberStreet", "floor", mapPosition);
		Provider provider = new Provider("87654321", "Rosali", "Zaracho", "zaracho.rosali@gmail.com", telephone,
				address, "password");

		List<WorkingTime> serviceWorkingHours = new ArrayList<>();
		List<TimeZone> workingHours = new ArrayList<>();
		TimeZone lateShift = new TimeZone("17:00", "22:30");
		workingHours.add(lateShift);
		serviceWorkingHours.add(new WorkingTime(1, workingHours));
		serviceWorkingHours.add(new WorkingTime(2, workingHours));
		serviceWorkingHours.add(new WorkingTime(3, workingHours));
		serviceWorkingHours.add(new WorkingTime(4, workingHours));
		serviceWorkingHours.add(new WorkingTime(5, workingHours));
		serviceWorkingHours.add(new WorkingTime(6, workingHours));
		serviceWorkingHours.add(new WorkingTime(7, workingHours));

		Set<Locality> lista = new HashSet<Locality>();
		lista.add(Locality.ALMIRANTEBROWN);
		lista.add(Locality.BERAZATEGUI);

		Set<Locality> lista2 = new HashSet<Locality>();
		lista2.add(Locality.AVELLANEDA);

		Service service = new Service("Mi Negocio", "Logo",
				new Address(Locality.BERAZATEGUI, "CALLE 14", "1000", "1", new MapPosition(1.0, 1.0)), "Mi Descripcion",
				"www.ventas.com.ar", "mail@ventas.com.ar", new Telephone("54", "011", "44444444"), serviceWorkingHours,
				lista);
		service.setIdProvider(2);
		Service service2 = new Service("Mi Negocio 2", "Logo",
				new Address(Locality.AVELLANEDA, "CALLE 14", "1000", "1", new MapPosition(1.0, 1.0)), "Mi Descripcion",
				"www.ventas.com.ar", "mail@ventas.com.ar", new Telephone("54", "011", "44444444"), serviceWorkingHours,
				lista2);
		service2.setIdProvider(2);

		provider.addNewService(service);
		provider.addNewService(service2);

		providerService.save(provider);

		Menu menu1 = new MenuBuilder().withMenuService(service).build();
		Menu menu2 = new MenuBuilder().withMenuService(service).withMenuName("Cerveza artesanal")
				.withMenuCategory(Category.BEER).build();
		Menu menu3 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.PIZZA).build();
		Menu menu4 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.VEGAN).build();
		Menu menu5 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.GREEN).build();
		Menu menu6 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.EMPANADAS).build();
		Menu menu7 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.SUSHI).build();
		Menu menu8 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.PIZZA).build();
		Menu menu9 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.EMPANADAS).build();
		Menu menu10 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.GREEN).build();
		Menu menu11 = new MenuBuilder().withMenuService(service2).withMenuCategory(Category.BURGER).build();

		menuService.save(menu1);
		menuService.save(menu2);
		menuService.save(menu3);
		menuService.save(menu4);
		menuService.save(menu5);
		menuService.save(menu6);
		menuService.save(menu7);
		menuService.save(menu8);
		menuService.save(menu9);
		menuService.save(menu10);
		menuService.save(menu11);

		return Response.ok().build();
	}

	public void setMenuService(final MenuService menuService) {
		this.menuService = menuService;
	}

	public void setProviderService(final ProviderService providerService) {
		this.providerService = providerService;
	}

	public void setUserService(final UserService userService) {
		this.userService = userService;
	}
}
