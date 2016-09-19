package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Provider;

@Remote
public interface ProviderServiceRemote {

	Boolean createProvider(Provider provider);

	Boolean saveProvider(Provider provider);
	
	Provider findProviderById(Integer id);

	Boolean removeProvider(Provider provider);

	List<Provider> findAllProviders();

}
