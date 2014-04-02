package models;

import javax.persistence.*;

import play.db.ebean.Model;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;

@Entity
@Table(name = "users")
public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * finder
	 */
	public final static Finder<Long, User> find = new Finder<Long, User>(
			Long.class, User.class);

	@Id
	@GeneratedValue
	private Long id;

	@Email
	@Required
	private String email;

	@Required
	private String password;

	private String name;

	public User(final String email, final String password, final String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ログインIDとパスワードでユーザを検索
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public static final User findByIdAndPassword(final Long id, final String password) {
		return find.where().eq("id", id).eq("password", password).findUnique();
	}
}