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
import jda.app.opasys.project.modules.asset.model.CommentAsset;
import jda.app.opasys.project.modules.asset.model.DefectAsset;
import jda.app.opasys.project.modules.asset.model.IssueAsset;
import jda.app.opasys.project.modules.asset.model.CommonKnowledgeAsset;
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
public class KnowledgeAssetController extends InterfaceController<Integer, KnowledgeAsset> {
	private static final Logger logger = LoggerFactory.getLogger(InterfaceController.class);

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
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE,
				req.getServletPath().replace("opa/", ""));
		return super.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processCreateOPA(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		if (ControllerTk.isPathContainModuleAndId(ManageProjectController.PATH_CREATE_OPA, path)) {
			int id = ControllerTk.getLastIdInPath(path);


			DefaultController<Project, Integer> projectController = ControllerRegistry.getInstance()
					.get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();

//			if (saveProjectAsset(completedProject) && saveActivityAsset(completedProject.getActivities()) && saveDefectAsset(completedProject.getDefects())
//					&& saveIssueAsset(completedProject.getIssues()) && saveRiskAsset(completedProject.getRisks())
//					&& saveKnowledgeAsset(completedProject.getKnowledges())) {
//				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
//			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	private boolean saveProjectAsset(Project completedProject) {
		ProjectAsset projectAsset = convertProjectToAsset(completedProject);
		String requestData = convertObjectToJSON(projectAsset);
		String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
				ManageProjectController.PATH_PROJECT);
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
			IssueAsset issueAsset = convertIssueToIssueAsset(issue);
			String requestData = convertObjectToJSON(issueAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE,
					OpaUrl.PATH_ISSUE_SERVICE);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			saveIssueComment(issue.getComments());
		}
		return true;
	}

	private IssueAsset convertIssueToIssueAsset(Issue issue) {
		return new IssueAsset(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(),
				issue.getAttachment(), issue.getUserId(), issue.getParentIssueId(), issue.getSummary(), issue.getIssueType(),
				issue.getPriority(), issue.getProjectId(), issue.getActivityId(), issue.getAssignee(), issue.getCreateDate());
	}

	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			CommentAsset commentAsset = convertCommentToCommentAsset(comment);
			String requestData = convertObjectToJSON(commentAsset);
			String modulePath = String.format(OpaUrl.PATH_ISSUE_SERVICE_COMMENT,
					"" + comment.getIssue().getId());
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE, modulePath);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private CommentAsset convertCommentToCommentAsset(Comment comment) {
		return new CommentAsset(comment.getId(), comment.getCommentUser(),
				new IssueAsset(comment.getIssue().getId()), comment.getTitle(), comment.getComment(),
				comment.getCreateDate());
	}

	private boolean saveRiskAsset(List<Risk> risks) {
		for (Risk risk : risks) {
			RiskAsset riskAsset = convertRiskToRiskAsset(risk);
			String requestData = convertObjectToJSON(riskAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE,
					OpaUrl.PATH_RISK_SERVICE);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private RiskAsset convertRiskToRiskAsset(Risk risk) {
		return new RiskAsset(risk.getId(), risk.getName(), risk.getDescription(), risk.getStatus(),
				risk.getAttachment(), risk.getProjectId(), risk.getActivityId(), risk.getUserId(), risk.getLevel(), risk.getOccurence(),
				risk.getImpact(), risk.getSolution());
	}

	private boolean saveDefectAsset(List<Defect> defects) {
		for (Defect defect : defects) {
			DefectAsset defectAsset = convertDefectToDefecAsset(defect);
			String requestData = convertObjectToJSON(defectAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE,
					OpaUrl.PATH_DEFECT_SERVICE);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private DefectAsset convertDefectToDefecAsset(Defect defect) {
		return new DefectAsset(defect.getId(), defect.getName(), defect.getDescription(), defect.getStatus(),
				defect.getAttachment(), defect.getUserId(), defect.getProjectId(), defect.getActivityId(), defect.getLevel(),
				defect.getSolution());
	}

	private boolean saveCommonKnowledgeAsset(List<CommonKnowledge> commonKnowledges) {
		for (CommonKnowledge commonknowledge : commonKnowledges) {
			CommonKnowledgeAsset commonknowledgeAsset = convertCommonKnowledgeToAsset(commonknowledge);
			String requestData = convertObjectToJSON(commonknowledgeAsset);
			String modulePath = "/config";
			switch (commonknowledge.getCommonKnowledgeType()) {
			case "Plan":
				modulePath = "/plan";
				break;
			case "Metric":
				modulePath = "/metric";
				break;
			case "Conf":
				modulePath = "/config";
				break;
			case "Fin":
				modulePath = "/finance";
				break;
			default:
				break;
			}
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE,
					OpaUrl.PATH_COMMON_KNOWLEDGE_SERVICE + modulePath);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private CommonKnowledgeAsset convertCommonKnowledgeToAsset(CommonKnowledge knowledge) {
		return new CommonKnowledgeAsset(knowledge.getId(), knowledge.getProjectId(), knowledge.getActivityId(), knowledge.getName(),
				knowledge.getDescription(), knowledge.getStatus(), knowledge.getAttachment(), knowledge.getUserId());
	}

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();

		return gson.toJson(object);
	}

}





