package etl.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "login")
public class Login {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="login_equip_id_seq", name="login_equip_id_seq")
	@GeneratedValue(generator="login_equip_id_seq", strategy=GenerationType.SEQUENCE)
	@Generated(GenerationTime.INSERT)
	@Id
	@Column(name="equip_id", columnDefinition="serial")
	private Integer equip_id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;
	
	@Column(name = "time")
	private Timestamp time;

	@Column(name = "id")
	private Integer personId;
	
	@Column(name = "age")
	private Integer age;

	/**
	 * @return the equip_id
	 */
	public Integer getEquip_id() {
		return equip_id;
	}

	/**
	 * @param equip_id the equip_id to set
	 */
	public void setEquip_id(Integer equip_id) {
		this.equip_id = equip_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
}
