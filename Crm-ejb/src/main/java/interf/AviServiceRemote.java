package interf;

import java.util.List;

import model.Avi;


public interface AviServiceRemote {
	public int AddAvi(Avi e);
	public List<Avi>getAllAvi();
	public void deleteAviById(int AviId);
	public void  updateAvi (Avi e);

}
