package jda.app.opasys.project.modules.risk.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RiskAsset {

	private int id;
	
	private String name;

	private String description;
	
	private int status;
	
	private String attachment;
	
	private int projectId;
	
	private int userId;

	private int level;
	
	private String occurence;
	
	private String impact;
	
	private String solution;

}