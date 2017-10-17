package repositories;

import java.util.List;

import model.Menu;

public class MenuRepository extends HibernateGenericDAO<Menu> implements GenericRepository<Menu> {

	private static final long serialVersionUID = -8543996946304099004L;

	@Override
	protected Class<Menu> getDomainClass() {
		return Menu.class;
	}

	public List<Menu> findMenuForName(String name) {
		List<Menu> find = (List<Menu>) this.getHibernateTemplate()
				.find("from " + this.persistentClass.getName() + " where menuName ='" + name + "'");
		return find;
	}
}