package jda.app.opasys.project.modules.opainterface.modelasset;

public class PlanAsset extends KnowledgeElementAsset{
	
	public PlanAsset(int id, String name, String description, int status, String attachment, int projectId, int activityId,int userId) {
		super(id,name,description,status,attachment,projectId,activityId,userId);
	}
}