package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Users database table.
 * 
 */
@Entity
@Table(name="Users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userId;

	@Column(name="Address")
	private String address;

	@Column(name="Cin")
	private Integer cin;

	@Column(name="CodePostal")
	private Integer codePostal;

	@Column(name="ConfirmPassword")
	private String confirmPassword;

	@Column(name="Document")
	private String document;

	@Column(name="Email")
	private String email;

	@Column(name="Etat")
	private Integer etat;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="ImageUrl")
	private String imageUrl;

	@Column(name="LastName")
	private String lastName;

	@Column(name="NbClient")
	private Integer nbClient;

	@Column(name="Password")
	private String password;

	@Column(name="Phone")
	private Integer phone;

	@Column(name="PoIntegers")
	private Integer poIntegers;

	@Column(name="Role")
	private String role;

	@Column(name="Type")
	private Integer type;

	//bi-directional many-to-one association to Rdv
	@OneToMany(mappedBy="user")
	private List<Rdv> rdvs;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCin() {
		return this.cin;
	}

	public void setCin(Integer cin) {
		this.cin = cin;
	}

	public Integer getCodePostal() {
		return this.codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDocument() {
		return this.document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEtat() {
		return this.etat;
	}

	public void setEtat(Integer etat) {
		this.etat = etat;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getNbClient() {
		return this.nbClient;
	}

	public void setNbClient(Integer nbClient) {
		this.nbClient = nbClient;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhone() {
		return this.phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public Integer getPoIntegers() {
		return this.poIntegers;
	}

	public void setPoIntegers(Integer poIntegers) {
		this.poIntegers = poIntegers;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<Rdv> getRdvs() {
		return this.rdvs;
	}

	public void setRdvs(List<Rdv> rdvs) {
		this.rdvs = rdvs;
	}

	public Rdv addRdv(Rdv rdv) {
		getRdvs().add(rdv);
		rdv.setUser(this);

		return rdv;
	}

	public Rdv removeRdv(Rdv rdv) {
		getRdvs().remove(rdv);
		rdv.setUser(null);

		return rdv;
	}

}