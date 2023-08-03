package jda.app.opasys.project.modules.opainterface.modelasset;

import java.util.Date;

public class IssueAsset extends KnowledgeElementAsset{
	
	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;

	private int assigneeId;

	private Date createDate;
	
	public IssueAsset(int id) {
		super(id);
	}

	public IssueAsset(int id, String name, String description, int status, String attachment, int projectId,
			int activityId, int userId, int parentIssueId, String summary, int type, int priority, int assigneeId,
			Date createDate) {
		super(id, name, description, status, attachment, projectId, activityId, userId);
		this.parentIssueId = parentIssueId;
		this.summary = summary;
		this.type = type;
		this.priority = priority;
		this.assigneeId = assigneeId;
		this.createDate = createDate;
	}

	
	

}