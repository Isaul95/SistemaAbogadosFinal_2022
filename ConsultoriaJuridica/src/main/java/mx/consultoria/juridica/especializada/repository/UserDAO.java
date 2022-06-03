package mx.consultoria.juridica.especializada.repository;

import java.util.List;

import mx.consultoria.juridica.especializada.entities.User;


public interface UserDAO {
	public List<User> list();
	
	public User get(int id);
	
	public void saveOrUpdate(User user);
	
	public void save (User user);
	
	public void delete(int id);
}