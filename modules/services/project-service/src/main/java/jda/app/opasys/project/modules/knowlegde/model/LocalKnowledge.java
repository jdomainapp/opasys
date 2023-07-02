package jda.app.opasys.project.modules.knowlegde.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocalKnowledge {
	private int id;

	private int projectId;
	
	private int activityId;

	private String name;

	private String description;

	private int status;

	private String attachment;

	private int userId;
	
	private int knowledgeType;

}