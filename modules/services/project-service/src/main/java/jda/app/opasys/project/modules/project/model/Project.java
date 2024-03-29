package jda.app.opasys.project.modules.project.model;

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

import com.fasterxml.jackson.annotation.JsonInclude;
import jda.app.opasys.project.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "project", schema = "project")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Project extends RepresentationModel<Project>{
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String description;
	
//	@Column(name = "type_id")
//	private int typeId;
	@ManyToOne
    @JoinColumn(name="type_id", nullable=false)
	private ProjectType type;
	
//	@Column(name = "user_id")
//	private int projManagerId;
	@ManyToOne
    @JoinColumn(name="user_id", nullable=false)
	private User projManager;
	
	//0: processing, 1: completed
	private int status;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	

}
