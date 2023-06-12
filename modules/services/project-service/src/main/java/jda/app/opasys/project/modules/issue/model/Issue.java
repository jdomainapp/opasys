package jda.app.opasys.project.modules.issue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.common.model.KnowlegdeAsset;
import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue", schema = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = Issue.class)
public class Issue extends OPA{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="parent_issue_id")
	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy="issue")
	private List<Comment> comments;
	
	@ManyToOne
    @JoinColumn(name="project_id", nullable=false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name="assignee_id", nullable=false)
	private User assignee;
}