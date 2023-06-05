package jda.app.opasys.issue.modules.issueasset.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.common.model.KnowlegdeAsset;
import jda.app.opasys.issue.modules.comment.model.Comment;
import jda.app.opasys.issue.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue", schema = "issue")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = IssueAsset.class)
public class IssueAsset extends KnowlegdeAsset{
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="assignee_id", nullable=false)
	private User assignee;
	
	@Column(name="parent_issue_id")
	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy="issue")
	private List<Comment> comments;
}