package jda.app.opasys.project.modules.activity.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocalKnowledgeAsset {
	private int id;

	private int projectId;

	private String name;

	private String description;

	private int status;

	private String attachment;

	private int userId;
	
	private int activityType;

}