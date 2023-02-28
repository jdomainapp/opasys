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
import javax.persistence.Table;
import javax.persistence.Transient;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "project")
public class Project {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
	private String attachment;
	
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
	
	@Transient
	private List<Activity> activities;
}
