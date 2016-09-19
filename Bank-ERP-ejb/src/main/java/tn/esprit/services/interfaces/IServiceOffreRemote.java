package tn.esprit.services.interfaces;


import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import tn.esprit.entities.Offer;
import tn.esprit.entities.OfferResponse;
import tn.esprit.entities.Provider;
import tn.esprit.enumerators.OfferResponseState;

@Remote
public interface IServiceOffreRemote {
	Boolean addOffer(Offer offer);
	Boolean updateOffer(Offer offer);
	Boolean deleteOffer(Offer offer);
	Offer findOfferById(Integer id);
	Offer findOfferByName(String name);
	List<Offer> findAllOffers();
	Boolean addOfferResponse(Offer offer,Provider provider,Date postedDate,Double amount,String specifications,OfferResponseState state,String notes);
	Boolean updateOfferResponse(Offer offer,Provider provider,Date postedDate,Double amount,String specifications,OfferResponseState state,String notes);
	List<OfferResponse> findAllOffreRespnses(Integer idOffer);
	List<OfferResponse> findAllOffreRespnses(Integer idOffer,Integer idProvider);
	List<String> findAllProviderName();
	Provider findProviderByName(String name);
}
