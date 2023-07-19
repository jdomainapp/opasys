package jda.app.opasys.project.modules.asset.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefectAsset {

	private int id;
	
	private String name;

	private String description;
	
	private int status;
	
	private String attachment;
	
	private int userId;
	
	private int projectId;
	
	private int activityId;
	
	private int level;
	
	private String solution;

	
}