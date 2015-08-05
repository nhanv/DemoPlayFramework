package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;
import play.Logger;
import play.data.Form;
import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
	private static final Form<User> userForm = Form.form(User.class);
	private static final Connection connection = DB.getConnection();

    public static Result index() {
    	return ok(index.render(userForm));
    }
    
    public static Result save(){
		Form<User> boundForm = userForm.bindFromRequest();
		if (boundForm.hasErrors())
			return badRequest();
		
		User created = boundForm.get();
		if (!validate(created))
			return ok("UserName / Password / TelNumber is empty.");
		
		String sql = String.format("INSERT INTO user (username, password, telnumber) VALUES(\'%s\', \'%s\', \'%s\')",
					 created.username, created.password, created.telnumber);
		Logger.info(sql);
		try {
			Statement stm = connection.createStatement();
			stm.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ok(String.format("User info: %s", created.toString()));
    }
    
    private static boolean validate(User user){
    	if (user.username == null || user.username.isEmpty()) return false;
    	else if (user.password == null || user.password.isEmpty()) return false;
    	else if (user.telnumber == null || user.telnumber.isEmpty()) return false;
    	
    	return true;
    }
}
