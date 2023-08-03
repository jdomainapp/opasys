package jda.app.opasys.project.modules.opainterface.modelasset;

import lombok.AllArgsConstructor;

public class RiskAsset extends KnowledgeElementAsset{

	private int level;
	private String occurence;
	
	private String impact;
	
	private String solution;
	
	public RiskAsset(int id, String name, String description, int status, String attachment, int projectId, int activityId,
			int userId, int level, String occurence, String impact, String solution) {
		super(id,name,description,status,attachment,projectId,activityId,userId);
		this.level = level;
		this.occurence=occurence;
		this.impact=impact;
		this.solution = solution;
	}

}