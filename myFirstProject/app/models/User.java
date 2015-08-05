package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class User extends Model{
	@Id
	public String username;
	public String password;
	public String telnumber;
	
	@Override
	public String toString() {
		return String.format("%s - %s - %s", username, password, telnumber);
	}
}
