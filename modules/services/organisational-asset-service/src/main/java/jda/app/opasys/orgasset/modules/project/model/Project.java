package jda.app.opasys.orgasset.modules.project.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @ToString
public class Project extends RepresentationModel<Project>{

	private int id;

	private String name;

	private String description;
	
	private String type;
	
	private User projManager;
	
	//0: processing, 1: completed
	private int status;
	
	private Date startDate;
	
	private Date endDate;
}
