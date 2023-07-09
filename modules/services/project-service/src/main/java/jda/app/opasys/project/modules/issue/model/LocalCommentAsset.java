package jda.app.opasys.project.modules.issue.model;

import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LocalCommentAsset {

	private int id;

	private int commentUserId;
	
	private LocalIssueAsset issue;
	
	private String title;

	private String comment;
	
	private Date createDate;
	

}