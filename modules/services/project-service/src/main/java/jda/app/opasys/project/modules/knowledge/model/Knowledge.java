package jda.app.opasys.project.modules.knowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import jda.app.opasys.common.model.OPA;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "knowledge")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CommonKnowledge.class, name = "common"),
	@JsonSubTypes.Type(value = Defect.class, name = "defect"),
	@JsonSubTypes.Type(value = Issue.class, name = "issue"),
	@JsonSubTypes.Type(value = Risk.class, name = "risk")
	})
public class Knowledge extends OPA{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "project_id")
	private int projectId;
	
	@Column(name = "activity_id")
	private int activityId;
	
	private String type;

}
