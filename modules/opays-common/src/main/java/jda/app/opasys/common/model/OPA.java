package jda.app.opasys.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class OPA{


	private String name;

	private String description;
	
	private int status;
	
	private String attachment;
	
	@Column(name = "user_id")
	private int userId;
}
