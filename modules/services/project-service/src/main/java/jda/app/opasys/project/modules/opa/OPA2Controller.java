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
import jda.app.opasys.project.modules.activity.model.OpaKnowledgeAsset;
import jda.app.opasys.project.modules.defect.model.Defect;
import jda.app.opasys.project.modules.defect.model.DefectAsset;
import jda.app.opasys.project.modules.issue.model.Comment;
import jda.app.opasys.project.modules.issue.model.CommentAsset;
import jda.app.opasys.project.modules.issue.model.CommentAsset2;
import jda.app.opasys.project.modules.issue.model.Issue;
import jda.app.opasys.project.modules.issue.model.IssueAsset2;
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
public class OPA2Controller extends InterfaceController<Integer, OPA2> {
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
			String projectSavePath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
					ManageProjectController.PATH_PROJECT);

			DefaultController2<Project, Integer> projectController = ControllerRegistry2.getInstance()
					.get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();

			if (saveProjectAsset(completedProject, projectSavePath) && saveDefectAsset(completedProject.getDefects())
					&& saveIssueAsset(completedProject.getIssues()) && saveRiskAsset(completedProject.getRisks())
					&& saveKnowledgeAsset(completedProject.getActivities())) {
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		} else {
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
			IssueAsset2 issueAsset = convertIssueToIssueAsset(issue);
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

	private IssueAsset2 convertIssueToIssueAsset(Issue issue) {
		return new IssueAsset2(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(),
				issue.getAttachment(), issue.getUserId(), issue.getParentIssueId(), issue.getSummary(), issue.getType(),
				issue.getPriority(), issue.getProject().getId(), issue.getAssignee(), issue.getCreateDate());
	}

	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			CommentAsset2 commentAsset = convertCommentToCommentAsset(comment);
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

	private CommentAsset2 convertCommentToCommentAsset(Comment comment) {
		return new CommentAsset2(comment.getId(), comment.getCommentUser(),
				new IssueAsset2(comment.getIssue().getId()), comment.getTitle(), comment.getComment(),
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
				risk.getAttachment(), risk.getProject().getId(), risk.getUserId(), risk.getLevel(), risk.getOccurence(),
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
				defect.getAttachment(), defect.getUserId(), defect.getProject().getId(), defect.getLevel(),
				defect.getSolution());
	}

	private boolean saveKnowledgeAsset(List<Activity> activities) {
		for (Activity activity : activities) {
			OpaKnowledgeAsset knowledgeAsset = convertActivityToOpaKnowledgeAsset(activity);
			String requestData = convertObjectToJSON(knowledgeAsset);
			String modulePath = "/config";
			switch (activity.getType().getName()) {
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
					OpaUrl.PATH_KNOWLEDGE_SERVICE + modulePath);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private OpaKnowledgeAsset convertActivityToOpaKnowledgeAsset(Activity activity) {
		return new OpaKnowledgeAsset(activity.getId(), activity.getProject().getId(), activity.getName(),
				activity.getDescription(), activity.getStatus(), activity.getAttachment(), activity.getUserId());
	}

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();

		return gson.toJson(object);
	}

}
