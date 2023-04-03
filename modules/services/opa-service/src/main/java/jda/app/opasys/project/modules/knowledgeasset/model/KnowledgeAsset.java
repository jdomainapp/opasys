package jda.app.opasys.project.modules.knowledgeasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.project.modules.project.model.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "knowledge_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = KnowledgeAsset.class)
public class KnowledgeAsset extends RepresentationModel<KnowledgeAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	@ManyToOne
    @JoinColumn(name="project_id", nullable=false)
	private Project project;
	
	@Column(name = "activity_type")
	private int activityType;
	
	private int status;
	
	private String attachment;
}