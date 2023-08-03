package jda.app.opasys.project.modules.opainterface.modelasset;

import lombok.Getter;
 
@Getter
public class KnowledgeElementAsset extends Asset{

	public String name;

	public String description;

	public int status;

	public int projectId;
	
	public int activityId;
	
	public int userId;
	
	public KnowledgeElementAsset(int id) {
		super(id);
	}

	public KnowledgeElementAsset(int id, String name, String description, int status, String attachment, int projectId,
			int activityId, int userId) {
		super(id, attachment);
		this.name = name;
		this.description = description;
		this.status = status;
		this.projectId = projectId;
		this.activityId = activityId;
		this.userId = userId;
	}
	
}
