package jda.app.opasys.opa.modules.issue.model;

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

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.opa.modules.project.model.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = IssueAsset.class)
public class IssueAsset extends RepresentationModel<IssueAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name="project_id", nullable=false)
	private int projectId;

	@Column(name="user_create_id", nullable=false)
	private int createUserId;
	
	@Column(name="assignee_id", nullable=false)
	private int assigneeId;
	
	@Column(name="parent_issue_id")
	private int parentIssueId;

	private String description;
	
	private String summary;
	
	private int status;
	
	private int type;
	
	private int priority;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy="issue")
	private List<Comment> comments;
}