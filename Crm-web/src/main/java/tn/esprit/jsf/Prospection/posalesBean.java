package tn.esprit.jsf.Prospection;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.PointOfSale;
import service.PointOfSaleService;



@ManagedBean
@SessionScoped
public class posalesBean implements Serializable {

private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private List<PointOfSale> pointSales;
	
	@EJB
	PointOfSaleService posaleService;
	
	public List<PointOfSale> getpointSales(){
		pointSales = posaleService.findallPointOfSale();
		return pointSales;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
