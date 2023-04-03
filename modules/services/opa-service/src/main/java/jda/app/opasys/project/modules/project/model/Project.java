package jda.app.opasys.project.modules.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.project.modules.knowledgeasset.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "project", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = Project.class)
public class Project extends RepresentationModel<Project>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	@Column(name = "project_type")
	private String projectType;
	
	@Column(name = "username")
	private String projManager;
	
	//0: processing, 1: completed
	private int status;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@OneToMany(mappedBy="project")
	private List<KnowledgeAsset> knowledgeAssets;
	
}
