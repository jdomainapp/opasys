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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
public class IssueAsset2{

	private int id;
	
	private String name;

	private String description;
	
	private int status;
	
	private String attachment;

	private int userId;

	private int parentIssueId;
	
	private String summary;
	
	private int type;
	
	private int priority;
	
	private int projectId;

	private User assignee;

	private Date createDate;

	public IssueAsset2(int id) {
		super();
		this.id = id;
	}
	

}