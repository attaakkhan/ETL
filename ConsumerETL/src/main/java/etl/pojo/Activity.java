package etl.pojo;

import javax.persistence.*;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.sql.Timestamp;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the no_media database table.
 * 
 */
@Entity
@Table(name = "activity")
public class Activity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="activity_equip_id_seq", name="activity_equip_id_seq")
	@GeneratedValue(generator="activity_equip_id_seq", strategy=GenerationType.SEQUENCE)
	@Generated(GenerationTime.INSERT)
	@Id
	@Column(name="equip_id", columnDefinition="serial")
	private Integer equip_id;
	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	@Column(name = "time")
	private Timestamp time;

	@Column(name = "id")
	private Integer personId;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
}