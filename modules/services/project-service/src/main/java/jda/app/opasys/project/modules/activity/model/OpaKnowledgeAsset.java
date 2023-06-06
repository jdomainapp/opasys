package jda.app.opasys.project.modules.activity.model;

public class OpaKnowledgeAsset {
	private int id;

	private int projectId;

	private String name;

	private String description;

	private int status;

	private String attachment;

	private int userId;

	private int activityType;

	public OpaKnowledgeAsset(int id, int projectId, String name, String description, int status, String attachment,
			int userId, int activityType) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.attachment = attachment;
		this.userId = userId;
		this.activityType = activityType;
	}

}