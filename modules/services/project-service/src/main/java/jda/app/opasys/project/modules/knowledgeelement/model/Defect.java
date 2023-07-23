package jda.app.opasys.project.modules.knowledgeelement.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "defect", schema = "project")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("defect") 
public class Defect extends KnowledgeElement{

	private int level;
	
	private String solution;
	
}