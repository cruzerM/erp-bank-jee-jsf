package tn.esprit.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Category;
import tn.esprit.entities.Inventory;
import tn.esprit.entities.Item;
import tn.esprit.services.interfaces.StockServiceLocal;

@ManagedBean
@SessionScoped
public class StockBean {
	// models
	private Item item = new Item();
	private Item itemSelected = new Item();
	private List<Item> items = new ArrayList<Item>();
	private Category categorie = new Category();
	private Category categorySelected = new Category();
	private List<Category> categories = new ArrayList<Category>();
	private Boolean displayForm = false;
	private Inventory inventory = new Inventory();
	private List<Inventory> inventories = new ArrayList<Inventory>();
	private int categoryId;

	// injection of the proxy
	@EJB
	private StockServiceLocal ssl;

	// methods
	public String doAddItemPage() {
		return "/addItem?faces-redirect=true";
	}

	public String doAddItem() {
		Item s = ssl.findItemByName(item.getName());

		if (s != null) {

			s.setQuantity(item.getQuantity()+s.getQuantity());
			ssl.saveItem(s);
			System.out.println("heelllooo item : " + item.getId() + " quantity"
					+ item.getQuantity());
			System.out.println("heelllooo s : " + s.getId() + " quantity"
					+ s.getQuantity());
		} else {

			ssl.createItemToCategorie(item, categoryId);

		}
		return "/listItem?faces-redirect=true";
	}

	public String doDeleteItem() {
		ssl.removeItem(itemSelected);
		return "/listItem?faces-redirect=true";
	}

	public String doUpdateItem() {
		ssl.saveItem(itemSelected);

		return "/listItem?faces-redirect=true";
	}

	public String doUpdate() {

		return "/updateItem?faces-redirect=true";
	}

	public String doAddCategoryPage() {
		return "/addCategorie?faces-redirect=true";
	}

	public String doCreateCategorie() {
		ssl.createCategory(categorie);
		return "/listCategories?faces-redirect=true";
	}

	public String doDeleteCategory() {
		ssl.removeCategory(categorySelected);
		return "/listCategories?faces-redirect=true";
	}

	public String doUpdateCategoryPage() {

		return "/updateCategory?faces-redirect=true";
	}

	public String doUpdateCategory() {
		ssl.saveCategory(categorySelected);
		return "";
	}

	public void dislay() {
		setDisplayForm(true);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Category getCategorie() {
		return categorie;
	}

	public void setCategorie(Category categorie) {
		this.categorie = categorie;
	}

	public Item getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(Item itemSelected) {
		this.itemSelected = itemSelected;
	}

	public List<Item> getItems() {
		items = ssl.findAllItem();
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Boolean getDisplayForm() {
		return displayForm;
	}

	public void setDisplayForm(Boolean displayForm) {
		this.displayForm = displayForm;
	}

	public List<Category> getCategories() {
		categories = ssl.findAllCategories();
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Inventory> getInventories() {
		// inventories = ssl.findAllInvetoriy();
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategorySelected() {
		return categorySelected;
	}

	public void setCategorySelected(Category categorySelected) {
		this.categorySelected = categorySelected;
	}

}
