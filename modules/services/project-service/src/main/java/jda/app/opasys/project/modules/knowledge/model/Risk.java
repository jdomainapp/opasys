package jda.app.opasys.project.modules.knowledge.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jda.app.opasys.project.modules.knowledge.model.Knowledge;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "risk", schema = "project")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("risk") 
public class Risk extends Knowledge{
	private int level;
	
	private String occurence;
	
	private String impact;
	
	private String solution;

}