package interfaces;

import java.util.List;

import model.Rdv;

public interface RdvRemote {
	public int addRdv(Rdv u);
	public List<Rdv> getAllRdvByUser(int id);
	public void updateRdv(Rdv u, int id);
	public void deleteRdv(int id);
	public Rdv getRdvByUser(int id);
	public Rdv getRdvById(int id);
	public List<Rdv> getAllRdv();
}
