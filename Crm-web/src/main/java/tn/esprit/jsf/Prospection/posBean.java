package tn.esprit.jsf.Prospection;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.PointOfProspection;
import model.Tache;
import service.PointOfProspectionService;

@ManagedBean
@SessionScoped
public class posBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private List<PointOfProspection> pointsPros;
	
	@EJB
	PointOfProspectionService posService;
	
	public List<PointOfProspection> getpointsPros()
	{
		pointsPros = posService.findallPointOfProspection();
		return pointsPros;
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
