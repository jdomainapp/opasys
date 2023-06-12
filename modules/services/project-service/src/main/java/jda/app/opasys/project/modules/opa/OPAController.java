package jda.app.opasys.project.modules.opa;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.LocalKnowledgeAsset;
import jda.app.opasys.project.modules.defect.model.Defect;
import jda.app.opasys.project.modules.defect.model.DefectAsset;
import jda.app.opasys.project.modules.issue.model.Comment;
import jda.app.opasys.project.modules.issue.model.CommentAsset;
import jda.app.opasys.project.modules.issue.model.Issue;
import jda.app.opasys.project.modules.issue.model.IssueAsset;
import jda.app.opasys.project.modules.opa.model.OPA2;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.project.model.ProjectAsset;
import jda.app.opasys.project.modules.risk.model.Risk;
import jda.app.opasys.project.modules.risk.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerRegistry2;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;
import jda.modules.msacommon.controller.InterfaceController;

@Controller
public class OPAController extends InterfaceController<Integer, OPA> {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		if (req.getMethod().equals(HttpMethod.GET.toString())) {
			return processGetLocalOPA(req, res);
		} else if (req.getMethod().equals(HttpMethod.POST.toString())) {
			return processCreateLocalOPA(req, res);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}
	

	public ResponseEntity<?> processGetLocalOPA(HttpServletRequest req, HttpServletResponse res) {
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, req.getServletPath().replace("local_opa/", "").replace("opa/", ""));
		return super.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processCreateLocalOPA(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		if (ControllerTk.isPathContainModuleAndId(ManageProjectController.PATH_CREATE_LOCAL_OPA, path)){
			int id = ControllerTk.getLastIdInPath(path);
			String projectSavePath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, ManageProjectController.PATH_PROJECT);
			
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

	private boolean saveIssueAsset(List<Issue> issues) {
		for (Issue issue : issues) {
			IssueAsset issueAsset = convertIssueToIssueAsset(issue);
			String requestData = convertObjectToJSON(issueAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_ISSUE_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			saveIssueComment(issue.getComments());
		}
		return true;
	}
	
	private IssueAsset convertIssueToIssueAsset(Issue issue) {
		return new IssueAsset(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(), issue.getAttachment(), 
				issue.getUserId(), issue.getParentIssueId(), issue.getSummary(), issue.getType(), issue.getPriority(), 
				issue.getProject().getId(), issue.getAssignee().getId(), issue.getCreateDate());
	}
	
	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			CommentAsset commentAsset = convertCommentToCommentAsset(comment);
			String requestData = convertObjectToJSON(commentAsset);
			String modulePath = String.format(OpaUrl.PATH_OPA_ISSUE_COMMENT_ASSET, ""+comment.getIssue().getId());
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, modulePath);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private CommentAsset convertCommentToCommentAsset(Comment comment) {
		return new CommentAsset(comment.getId(), comment.getCommentUser().getId(), new IssueAsset(comment.getIssue().getId()), 
				comment.getTitle(), comment.getComment(), comment.getCreateDate());
	}
	
	private boolean saveRiskAsset(List<Risk> risks) {
		for (Risk risk : risks) {
			RiskAsset riskAsset = convertRiskToRiskAsset(risk);
			String requestData = convertObjectToJSON(riskAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_RISK_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private RiskAsset convertRiskToRiskAsset(Risk risk) {
		return new RiskAsset(risk.getId(), risk.getName(), risk.getDescription(), risk.getStatus(), risk.getAttachment(), 
				risk.getProject().getId(), risk.getUserId(), risk.getLevel(), risk.getOccurence(), risk.getImpact(), risk.getSolution());
	}
	
	private boolean saveDefectAsset(List<Defect> defects) {
		for (Defect defect : defects) {
			DefectAsset defectAsset = convertDefectToDefecAsset(defect);
			String requestData = convertObjectToJSON(defectAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_DEFECT_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private DefectAsset convertDefectToDefecAsset(Defect defect) {
		return new DefectAsset(defect.getId(), defect.getName(), defect.getDescription(), defect.getStatus(), defect.getAttachment(), 
				defect.getUserId(), defect.getProject().getId(), defect.getLevel(), defect.getSolution());
	}
	
	private boolean saveKnowledgeAsset(List<Activity> activities) {
		for (Activity activity : activities) {
			LocalKnowledgeAsset knowledgeAsset = convertActivityToLocalOpaKnowledgeAsset(activity);
			String requestData = convertObjectToJSON(knowledgeAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_KNOWLEDGE_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private LocalKnowledgeAsset convertActivityToLocalOpaKnowledgeAsset(Activity activity) {
		return new LocalKnowledgeAsset(activity.getId(), activity.getProject().getId(), activity.getName(),
				activity.getDescription(), activity.getStatus(), activity.getAttachment(), activity.getUserId(), activity.getType().getId());
	}

	

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();
	
		return gson.toJson(object);
	}


}
