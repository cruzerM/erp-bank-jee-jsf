package tn.esprit.services;


import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tn.esprit.entities.Offer;
import tn.esprit.entities.OfferResponse;
import tn.esprit.entities.Provider;
import tn.esprit.enumerators.OfferResponseState;
import tn.esprit.services.interfaces.IServiceOffreLocal;
import tn.esprit.services.interfaces.IServiceOffreRemote;

/**
 * Session Bean implementation class ServiceOffre
 */
@Stateless
public class ServiceOffre implements IServiceOffreRemote, IServiceOffreLocal {

	@PersistenceContext(unitName = "Bank-ERP-ejb")
	private EntityManager em;
	
    public ServiceOffre() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Boolean addOffer(Offer offer) {
		try {
			em.persist(offer);
			return true;
		} catch (Exception e) {
			System.out.println("Persist offer Error: " + e.getMessage());
			return false;
		}
		
	}

	@Override
	public Boolean updateOffer(Offer offer) {
		try {
				em.merge(offer);
				return true;
			} catch (Exception e) {
				System.out.println("Update offer Error: " + e.getMessage());
				return false;
			}	
	}

	@Override
	public Boolean deleteOffer(Offer offer) {
		try {
				em.remove(em.merge(offer));
				return true;
			} catch (Exception e) {
				System.out.println("Delete offer Error: " + e.getMessage());
				return false;
			}
			
	}

	@Override
	public Offer findOfferById(Integer id) {
		return em.find(Offer.class, id);
	}

	@Override
	public Offer findOfferByName(String name) {
		String jpql = "select o from Offer o where o.name=:param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", name);
		Offer offerFound = null;
		try {
			offerFound = (Offer) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("no result");
		}
		return offerFound;
	}

	@Override
	public List<Offer> findAllOffers() {
		String jpql = "select o from Offer o";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public Boolean addOfferResponse(Offer offer, Provider provider,
			Date postedDate, Double amount, String specifications,
			OfferResponseState state, String notes) {
		OfferResponse roles = new OfferResponse(offer,provider,postedDate,amount,
				specifications,state,notes);
		try{
			em.persist(roles);
			return true;
			}catch(Exception ex){
				System.out.println("Persist response Error: " +ex.getMessage());
				return false;
			}
	}

	@Override
	public Boolean updateOfferResponse(Offer offer, Provider provider,
			Date postedDate, Double amount, String specifications,
			OfferResponseState state, String notes) {
		OfferResponse roles = new OfferResponse(offer,provider,postedDate,amount,
				specifications,state,notes);
		try{
			em.merge(roles);
			return true;
			}catch(Exception ex){
				System.out.println("Update response Error: " + ex.getMessage());
				return false;
			}
	}

	@Override
	public List<OfferResponse> findAllOffreRespnses(Integer idOffer) {
		Query query=em.createQuery("select o from OfferResponse o where o.offer.id=:id");
		query.setParameter("id", idOffer);
		return query.getResultList();
	}

	@Override
	public List<OfferResponse> findAllOffreRespnses(Integer idOffer,
			Integer idProvider) {
		Query query=em.createQuery("select 0 from OfferResponse o where o.offer.id=:ido  and o.provider.id=:idp");
		query.setParameter("ido", idOffer).setParameter("idp", idProvider);
		return query.getResultList();
	}

	@Override
	public List<String> findAllProviderName() {
		Query query=em.createQuery("select p.name from Provider p");
		return query.getResultList();
	}

	@Override
	public Provider findProviderByName(String name) {
		Query query = em.createQuery("select p from Provider p where p.name=:name");
		query.setParameter("name", name);
		return (Provider) query.getSingleResult();
	}

}
