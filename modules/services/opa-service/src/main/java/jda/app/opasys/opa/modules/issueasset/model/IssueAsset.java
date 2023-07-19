package jda.app.opasys.opa.modules.issueasset.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.common.model.KnowledgeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = IssueAsset.class)
public class IssueAsset extends KnowledgeAsset{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name="assignee_id", nullable=false)
	private int assigneeId;
	
	@Column(name="parent_issue_id")
	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;
	
	@Column(name = "create_date")
	private Date createDate;
	
	@OneToMany(mappedBy="issue")
	private List<CommentAsset> commentAssets;
}