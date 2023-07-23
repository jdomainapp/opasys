package jda.app.opasys.project.modules.knowledgeelement.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "finance")
@PrimaryKeyJoinColumn(name = "id")
@JsonTypeName("finance") 
public class Finance extends KnowledgeElement{

}