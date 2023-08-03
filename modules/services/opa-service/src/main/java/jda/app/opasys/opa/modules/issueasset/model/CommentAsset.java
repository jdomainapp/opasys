package jda.app.opasys.opa.modules.issueasset.model;

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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue_comment", schema = "opa")
@JsonIgnoreProperties(value = "attachment")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = CommentAsset.class)
public class CommentAsset extends RepresentationModel<CommentAsset>{
	@Id
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name="user_id", nullable=false)
	private int commentUserId;
	
	@ManyToOne
    @JoinColumn(name="issue_id", nullable=false)
	private IssueAsset issue;

	private String comment;
	
	@Column(name = "create_date")
	private Date createDate;
	

}