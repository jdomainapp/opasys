package jda.app.opasys.project.modules.issuecomment.model;

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

import jda.app.opasys.project.modules.knowledgeelement.model.Issue;
import jda.app.opasys.project.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "issue_comment", schema = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Comment extends RepresentationModel<Comment>{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User commentUser;
	
	@ManyToOne
    @JoinColumn(name="issue_id", nullable=false)
	private Issue issue;
	
	private String title;

	private String comment;
	
	@Column(name = "create_date")
	private Date createDate;
	

}