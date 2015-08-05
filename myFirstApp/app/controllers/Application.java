package controllers;

import java.sql.Connection;

import javax.sql.DataSource;

import com.avaje.ebean.Ebean;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;
import play.db.*;
import views.html.*;

public class Application extends Controller {
	private static final Form<User> userForm = Form.form(User.class);
//	private static final DataSource ds = DB.getDataSource();
//	private static final Connection connection = DB.getConnection();

    public static Result index() {
    	return ok(index.render(userForm));
    }
    
    public static Result save(){
		Form<User> boundForm = userForm.bindFromRequest();
		User created = boundForm.get();
		Ebean.save(created);
		return ok(String.format("User info: %s", created.toString()));
    }
}
