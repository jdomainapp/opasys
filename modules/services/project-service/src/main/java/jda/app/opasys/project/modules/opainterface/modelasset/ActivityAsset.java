package jda.app.opasys.project.modules.opainterface.modelasset;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString

public class ActivityAsset {

	private int id;

	private String name;

	private String description;
	
	private int userId;
	
	private int projectId;
	

}
