package jda.app.opasys.common.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class OPA extends Asset{
	

	private String name;

	private String description;
	
	private int status;
	
	private String attachment;
	
	@Column(name = "user_id")
	private int userId;
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	public OPA() {
		
	}

	public OPA(String name, String description, int status, String attachment, int userId, int id) {
		super();
		this.name = name;
		this.description = description;
		this.status = status;
		this.attachment = attachment;
		this.userId = userId;
		this.id = id;
	}
	
	
}
