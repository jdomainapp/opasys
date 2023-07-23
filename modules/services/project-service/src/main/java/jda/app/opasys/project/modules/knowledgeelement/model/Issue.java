package jda.app.opasys.project.modules.knowledgeelement.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;
import jda.app.opasys.project.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue", schema = "project")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("issue") 
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = Issue.class)
public class Issue extends KnowledgeElement{
	
	@Column(name="parent_issue_id")
	private int parentIssueId;
	
	private String summary;
	
	@Column(name="issue_type")
	private int issueType;
	
	private int priority;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy="issue")
	private List<Comment> comments;
	
	@ManyToOne
	@JoinColumn(name="assignee_id", nullable=false)
	private User assignee;
}