package jda.app.opasys.project.modules.asset;

import java.util.ArrayList;
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

import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.ActivityAsset;
import jda.app.opasys.project.modules.asset.model.CommentAsset;
import jda.app.opasys.project.modules.asset.model.ConfigAsset;
import jda.app.opasys.project.modules.asset.model.DefectAsset;
import jda.app.opasys.project.modules.asset.model.FinanceAsset;
import jda.app.opasys.project.modules.asset.model.IssueAsset;
import jda.app.opasys.project.modules.asset.model.LocalIssueAsset;
import jda.app.opasys.project.modules.asset.model.LocalKnowledgeAsset;
import jda.app.opasys.project.modules.asset.model.MetricAsset;
import jda.app.opasys.project.modules.asset.model.PlanAsset;
import jda.app.opasys.project.modules.asset.model.RiskAsset;
import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledge.model.Plan;
import jda.app.opasys.project.modules.knowledge.model.Config;
import jda.app.opasys.project.modules.knowledge.model.Defect;
import jda.app.opasys.project.modules.knowledge.model.Finance;
import jda.app.opasys.project.modules.knowledge.model.Issue;
import jda.app.opasys.project.modules.knowledge.model.Knowledge;
import jda.app.opasys.project.modules.knowledge.model.Metric;
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
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
				req.getServletPath().replace("local_opa/", "").replace("opa/", ""));
		return super.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processCreateLocalOPA(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		if (ControllerTk.isPathContainModuleAndId(ManageProjectController.PATH_CREATE_LOCAL_OPA, path)) {
			int id = ControllerTk.getLastIdInPath(path);

			DefaultController<Project, Integer> projectController = ControllerRegistry.getInstance().get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();
			DefaultController<Activity, Integer> activityController = ControllerRegistry.getInstance()
					.get(Activity.class);
			List<Activity> activities = (List<Activity>) activityController
					.getDataByPropertyName("projectId", completedProject.getId()).getBody();
			DefaultController<Knowledge, Integer> knowledgeController = ControllerRegistry.getInstance()
					.get(Knowledge.class);
			ResponseEntity<List<Knowledge>> knowledgeReponse = (ResponseEntity<List<Knowledge>>) knowledgeController
					.getDataByPropertyName("projectId", completedProject.getId());
			List<Knowledge> knowledges = knowledgeReponse.getBody();
			List<Defect> defects = new ArrayList<>();
			List<Issue> issues = new ArrayList<>();
			List<Risk> risks = new ArrayList<>();
			List<Plan> plans = new ArrayList<>();
			List<Config> configs = new ArrayList<>();
			List<Metric> metrics = new ArrayList<>();
			List<Finance> finances = new ArrayList<>();
			for (Knowledge knowledge : knowledges) {
				if (knowledge instanceof Defect) {
					defects.add((Defect) knowledge);
				}else if (knowledge instanceof Issue) {
					issues.add((Issue) knowledge);
				}else if (knowledge instanceof Risk) {
					risks.add((Risk) knowledge);
				}else if (knowledge instanceof Plan) {
					plans.add((Plan) knowledge);
				}else if (knowledge instanceof Config) {
					configs.add((Config) knowledge);
				}else if (knowledge instanceof Metric) {
					metrics.add((Metric) knowledge);
				}else if (knowledge instanceof Finance) {
					finances.add((Finance) knowledge);
				}
			}

			if (saveProjectAsset(completedProject) && saveActivityAsset(activities) && saveDefectAsset(defects)
					&& saveIssueAsset(issues) && saveRiskAsset(risks) && savePlanAsset(plans) && saveConfigAsset(configs) && saveMetricAsset(metrics) && saveFinanceAsset(finances)) {
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		} else {
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
		return new ActivityAsset(activity.getId(), activity.getName(), activity.getDescription(),
				activity.getUser().getId(), activity.getProject().getId());
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
		return new IssueAsset(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(),
				issue.getAttachment(), issue.getUserId(), issue.getParentIssueId(), issue.getSummary(),
				issue.getIssueType(), issue.getPriority(), issue.getProjectId(), issue.getActivityId(),
				issue.getAssignee().getId(), issue.getCreateDate());
	}

	private boolean saveIssueComment(List<Comment> comments) {
		for (Comment comment : comments) {
			CommentAsset commentAsset = convertCommentToCommentAsset(comment);
			String requestData = convertObjectToJSON(commentAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_ISSUE_COMMENT_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private CommentAsset convertCommentToCommentAsset(Comment comment) {
		return new CommentAsset(comment.getId(), comment.getCommentUser().getId(),
				new IssueAsset(comment.getIssue().getId()), comment.getTitle(), comment.getComment(),
				comment.getCreateDate());
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
		return new RiskAsset(risk.getId(), risk.getName(), risk.getDescription(), risk.getStatus(),
				risk.getAttachment(), risk.getProjectId(), risk.getActivityId(), risk.getUserId(), risk.getLevel(),
				risk.getOccurence(), risk.getImpact(), risk.getSolution());
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
		return new DefectAsset(defect.getId(), defect.getName(), defect.getDescription(), defect.getStatus(),
				defect.getAttachment(), defect.getUserId(), defect.getProjectId(), defect.getActivityId(),
				defect.getLevel(), defect.getSolution());
	}

	private boolean savePlanAsset(List<Plan> plans) {
		for (Plan plan : plans) {
			PlanAsset planAsset = convertPlanToPlanAsset(plan);
			String requestData = convertObjectToJSON(planAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_PLAN_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private PlanAsset convertPlanToPlanAsset(Plan plan) {
		return new PlanAsset(plan.getId(), plan.getProjectId(), plan.getActivityId(), plan.getName(),
				plan.getDescription(), plan.getStatus(), plan.getAttachment(), plan.getUserId());
	}
	
	private boolean saveMetricAsset(List<Metric> metrics) {
		for (Metric metric : metrics) {
			MetricAsset metricAsset = convertMetricToMetricAsset(metric);
			String requestData = convertObjectToJSON(metricAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE, OpaUrl.PATH_METRIC_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private MetricAsset convertMetricToMetricAsset(Metric metric) {
		return new MetricAsset(metric.getId(), metric.getProjectId(), metric.getActivityId(), metric.getName(),
				metric.getDescription(), metric.getStatus(), metric.getAttachment(), metric.getUserId());
	}
	
	private boolean saveConfigAsset(List<Config> configs) {
		for (Config config : configs) {
			ConfigAsset configAsset = convertConfigToConfigAsset(config);
			String requestData = convertObjectToJSON(configAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE, OpaUrl.PATH_CONFIG_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private ConfigAsset convertConfigToConfigAsset(Config config) {
		return new ConfigAsset(config.getId(), config.getProjectId(), config.getActivityId(), config.getName(),
				config.getDescription(), config.getStatus(), config.getAttachment(), config.getUserId());
	}
	
	private boolean saveFinanceAsset(List<Finance> finances) {
		for (Finance finance : finances) {
			FinanceAsset planAsset = convertFinanceToFinanceAsset(finance);
			String requestData = convertObjectToJSON(planAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_OPA_SERVICE, OpaUrl.PATH_FINANCE_ASSET);
			ResponseEntity<?> response = forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
		}
		return true;
	}

	private FinanceAsset convertFinanceToFinanceAsset(Finance fin) {
		return new FinanceAsset(fin.getId(), fin.getProjectId(), fin.getActivityId(), fin.getName(),
				fin.getDescription(), fin.getStatus(), fin.getAttachment(), fin.getUserId());
	}

	public String convertObjectToJSON(Object object) {
		Gson gson = new Gson();

		return gson.toJson(object);
	}

}
