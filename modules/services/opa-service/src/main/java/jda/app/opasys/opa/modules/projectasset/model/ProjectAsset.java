package jda.app.opasys.opa.modules.projectasset.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "project_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = ProjectAsset.class)
public class ProjectAsset extends RepresentationModel<ProjectAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	@Column(name = "project_type")
	private int projectType;
	
	@Column(name = "user_id")
	private int projManagerId;
	
	private int status;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
}
