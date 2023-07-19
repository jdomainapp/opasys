package jda.app.opasys.project.modules.asset;

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

import jda.app.opasys.common.model.KnowledgeAsset;
import jda.app.opasys.common.model.OPA;
import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.ActivityAsset;
import jda.app.opasys.project.modules.asset.model.DefectAsset;
import jda.app.opasys.project.modules.asset.model.LocalCommentAsset;
import jda.app.opasys.project.modules.asset.model.LocalCommonKnowledge;
import jda.app.opasys.project.modules.asset.model.LocalIssueAsset;
import jda.app.opasys.project.modules.asset.model.LocalKnowledgeAsset;
import jda.app.opasys.project.modules.asset.model.RiskAsset;
import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledge.model.CommonKnowledge;
import jda.app.opasys.project.modules.knowledge.model.Defect;
import jda.app.opasys.project.modules.knowledge.model.Issue;
import jda.app.opasys.project.modules.knowledge.model.Risk;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.project.model.ProjectAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;

@Controller
public class LocalKnowledgeAssetController extends InterfaceController<Integer, LocalKnowledgeAsset> {
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
			
			DefaultController<Project, Integer> projectController = ControllerRegistry.getInstance().get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();
			
//			if(saveProjectAsset(completedProject) && saveActivityAsset(completedProject.getActivities()) && saveDefectAsset(completedProject.getDefects())
//					&& saveIssueAsset(completedProject.getIssues()) && saveRiskAsset(completedProject.getRisks())
//					&& saveKCommonKnowledgeAsset(completedProject.getKnowledges())){
//				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
//			}
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
			
			
		}else {
			return ResponseEntity.badRequest().build();
		}
	}

	private boolean saveProjectAsset(Project completedProject) {
		ProjectAsset projectAsset = convertProjectToAsset(completedProject);
		String requestData = convertObjectToJSON(projectAsset);
		String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, ManageProjectController.PATH_PROJECT);
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
	
	private boolean saveActivityAsset(List<Activity> activities) {
		for (Activity activity : activities) {
			ActivityAsset activityAsset = convertActivityToActivityAsset(activity);
			String requestData = convertObjectToJSON(activityAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
					ManageProjectController.PATH_ACTIVITY);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private ActivityAsset convertActivityToActivityAsset(Activity activity) {
		return new ActivityAsset(activity.getId(), activity.getName(), activity.getDescription(), activity.getUser().getId(),
				activity.getProject().getId());
	}

	private boolean saveIssueAsset(List<Issue> issues) {
		for (Issue issue : issues) {
			LocalIssueAsset issueAsset = convertIssueToIssueAsset(issue);
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
	
	private LocalIssueAsset convertIssueToIssueAsset(Issue issue) {
		return new LocalIssueAsset(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(), issue.getAttachment(), 
				issue.getUserId(), issue.getParentIssueId(), issue.getSummary(), issue.getIssueType(), issue.getPriority(), 
				issue.getProjectId(), issue.getActivityId(), issue.getAssignee().getId(), issue.getCreateDate());
	}
	
	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			LocalCommentAsset commentAsset = convertCommentToCommentAsset(comment);
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
	
	private LocalCommentAsset convertCommentToCommentAsset(Comment comment) {
		return new LocalCommentAsset(comment.getId(), comment.getCommentUser().getId(), new LocalIssueAsset(comment.getIssue().getId()), 
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
				risk.getProjectId(), risk.getActivityId(),risk.getUserId(), risk.getLevel(), risk.getOccurence(), risk.getImpact(), risk.getSolution());
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
				defect.getUserId(), defect.getProjectId(), defect.getActivityId(),defect.getLevel(), defect.getSolution());
	}
	
	private boolean saveKCommonKnowledgeAsset(List<CommonKnowledge> knowledges) {
		for (CommonKnowledge knowledge : knowledges) {
			LocalCommonKnowledge knowledgeAsset = convertKnowledgeToLocalOpaKnowledgeAsset(knowledge);
			String requestData = convertObjectToJSON(knowledgeAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_COMMON_KNOWLEDGE_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}
	
	private LocalCommonKnowledge convertKnowledgeToLocalOpaKnowledgeAsset(CommonKnowledge commonKnowledge) {
		return new LocalCommonKnowledge(commonKnowledge.getId(), commonKnowledge.getProjectId(), commonKnowledge.getActivityId(),commonKnowledge.getName(),
				commonKnowledge.getDescription(), commonKnowledge.getStatus(), commonKnowledge.getAttachment(), commonKnowledge.getUserId(), commonKnowledge.getType());
	}

	

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();
	
		return gson.toJson(object);
	}


}





