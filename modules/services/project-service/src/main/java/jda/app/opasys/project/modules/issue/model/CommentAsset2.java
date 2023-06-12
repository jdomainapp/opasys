package jda.app.opasys.project.modules.issue.model;

import java.util.Date;

import jda.app.opasys.project.modules.user.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentAsset2 {

	private int id;

	private User commentUser;
	
	private IssueAsset2 issue;
	
	private String title;

	private String comment;
	
	private Date createDate;
	

}