package jda.app.opasys.project.modules.orgasset.model;

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
@Table(name = "org_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = OrgAsset.class)
public class OrgAsset extends RepresentationModel<OrgAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	@Column(name = "project_type")
	private int projectType;
	
	@Column(name = "activity_type")
	private int activityType;
	
	//0: processing, 1: completed
	private int status;
	
	private String attachment;
}
