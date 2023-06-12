package jda.app.opasys.project.modules.project.model;

import java.util.Date;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProjectAsset {
	private int id;

	private String name;

	private String description;
	
	private int projectType;
	
	private int projManagerId;
	
	private int status;
	
	private Date startDate;
	
	private Date endDate;

}
