package tn.esprit.services.interfaces;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Category;
import tn.esprit.entities.Inventory;
import tn.esprit.entities.Item;




@Local
public interface StockServiceLocal {
	
	Item createItem(Item item);
	void saveItem(Item item);
	void removeItem(Item Item);
	List<Item> findAllItem();
	Item findItemByName(String name);
	Item findItemById(int id);
	Boolean createItemToCategorie(Item item,int idcategorie);
	
	
	Category createCategory(Category category);
	void saveCategory(Category category);
	void removeCategory(Category category);
	List<Category> findAllCategories();
	Category findCategorieById(int id);
	Category findCategoryByName(String name);
	
	List<Inventory> findAllInvetoriy();
	Inventory findInvetoriyById(int id);
	Inventory findInvetoriyByName(String name);
}
