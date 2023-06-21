package jda.app.opasys.opa.modules.activityasset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "activity_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = ActivityAsset.class)
public class ActivityAsset {
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	private String name;

	private String description;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "project_id")
	private int projectId;
	

}
