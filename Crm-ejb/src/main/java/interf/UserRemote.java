package interfaces;

import java.util.List;

import model.User;



public interface UserRemote {

	public int addUser(User u);
	public List<User> getAllUsers();
	public void updateUser(User u, int id);
	public void deleteUser(int id);
	public User getUserByEmailAndPassword(String email, String password);
	public User getUserById(int id);
	
}
