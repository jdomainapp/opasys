package jda.app.opasys.project.modules.opainterface.modelasset;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProjectAsset{

	private int id;

	private String name;

	private String description;
	
	private int projectType;
	
	private int projManagerId;
	
	private int status;
	
	private Date startDate;
	
	private Date endDate;
	
}
