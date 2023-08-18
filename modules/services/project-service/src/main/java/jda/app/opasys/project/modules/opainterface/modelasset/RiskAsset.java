package jda.app.opasys.project.modules.opainterface.modelasset;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RiskAsset extends KnowledgeAsset{
	private int level;
	
	private String occurence;
	
	private String impact;
	
	private String solution;

	public RiskAsset(int id, String name, String description, int status, String attachment, int userId, int projectId,
			int activityId, int level, String occurence, String impact, String solution) {
		super(name, description, status, attachment, userId, projectId, activityId, id);
		
		this.level = level;
		this.occurence = occurence;
		this.impact = impact;
		this.solution = solution;
	}
	
	
}