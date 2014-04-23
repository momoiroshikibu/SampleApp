package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.UserForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.BodyParser;

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
	@BodyParser.Of(BodyParser.Json.class)
	public static Result create() {
		
		// UserForm.usersには@Validが設定されているため、バリデーションが有効となる
		final Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(form.errorsAsJson());
		}
		
		// ユーザを登録
		final UserForm userForm = form.get();
		final List<User> userList = userForm.users;
		final List<User> savedUserList = new ArrayList<User>(); 
		for (final User user : userList) {
			user.save();
			savedUserList.add(user);
		}
		return ok(Json.toJson(savedUserList));
	}
	

}
