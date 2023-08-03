package jda.app.opasys.project.modules.opainterface.modelasset;

public class DefectAsset extends KnowledgeElementAsset{
	
	private int level;
	
	private String solution;
	
	
	public DefectAsset(int id, String name, String description, int status, String attachment, int projectId, int activityId,
			int userId, int level, String solution) {
		super(id,name,description,status,attachment,projectId,activityId,userId);
		this.level = level;
		this.solution = solution;
	}
	
}