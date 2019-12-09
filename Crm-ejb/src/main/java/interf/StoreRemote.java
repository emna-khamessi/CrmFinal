package interfaces;

import java.util.List;


import model.Store;
import model.User;

public interface StoreRemote {
	public List<Store> getAllStore();
	public Store getStoreById(int id);

}
