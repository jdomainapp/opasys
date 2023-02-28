package jda.app.opasys.project.modules.project.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.project.modules.activity.model.Activity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = Project.class)
public class Project {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
//	@Column(name = "typeId")
//	private int typeId;
	@ManyToOne
    @JoinColumn(name="typeId", nullable=false)
	private ProjectType type;
	
//	@Column(name = "projManagerId")
//	private int projManagerId;
	@ManyToOne
    @JoinColumn(name="projManagerId", nullable=false)
	private User projManager;
	
	//0: processing, 1: completed
	private int status;
	
	private Date startDate;
	
	private Date endDate;
	
	@OneToMany(mappedBy="project")
	private List<Activity> activities;
}
