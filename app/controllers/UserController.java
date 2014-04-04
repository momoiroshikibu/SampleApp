package controllers;

import java.util.List;

import models.User;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

	/**
	 * 全てのユーザを返す
	 * 
	 * @return
	 */
	public static Result index() {
		List<User> userList = User.find.all();
		return ok(Json.toJson(userList));
	}
	
	/**
	 * IDで指定したユーザを返す
	 * 
	 * @param id
	 * @return
	 */
	public static Result show(final Long id) {
		final User user = User.find.byId(id);
		if (user == null) {
			return notFound();
		}
		return ok(Json.toJson(user));
	}
	
	/**
	 * ユーザを新規作成する
	 * 
	 * @return
	 */
	public static Result create() {
		
		final Form<User> userForm = Form.form(User.class).bindFromRequest();
		if (userForm.hasErrors()) {
			return badRequest(userForm.errorsAsJson());
		}
		final User user = userForm.get();
		user.save();
		return created(Json.toJson(user));
//		return ok(Json.toJson(user));
	}
	

}
