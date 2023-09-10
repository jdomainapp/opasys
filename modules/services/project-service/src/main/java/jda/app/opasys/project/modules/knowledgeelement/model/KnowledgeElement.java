package jda.app.opasys.project.modules.knowledgeelement.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.project.model.Project;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "knowledge")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(value = Plan.class, name = "plan"),
	@JsonSubTypes.Type(value = Config.class, name = "config"),
	@JsonSubTypes.Type(value = Metric.class, name = "metric"),
	@JsonSubTypes.Type(value = Finance.class, name = "finance"),
	@JsonSubTypes.Type(value = Defect.class, name = "defect"),
	@JsonSubTypes.Type(value = Issue.class, name = "issue"),
	@JsonSubTypes.Type(value = Risk.class, name = "risk")
	})
public class KnowledgeElement extends OPA{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
    @JoinColumn(name="project_id", nullable=false)
	private Project project;
	
	@ManyToOne
    @JoinColumn(name="activity_id", nullable=false)
	private Activity activity;
	
	private String type;

}
