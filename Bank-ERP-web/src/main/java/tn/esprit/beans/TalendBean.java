package tn.esprit.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import demo.second_0_1.Second;

@ManagedBean
@SessionScoped
public class TalendBean {

	Second talendJob = new Second();

	public Second getTalendJob() {
		return talendJob;
	}

	public String refreshInventory() {
		talendJob.runJob(new String[] {});
		return "template";
	}
}