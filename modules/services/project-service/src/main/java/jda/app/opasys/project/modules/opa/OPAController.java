package jda.app.opasys.project.modules.opa;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.data.json.impl.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.netflix.ribbon.proxy.annotation.Http;

import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.OpaKnowledgeAsset;
import jda.app.opasys.project.modules.defectasset.model.DefectAsset;
import jda.app.opasys.project.modules.issueasset.model.Comment;
import jda.app.opasys.project.modules.issueasset.model.IssueAsset;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.project.model.ProjectAsset;
import jda.app.opasys.project.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;
import jda.modules.msacommon.controller.InterfaceController;

@Controller
public class OPAController extends InterfaceController<Integer, OPA> {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);

	public final static String PATH_OPA_SERVICE = "opa-service";

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equals(HttpMethod.GET.toString())) {
			return processGetOPA(req, res);
		} else if (req.getMethod().equals(HttpMethod.POST.toString())) {
			return processCreateOPA(req, res);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	public ResponseEntity<?> processGetOPA(HttpServletRequest req, HttpServletResponse res) {
		String targetPath = ControllerTk.getServiceUri(PATH_OPA_SERVICE, req.getServletPath().replace("opa/", ""));
		return super.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processCreateOPA(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		if (ControllerTk.isPathContainModuleAndId(ManageProjectController.PATH_CREATE_OPA, path)){
			int id = ControllerTk.getLastIdInPath(path);
			String projectSavePath = ControllerTk.getServiceUri(PATH_OPA_SERVICE, ManageProjectController.PATH_PROJECT);
			
			DefaultController2<Project, Integer> projectController = ControllerRegistry2.getInstance().get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();
			
			if(saveProjectAsset(completedProject, projectSavePath) && saveDefectAsset(completedProject.getDefects())
					&& saveIssueAsset(completedProject.getIssues()) && saveRiskAsset(completedProject.getRisks())
					&& saveKnowledgeAsset(completedProject.getActivities())){
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			
			
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	private boolean saveProjectAsset(Project completedProject, String path) {
		ProjectAsset projectAsset = convertProjectToAsset(completedProject);
		String requestData = convertObjectToJSON(projectAsset);
		ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
		if (response.getStatusCode() == HttpStatus.OK) {
			return true;
		}
		return false;
	}
	
	public ProjectAsset convertProjectToAsset(Project completedProject) {
		return new ProjectAsset(completedProject.getId(), completedProject.getName(), completedProject.getDescription(),
				completedProject.getType().getId(), completedProject.getProjManager().getId(),
				completedProject.getStatus(), completedProject.getStartDate(), completedProject.getEndDate());
	}

	private boolean saveIssueAsset(List<IssueAsset> issues) {
		for (IssueAsset issue : issues) {
//			String requestData = convertObjectToJSON(issue);
//			String path = ControllerTk.getServiceUri(PATH_OPA_SERVICE, "/issue_asset"");
//			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
//			if (response.getStatusCode() != HttpStatus.OK) {
//				return false;
//			}
//			saveIssueComment(issue.getComments());
		}
		return true;
	}
	
	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			String requestData = convertObjectToJSON(comment);
			String path = ControllerTk.getServiceUri(PATH_OPA_SERVICE, "/comment");
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private boolean saveRiskAsset(List<RiskAsset> risks) {
//		for (RiskAsset risk : risks) {
//			String requestData = convertObjectToJSON(risk);
//			String path = ControllerTk.getServiceUri(PATH_OPA_SERVICE, "/risk_asset");
//			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
//			if (response.getStatusCode() != HttpStatus.OK) {
//				return false;
//			}
//		}
		return true;
	}
	
	private boolean saveDefectAsset(List<DefectAsset> defects) {
//		for (DefectAsset defect : defects) {
//			String requestData = convertObjectToJSON(defect);
//			String path = ControllerTk.getServiceUri(PATH_OPA_SERVICE, "/defect_asset");
//			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
//			if (response.getStatusCode() != HttpStatus.OK) {
//				return false;
//			}
//		}
		return true;
	}
	
	private boolean saveKnowledgeAsset(List<Activity> activities) {
		for (Activity activity : activities) {
			OpaKnowledgeAsset knowledgeAsset = convertActivityToOpaKnowledgeAsset(activity);
			String requestData = convertObjectToJSON(knowledgeAsset);
			String path = ControllerTk.getServiceUri(PATH_OPA_SERVICE, "/opa_knowledge_asset");
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private OpaKnowledgeAsset convertActivityToOpaKnowledgeAsset(Activity activity) {
		return new OpaKnowledgeAsset(activity.getId(), activity.getProject().getId(), activity.getName(),
				activity.getDescription(), activity.getStatus(), activity.getAttachment(), activity.getUserId(), activity.getType().getId());
	}

	

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();
	
		return gson.toJson(object);
	}

}
