package jda.app.opasys.project.modules.opainterface.modelasset;

import java.util.Date;

import jda.app.opasys.common.model.Asset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentAsset extends Asset{
	private int id;
	private int commentUserId;
	
	private IssueAsset issue;

	private String comment;
	
	private Date createDate;

	public CommentAsset(int id, int commentUserId, IssueAsset issue, String comment, Date createDate) {
		super();
		this.id = id;
		this.commentUserId = commentUserId;
		this.issue = issue;
		this.comment = comment;
		this.createDate = createDate;
	}
	

}