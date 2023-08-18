package jda.app.opasys.project.modules.opainterface.modelasset;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ConfigAsset extends KnowledgeAsset{

	public ConfigAsset(int id, String name, String description, int status, String attachment, int userId, int projectId,
			int activityId) {
		super(name, description, status, attachment, userId, projectId, activityId, id);
	}

	
}