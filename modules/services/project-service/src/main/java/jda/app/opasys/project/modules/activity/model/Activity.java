package jda.app.opasys.project.modules.activity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import jda.app.opasys.project.modules.project.model.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "activity")
public class Activity {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int type;
	
	private String attachment;
	
//	@Column(name = "userId")
//	private int userId;
	@ManyToOne
    @JoinColumn(name="userId", nullable=false)
	private User user;
	
	private int projectId;;
	
}