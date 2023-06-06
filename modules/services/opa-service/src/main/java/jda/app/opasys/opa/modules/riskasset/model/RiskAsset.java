package jda.app.opasys.opa.modules.riskasset.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.common.model.KnowlegdeAsset;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name = "risk_asset", schema = "opa")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id", scope = RiskAsset.class)
public class RiskAsset extends KnowlegdeAsset{

	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	private int level;
	
	private String occurence;
	
	private String impact;
	
	private String solution;
	
}