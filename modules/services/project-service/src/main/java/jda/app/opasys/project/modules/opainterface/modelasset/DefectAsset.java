package jda.app.opasys.project.modules.opainterface.modelasset;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DefectAsset extends KnowledgeAsset{
	private int level;
	
	private String solution;

	public DefectAsset(int id, String name, String description, int status, String attachment, int userId, int projectId,
			int activityId,  int level, String solution) {
		super(name, description, status, attachment, userId, projectId, activityId, id);
		
		this.level = level;
		this.solution = solution;
	}
	
}