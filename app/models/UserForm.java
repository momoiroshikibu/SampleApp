package models;

import java.util.List;

import javax.validation.Valid;


public class UserForm {
	
	@Valid
	public List<User> users;
}
