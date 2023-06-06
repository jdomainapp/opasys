package jda.app.opasys.project.modules.project.model;

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

import org.springframework.data.annotation.Transient;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.defectasset.model.DefectAsset;
import jda.app.opasys.project.modules.issueasset.model.IssueAsset;
import jda.app.opasys.project.modules.riskasset.model.RiskAsset;
import jda.app.opasys.project.modules.user.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ProjectAsset extends RepresentationModel<ProjectAsset>{
	private int id;

	private String name;

	private String description;
	
	private int projectType;
	
	private int projManagerId;
	
	private int status;
	
	private Date startDate;
	
	private Date endDate;

	public ProjectAsset(int id, String name, String description, int projectType, int projManagerId, int status,
			Date startDate, Date endDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.projectType = projectType;
		this.projManagerId = projManagerId;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
