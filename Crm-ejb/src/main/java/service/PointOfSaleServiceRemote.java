package service;

import java.util.List;

import javax.ejb.Remote;

import model.PointOfSale;

@Remote
public interface PointOfSaleServiceRemote {

	public int AddPointOfSale(PointOfSale pointOfSale);
	public void removePointOfSale(int idPointOfSale);
	public void updatePointOfSale(PointOfSale pointOfSale);
	public PointOfSale findPointOfSaleById(int idPointOfSale);
	public List<PointOfSale> findallPointOfSale();
}
