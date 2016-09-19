package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Provider;

@Local
public interface ProviderServiceLocal {

	Boolean createProvider(Provider provider);

	Boolean saveProvider(Provider provider);
	
	Provider findProviderById(Integer id);

	Boolean removeProvider(Provider provider);

	List<Provider> findAllProviders();

}
