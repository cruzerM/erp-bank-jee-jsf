package tn.esprit.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Provider;
import tn.esprit.services.interfaces.ProviderServiceLocal;
import tn.esprit.services.interfaces.ProviderServiceRemote;

@Stateless
public class ProviderService implements ProviderServiceLocal,
		ProviderServiceRemote {

	@PersistenceContext
	private EntityManager em;

	public Boolean createProvider(Provider provider) {
		try {
			em.persist(provider);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Boolean saveProvider(Provider provider) {
		try {
			em.merge(provider);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Provider findProviderById(Integer id){
		return em.find(Provider.class, id);
	}

	public Boolean removeProvider(Provider provider) {
		try {
			em.remove(findProviderById(provider.getId()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<Provider> findAllProviders() {
		return em.createQuery("select p from Provider p", Provider.class)
				.getResultList();
	}

}
