package tn.esprit.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Claim;



@Remote
public interface ClaimServicesRemote {
	public boolean AddClaim(Claim claim);

	public boolean DeleteClaim(Claim claim);

	public Claim findClaimById(Integer Id);

	public boolean updateClaim(Claim claim);
	
	public List<Claim> findAll();
}
