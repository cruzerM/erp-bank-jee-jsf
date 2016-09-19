package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Category;
import tn.esprit.entities.Item;

@Remote
public interface StockServiceRemote {

	Item createItem(Item item);
	void saveItem(Item item);
	void removeItem(Item Item);
	List<Item> findAllItem();
	Item findItemByName(String name);
	Boolean createItemToCategorie(Item item,int idcategorie);
	
	
	Category createCategory(Category category);
	void saveCategory(Category category);
	void removeCategory(Category category);
	List<Category> findAllCategories();
	Category findCategoryByName(String name);
}
