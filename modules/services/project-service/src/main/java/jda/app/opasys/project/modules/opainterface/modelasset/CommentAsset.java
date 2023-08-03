package jda.app.opasys.project.modules.opainterface.modelasset;

import java.util.Date;

import jda.app.opasys.project.modules.user.model.User;
import lombok.AllArgsConstructor;

public class CommentAsset extends Asset{

	private int commentUserId;
	
	private IssueAsset issue;
	
	private String title;

	private String comment;
	
	private Date createDate;

	public CommentAsset(int id, int commentUserId, IssueAsset issue, String title, String comment, Date createDate) {
		super(id);
		this.commentUserId = commentUserId;
		this.issue = issue;
		this.title = title;
		this.comment = comment;
		this.createDate = createDate;
	}
	
	
}