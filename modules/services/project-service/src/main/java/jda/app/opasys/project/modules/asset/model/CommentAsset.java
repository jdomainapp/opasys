package jda.app.opasys.project.modules.asset.model;

import java.util.Date;

import jda.app.opasys.project.modules.user.model.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CommentAsset {

	private int id;

	private int commentUserId;
	
	private IssueAsset issue;
	
	private String title;

	private String comment;
	
	private Date createDate;
	

}