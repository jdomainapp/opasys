package jda.app.opasys.issue.modules.commentasset.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;
import jda.app.opasys.issue.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "comment", schema = "issue")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = CommentAsset.class)
public class CommentAsset extends RepresentationModel<CommentAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User commentUser;
	
	@ManyToOne
    @JoinColumn(name="issue_id", nullable=false)
	private IssueAsset issue;

	private String comment;
	
	@Column(name = "create_date")
	private Date createDate;

}