package service;

import java.util.List;

import javax.ejb.Remote;

import model.PointOfProspection;

@Remote
public interface PointOfProspectionServiceRemote {
	
	public int AddPointOfProspection(PointOfProspection pointOfProspection);
	public void removePointOfProspection(int idPointOfProspection);
	public void updatePointOfProspection(PointOfProspection pointOfProspection);
	public PointOfProspection findPointOfProspectionById(int idPointOfProspection);
	public List<PointOfProspection> findallPointOfProspection();
}
