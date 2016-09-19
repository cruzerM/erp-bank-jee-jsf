package tn.esprit.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

import tn.esprit.entities.Invoice;
import tn.esprit.entities.Offer;
import tn.esprit.entities.OfferResponse;
import tn.esprit.entities.Provider;
import tn.esprit.enumerators.OfferResponseState;
import tn.esprit.services.interfaces.IInvoiceRestServiceLocal;
import tn.esprit.services.interfaces.IServiceOffreLocal;

@ManagedBean
@SessionScoped
public class OfferBean {

	private Offer offer = new Offer();
	private OfferResponse offerResponse = new OfferResponse();
	private Offer selectedOffer = new Offer();
	private Provider provider = new Provider();
	private OfferResponse selectedofferResponse = new OfferResponse();
	
	private Boolean displayUpdateDeleteForm = false;
	private Boolean displayAcceptRefuseForm = false;
	private Boolean displayAddResponseForm = false;
	private Boolean displayOffreSpecificationForm = false;
	private Boolean displayProviderSpecificationForm = false;
	
	private Properties props = System.getProperties();
	private String uploadPath = props.getProperty("jboss.home.dir")+"\\welcome-content\\";
	private String url = "http://localhost:18080/";
	private UploadedFile file=null;
	
	private List<Offer> offers = new ArrayList<Offer>();
	private List<OfferResponse> offerResponses = new ArrayList<OfferResponse>();
	private List<String> providerNames = new ArrayList<String>();
	private List<String> products = new ArrayList <String>() {{ add("coffee table"); add("normal table"); add("high chair"); 
	add("normal chair"); add("desktop computer"); add("pc"); add("phone"); add("printer"); add("counter");add("fax"); add("pen"); add("paper");}};
	private String providerName;
	private String product;
	private String convertProduct;
	private Integer quentity;
	private HashMap<String, Integer> items = new HashMap<>();
	private String selectedOffreProduct;
	private String selectedQuentity;
	private HashMap<String, Integer> selectedOfferitems = new HashMap<>();
	
	
	
	@EJB
	private IServiceOffreLocal serviceOffreLocal;
	
	@EJB
	IInvoiceRestServiceLocal service;
	
	//
	public String goToOfferResponses(){
		return "listeOfferResponses?faces-redirect=true";
	}
	public String fillItemsHashMap(){
		items.put(product, quentity);
		return "";
	}
	public void fillSelectedItemsHashMap(){
		char pos;
		selectedQuentity="";
		selectedOffreProduct="";
		selectedOfferitems.clear();
		for(Integer i=0;i<selectedOffer.getItems().length();i++)
		{
			pos=selectedOffer.getItems().charAt(i);
			if(pos!='0'&&pos!='1'&&pos!='2'&&pos!='3'&&pos!='4'&&pos!='5'&&pos!='6'&&pos!='7'&&pos!='8'&&pos!='9'&&pos!=';'&&pos!=',')
			{
			selectedOffreProduct=selectedOffreProduct+pos;
			}
			if(pos=='0'||pos=='1'||pos=='2'||pos=='3'||pos=='4'||pos=='5'||pos=='6'||pos=='7'||pos=='8'||pos=='9'&&pos!=';'&&pos!=',')
			{
				selectedQuentity=selectedQuentity+pos;
			}
			if(pos==';')
			{
				selectedOfferitems.put(selectedOffreProduct, Integer.parseInt(selectedQuentity));
				System.out.println("Map N----->"+selectedOfferitems.size());
				System.out.println("Product----->"+selectedOffreProduct);
				System.out.println("Quentity----->"+selectedQuentity);
				System.out.println("<----->");
				selectedQuentity="";
				selectedOffreProduct="";
			}
		}
	}
	//Display forms
	public void dislayUpdateDeleteForm() {
		if(displayUpdateDeleteForm){
			displayUpdateDeleteForm = false;	
		}else{
		displayUpdateDeleteForm = true;
		displayAddResponseForm=false;
		displayOffreSpecificationForm = false;
		displayAcceptRefuseForm = false;
		displayProviderSpecificationForm = false;
		}
	}
	
	public void displayAddResponseForm(){
		if(displayAddResponseForm){
			displayAddResponseForm=false;	
		}else{
		displayAddResponseForm=true;
		displayUpdateDeleteForm = false;
		displayProviderSpecificationForm = false;
		displayOffreSpecificationForm = false;
		displayAcceptRefuseForm = false;
		}
	}
	public void displayOffreSpecificationForm(){
		
		if(displayOffreSpecificationForm){
			displayOffreSpecificationForm = false;
		}else{
			displayOffreSpecificationForm = true;
			displayUpdateDeleteForm = false;
			displayAddResponseForm=false;
			displayProviderSpecificationForm = false;
			displayAcceptRefuseForm = false;
		}
	}
	public void displayProviderSpecificationForm(){
		if(displayProviderSpecificationForm){
			displayProviderSpecificationForm = false;
		}else{
			displayProviderSpecificationForm = true;
			displayOffreSpecificationForm = false;
			displayUpdateDeleteForm = false;
			displayAddResponseForm=false;
			displayAcceptRefuseForm = false;
		}
	}
	public void displayAcceptRefuseForm(){
		if(displayAcceptRefuseForm){
			displayAcceptRefuseForm = false;
		}else{
			displayAcceptRefuseForm = true;
			displayProviderSpecificationForm = false;
			displayOffreSpecificationForm = false;
			displayUpdateDeleteForm = false;
			displayAddResponseForm=false;
		}
	}
	//Services
	public String doAddOffer(){
		String item="";
		String key="";
		Integer value=null;
		Iterator<String> it = items.keySet().iterator();
		while(it.hasNext()){
			key=(String)it.next();
			value=(Integer)items.get(key);
			item=item+key+","+value+";";
		}
		offer.setItems(item);
		offer.setState(true);
		if(upload()){
			offer.setSpecifications(url+getFile().getFileName());
			serviceOffreLocal.addOffer(offer);
			offer=new Offer();
			items=new HashMap<>();
			return "listOffers?faces-redirect=true";
		}else{
			return "page_404";
		}
		
	}
	public String doUpdateOffer(){
		fillSelectedItemsHashMap();
		if(upload()){
			selectedOffer.setSpecifications(url+getFile().getFileName());
			serviceOffreLocal.updateOffer(selectedOffer);
			displayUpdateDeleteForm = false;
			return "";
		}else{
			return "";
		}
	}
	
	public String doDeleteOffer(){
		serviceOffreLocal.deleteOffer(selectedOffer);
		displayUpdateDeleteForm = false;
		return "";
	}
	
	public String doAddOfferResponse(){
		fillSelectedItemsHashMap();
		if(upload()){
		provider =  serviceOffreLocal.findProviderByName(providerName);
		serviceOffreLocal.addOfferResponse(selectedOffer,provider,new Date(), offerResponse.getAmount(),url+getFile().getFileName(), OfferResponseState.Hold,null);
		return "";
		}else{
			return "";
		}
	}
	public String doAcceptOfferResponse(){
		fillSelectedItemsHashMap();
		serviceOffreLocal.updateOfferResponse(selectedofferResponse.getOffer(), selectedofferResponse.getProvider(), selectedofferResponse.getPostedDate(), selectedofferResponse.getAmount(), selectedofferResponse.getSpecifications(), OfferResponseState.Accepted, selectedofferResponse.getNotes());
		Invoice invoice = new Invoice(selectedofferResponse.getProvider().getName(),selectedofferResponse.getOffer().getName(), selectedofferResponse.getAmount(), selectedOfferitems);
		service.addInvoice(invoice);
		return "";
	}
	public String doRefuseOfferResponse(){
		serviceOffreLocal.updateOfferResponse(selectedofferResponse.getOffer(), selectedofferResponse.getProvider(), selectedofferResponse.getPostedDate(), selectedofferResponse.getAmount(), selectedofferResponse.getSpecifications(), OfferResponseState.Refused, selectedofferResponse.getNotes());
		return "";
	}
	// Getter & setter
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	public OfferResponse getOfferResponse() {
		return offerResponse;
	}
	
	public String getConvertProduct() {
		return convertProduct;
	}
	public void setConvertProduct(String convertProduct) {
		this.convertProduct = convertProduct;
	}
	public void setOfferResponse(OfferResponse offerResponse) {
		this.offerResponse = offerResponse;
	}

	public List<Offer> getOffers() {
		String item;
		String product="";
		Offer o=new Offer();
		Integer index=null;
		char pos;
		offers=serviceOffreLocal.findAllOffers();
		for(Iterator<Offer> it=offers.iterator(); it.hasNext();)
		{
			o=it.next();
			item=o.getItems();
			index=offers.indexOf(o);
			for(int i=0;i<item.length();i++)
			{
				pos=item.charAt(i);
				if(pos==',')
				{
					pos=' ';
					product=product+pos;
				}
				if(pos==';')
				{
					pos='\n';
					product=product+pos;
				}
				product=product+pos;
				o.setConvertedItems(product);
				offers.set(index,o);
			}
			
		}
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Offer getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(Offer selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

	public Boolean getDisplayUpdateDeleteForm() {
		return displayUpdateDeleteForm;
	}

	public void setDisplayUpdateDeleteForm(Boolean displayUpdateDeleteForm) {
		this.displayUpdateDeleteForm = displayUpdateDeleteForm;
	}

	public Boolean getDisplayAddResponseForm() {
		return displayAddResponseForm;
	}
	
	public HashMap<String, Integer> getItems() {
		return items;
	}
	public void setItems(HashMap<String, Integer> items) {
		this.items = items;
	}
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
	public void setDisplayAddResponseForm(Boolean displayAddResponseForm) {
		this.displayAddResponseForm = displayAddResponseForm;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public Integer getQuentity() {
		return quentity;
	}
	public void setQuentity(Integer quentity) {
		this.quentity = quentity;
	}
	public OfferResponse getSelectedofferResponse() {
		return selectedofferResponse;
	}
	public void setSelectedofferResponse(OfferResponse selectedofferResponse) {
		this.selectedofferResponse = selectedofferResponse;
	}
	public Boolean getDisplayOffreSpecificationForm() {
		return displayOffreSpecificationForm;
	}

	public void setDisplayOffreSpecificationForm(
			Boolean displayOffreSpecificationForm) {
		this.displayOffreSpecificationForm = displayOffreSpecificationForm;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public String getSelectedOffreProduct() {
		return selectedOffreProduct;
	}
	public void setSelectedOffreProduct(String selectedOffreProduct) {
		this.selectedOffreProduct = selectedOffreProduct;
	}
	public String getSelectedQuentity() {
		return selectedQuentity;
	}
	public void setSelectedQuentity(String selectedQuentity) {
		this.selectedQuentity = selectedQuentity;
	}
	public HashMap<String, Integer> getSelectedOfferitems() {
		return selectedOfferitems;
	}
	public void setSelectedOfferitems(HashMap<String, Integer> selectedOfferitems) {
		this.selectedOfferitems = selectedOfferitems;
	}
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public List<String> getProviderNames() {
		providerNames=serviceOffreLocal.findAllProviderName();
		return providerNames;
	}

	public void setProviderNames(List<String> providerNames) {
		this.providerNames = providerNames;
	}
	
	public Boolean getDisplayProviderSpecificationForm() {
		return displayProviderSpecificationForm;
	}
	public void setDisplayProviderSpecificationForm(Boolean displayProviderSpecificationForm) {
		this.displayProviderSpecificationForm = displayProviderSpecificationForm;
	}
	
	public List<OfferResponse> getOfferResponses() {
		offerResponses=serviceOffreLocal.findAllOffreRespnses(selectedOffer.getId());
		return offerResponses;
	}

	public void setOfferResponses(List<OfferResponse> offerResponses) {
		this.offerResponses = offerResponses;
	}
	
	public Boolean getDisplayAcceptRefuseForm() {
		return displayAcceptRefuseForm;
	}
	public void setDisplayAcceptRefuseForm(Boolean displayAcceptRefuseForm) {
		this.displayAcceptRefuseForm = displayAcceptRefuseForm;
	}
	
	//Other
	
	
	public void TransferFile(String fileName,InputStream in){
		try{
			OutputStream out = new FileOutputStream(new File(uploadPath+fileName));
			int reader=0;
			byte[] bytes = new byte [(int)getFile().getSize()];
			while((reader=in.read(bytes))!= -1){
				out.write(bytes,0,reader);
			}
			in.close();
			out.flush();
			out.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public Boolean upload(){
		String extValide;
		if(getFile()!=null){
			String ext=getFile().getFileName();
			if(ext!=null){
				extValide=ext.substring(ext.indexOf(".")+1);
			}else{
				extValide="null";
			}
			if(extValide.equals("pdf")){
				try{
					TransferFile(getFile().getFileName(),getFile().getInputstream());
				}catch(IOException e){
					Logger.getLogger(OfferBean.class.getName()).log(Level.SEVERE,null,e);
					FacesContext context = FacesContext.getCurrentInstance();
					context.addMessage(null, new FacesMessage("Wrong","Error uploading file !!"));
					return false;
				}
				return true;
			}else{
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Wrong","Only pdf file !!"));
				return false;
			}
		}
		else{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Wrong","Select a file !!"));
			return false;
		}
	}
	
}
