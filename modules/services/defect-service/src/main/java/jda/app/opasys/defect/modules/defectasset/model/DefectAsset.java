package jda.app.opasys.defect.modules.defectasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "defect", schema = "defect")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = DefectAsset.class)
public class DefectAsset extends RepresentationModel<DefectAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	private String level;
	
	private String solution;
	
	private String status;
	
	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "user_id")
	private int userId;
}