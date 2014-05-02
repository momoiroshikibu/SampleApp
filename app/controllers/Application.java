package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import models.User;
import models.UserForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        
        
//        UserForm userForm = new UserForm();
//        userForm.users = User.find.all();
        
        final List<String> animalList = Arrays.asList(
                new String[] {"Dog", "Cat", "Bird"});
        final List<String> colorList = Arrays.asList(
                new String[] {"Red", "Green", "Blue", "Pink", "Black", "White"});
        
        final Form<User> userForm = Form.form(User.class).bindFromRequest();
        
        return ok(index.render(userForm, animalList, colorList));
    }

}
