package jda.app.opasys.project.modules.asset.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommonKnowledgeAsset {
	private int id;

	private int projectId;
	
	private int activityId;

	private String name;

	private String description;

	private int status;

	private String attachment;

	private int userId;


}