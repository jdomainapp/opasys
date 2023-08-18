package jda.app.opasys.project.modules.opainterface.modelasset;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PlanAsset extends KnowledgeAsset{

	public PlanAsset(int id, String name, String description, int status, String attachment, int userId, int projectId,
			int activityId) {
		super(name, description, status, attachment, userId, projectId, activityId, id);
		
	}

}