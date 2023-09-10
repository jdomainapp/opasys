package jda.app.opasys.project.modules.opainterface.modelasset;

import java.util.Date;
import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class IssueAsset extends KnowledgeAsset{

	private int assigneeId;
	
	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;
	
	private Date createDate;
	
	public IssueAsset(int id) {
		super(id);
	}
	
	public IssueAsset(int id, String name, String description, int status, String attachment, int userId, int projectId,
			int activityId,  int parentIssueId, String summary,int assigneeId, int type, int priority,
			Date createDate) {
		super(name, description, status, attachment, userId, projectId, activityId, id);
		
		this.assigneeId = assigneeId;
		this.parentIssueId = parentIssueId;
		this.summary = summary;
		this.type = type;
		this.priority = priority;
		this.createDate = createDate;
	}
	
	
	
}