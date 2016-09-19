package tn.esprit.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Category;
import tn.esprit.entities.Inventory;
import tn.esprit.entities.Item;
import tn.esprit.services.interfaces.StockServiceLocal;
import tn.esprit.services.interfaces.StockServiceRemote;

@Stateless
public class StockService implements StockServiceRemote,StockServiceLocal{

	@PersistenceContext
	private EntityManager entityManager;
	
	public Item createItem(Item item) {
		 entityManager.persist(item);
		return item;
	}

	
	public void saveItem(Item item) {
		entityManager.merge(item);
		
	}

	
	public void removeItem(Item item) {
		int id=item.getId();
		entityManager.remove(findItemById(id));
		
	}

	
	public List<Item> findAllItem() {
		return entityManager.createQuery("select i from Item i",Item.class)
				.getResultList();
	}

	
	public Item findItemByName(String name) {
		Item found = null;
		TypedQuery<Item> query = entityManager.createQuery(
				"select i from Item i where i.name=:x", Item.class);
		query.setParameter("x", name);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no item with name=" + name);
		}
		return found;
	}

	
	public Category createCategory(Category category) {
		entityManager.persist(category);
		return category;
	}

	
	public void saveCategory(Category category) {
		entityManager.merge(category);
		
	}

	
	public void removeCategory(Category category) {
		int id=category.getId();
		entityManager.remove(findCategorieById(id));
		
	}

	
	public List<Category> findAllCategories() {
		return entityManager.createQuery("select c from Category c",Category.class)
				.getResultList();
	}

	
	public Category findCategoryByName(String name) {
		Category found = null;
		TypedQuery<Category> query = entityManager.createQuery(
				"select c from Category c where c.name=:x", Category.class);
		query.setParameter("x", name);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no category with name=" + name);
		}
		return found;
	}

	
	public Boolean createItemToCategorie(Item item, int idcategorie) {
		Boolean b = false;
		try {
			
			Category categoriFound = entityManager.find(Category.class, idcategorie);
			item.setCategory(categoriFound);
			entityManager.merge(item);
			b = true;
		} catch (Exception e) {
		}
		return b;
		
	}

	
	public Item findItemById(int id) {
		
		return  entityManager.find(Item.class, id);
	}

	
	public Category findCategorieById(int id) {
		
		return entityManager.find(Category.class,id);
	}


	@Override
	public List<Inventory> findAllInvetoriy() {
		
		return entityManager.createQuery("select i from inventory i",Inventory.class)
				.getResultList();
	}


	@Override
	public Inventory findInvetoriyById(int id) {
		return entityManager.find(Inventory.class,id);
	}


	@Override
	public Inventory findInvetoriyByName(String name) {
		Inventory found = null;
		TypedQuery<Inventory> query = entityManager.createQuery(
				"select i from inventory i where i.name=:x", Inventory.class);
		query.setParameter("x", name);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no category with name=" + name);
		}
		return found;
	}

	

	

}
