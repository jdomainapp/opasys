package jda.app.opasys.opa.modules.riskasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "risk", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = RiskAsset.class)
public class RiskAsset {

	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String level;
	
	private String description;
	
	private String occurence;
	
	private String impact;
	
	private String solution;
	
	private String status;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="project_id")
	private int projectId;
	
}