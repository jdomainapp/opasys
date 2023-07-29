package jda.app.opasys.project.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;

import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.activity.model.ActivityAsset;
import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledgeelement.model.Config;
import jda.app.opasys.project.modules.knowledgeelement.model.Defect;
import jda.app.opasys.project.modules.knowledgeelement.model.Finance;
import jda.app.opasys.project.modules.knowledgeelement.model.Issue;
import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;
import jda.app.opasys.project.modules.knowledgeelement.model.Metric;
import jda.app.opasys.project.modules.knowledgeelement.model.Plan;
import jda.app.opasys.project.modules.knowledgeelement.model.Risk;
import jda.app.opasys.project.modules.opainterface.OpaUrl;
import jda.app.opasys.project.modules.opainterface.modelasset.CommentAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.ConfigAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.DefectAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.FinanceAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.IssueAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.MetricAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.OPAInterface;
import jda.app.opasys.project.modules.opainterface.modelasset.PlanAsset;
import jda.app.opasys.project.modules.opainterface.modelasset.RiskAsset;
import jda.app.opasys.project.modules.project.model.Project;
import jda.app.opasys.project.modules.project.model.ProjectAsset;
import jda.modules.common.exceptions.NotImplementedException;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;

@Component
public class MaterialiseOPA {
	
	
	@Value("${filestorage.path}")
	private String fileStoragePath="/home/vietdo/opasys_storage_temp";
	
	public ResponseEntity<?> processGetLocalOPA(InterfaceController<OPAInterface, Integer>  interfaceController, HttpServletRequest req) {
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
				req.getServletPath().replace(ManageProjectController.PATH_GET_LOCAL_OPA, ""));
		return interfaceController.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}
	
	public ResponseEntity<?> processGetSubtypeOPA(InterfaceController<OPAInterface, Integer>  interfaceController, HttpServletRequest req) {
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE,
				req.getServletPath().replace(ManageProjectController.PATH_GET_SUBTYPE_OPA, ""));
		return interfaceController.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}
	
	public ResponseEntity<?> processCreateOPA(InterfaceController<OPAInterface, Integer>  interfaceController, String path) {
		if (ControllerTk.isPathContainModuleAndId(ManageProjectController.PATH_CREATE_OPA, path)) {
			int id = ControllerTk.getLastIdInPath(path);

			DefaultController<Project, Integer> projectController = ControllerRegistry.getInstance().get(Project.class);
			Project completedProject = projectController.getEntityById(id).getBody();
			DefaultController<Activity, Integer> activityController = ControllerRegistry.getInstance()
					.get(Activity.class);
			List<Activity> activities = (List<Activity>) activityController
					.getDataByPropertyName("projectId", completedProject.getId()).getBody();
			DefaultController<KnowledgeElement, Integer> knowledgeController = ControllerRegistry.getInstance()
					.get(KnowledgeElement.class);
			ResponseEntity<List<KnowledgeElement>> knowledgeReponse = (ResponseEntity<List<KnowledgeElement>>) knowledgeController
					.getDataByPropertyName("projectId", completedProject.getId());

			//todo: call materialiseSubtypeAsset() instead of calling each individual method
			
			List<KnowledgeElement> knowledgeElements = knowledgeReponse.getBody();
			List<Defect> defects = new ArrayList<>();
			List<Issue> issues = new ArrayList<>();
			List<Risk> risks = new ArrayList<>();
			List<Plan> plans = new ArrayList<>();
			List<Config> configs = new ArrayList<>();
			List<Metric> metrics = new ArrayList<>();
			List<Finance> finances = new ArrayList<>();
			for (KnowledgeElement knowledgeElement : knowledgeElements) {
				if (knowledgeElement instanceof Defect) {
					defects.add((Defect) knowledgeElement);
				}else if (knowledgeElement instanceof Issue) {
					issues.add((Issue) knowledgeElement);
				}else if (knowledgeElement instanceof Risk) {
					risks.add((Risk) knowledgeElement);
				}else if (knowledgeElement instanceof Plan) {
					plans.add((Plan) knowledgeElement);
				}else if (knowledgeElement instanceof Config) {
					configs.add((Config) knowledgeElement);
				}else if (knowledgeElement instanceof Metric) {
					metrics.add((Metric) knowledgeElement);
				}else if (knowledgeElement instanceof Finance) {
					finances.add((Finance) knowledgeElement);
				}
			}

			if (
//					saveProjectAsset(completedProject, interfaceController) 
//					&& saveActivityAsset(activities, interfaceController) 
//					&& saveDefectAsset(defects, interfaceController)
//					&& saveIssueAsset(issues, interfaceController)
//					&& saveRiskAsset(risks, interfaceController)
					savePlanAsset(plans, interfaceController)) 
//					&& saveConfigAsset(configs, interfaceController)
//					&& saveMetricAsset(metrics, interfaceController)
//					&& saveFinanceAsset(finances, interfaceController)) 
			{
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 

		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	private boolean saveProjectAsset(Project completedProject, InterfaceController<OPAInterface, Integer>  interfaceController) {
		ProjectAsset projectAsset = convertProjectToAsset(completedProject);
		String requestData = convertObjectToJSON(projectAsset);
		String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, ManageProjectController.PATH_PROJECT);
		ResponseEntity<?> response = interfaceController.forwardRequest(path, HttpMethod.POST, requestData);
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

	private boolean saveActivityAsset(List<Activity> activities, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Activity activity : activities) {
			ActivityAsset activityAsset = convertActivityToActivityAsset(activity);
			String requestData = convertObjectToJSON(activityAsset);
			String path = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
					ManageProjectController.PATH_ACTIVITY);
			ResponseEntity<?> response = interfaceController.forwardRequest(path, HttpMethod.POST, requestData);
			if (response.getStatusCode() == HttpStatus.OK) {
				return true;
			}
		}
		return false;
	}

	private ActivityAsset convertActivityToActivityAsset(Activity activity) {
		return new ActivityAsset(activity.getId(), activity.getName(), activity.getDescription(),
				activity.getUser().getId(), activity.getProject().getId());
	}

	private <T> boolean materialiseSubtypeAsset(Class<T> type, Collection<T> objects, String subTypeOPAUrl, InterfaceController<OPAInterface, Integer>  interfaceController) {
    for (T objRaw : objects) {
      KnowledgeElement obj = (KnowledgeElement) objRaw;
      KnowledgeElementAsset assetObj = convertObjectToAsset(obj);
      String requestData = convertObjectToJSON(assetObj);
      
      // TODO: refactor 
      String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, subTypeOPAUrl);
      ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
      if (response.getStatusCode() != HttpStatus.OK) {
        return false;
      }
      
//      String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_ISSUE_SERVICE);
//      response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
//      if (response.getStatusCode() != HttpStatus.OK) {
//        return false;
//      }
      
      materialiseAssocAssets(type, obj, interfaceController);
//      if (!saveIssueComment(issue.getComments(), interfaceController)){
//        return false;
//      }
      
      return saveFileToAssetStorage(obj.getId(), obj.getAttachment(), interfaceController);
    }
    return true;
  }
	
	/**
   * @effects 
   *  return the KnowledgeElementAsset object that represents the materialised version of <tt>obj</tt>
   */
  private KnowledgeElementAsset convertObjectToAsset(KnowledgeElement obj) {
    // todo: generalise from other convertX() methods 
    throw new NotImplementedException("Not yet implemented");
  }

  /**
   * @effects 
   * 
   * @version 
   * 
   */
  private void materialiseAssocAssets(Class<?> type, Object obj, 
      InterfaceController<OPAInterface, Integer> interfaceController) {
    // get all associated objects
    Map<Class, Collection> objsMap = getAssociatedObjects(type, obj);
    
    // materialise each group
    for (Entry<Class, Collection> entry: objsMap.entrySet()) {
      Class assocType = entry.getKey();
      String assocTypeUrl = getTypeUrl(assocType);
      materialiseSubtypeAsset(assocType, entry.getValue(), assocTypeUrl, interfaceController);
    }
  }

  /**
   * @effects 
   *  return Map of the associated objects of <tt>obj</tt>: 
   *    key is the associated object type and value is Collection of the associated objects of <tt>obj</tt> of that type
   */
  private Map<Class, Collection> getAssociatedObjects(Class<?> type,
      Object obj) {
    // todo: use reflection or if...else block
    Map<Class, Collection> objMap = new HashMap<>();
    if (obj instanceof Defect) {
      // todo
      throw new NotImplementedException("Not yet implemented");
    }else if (obj instanceof Issue) {
      // todo
      objMap.put(Comment.class, ((Issue)obj).getComments());
    }else if (obj instanceof Risk) {
      // todo
      throw new NotImplementedException("Not yet implemented");

    }else if (obj instanceof Plan) {
      // todo
      throw new NotImplementedException("Not yet implemented");
    }else if (obj instanceof Config) {
      // todo
      throw new NotImplementedException("Not yet implemented");
    }else if (obj instanceof Metric) {
      // todo
      throw new NotImplementedException("Not yet implemented");
    }else if (obj instanceof Finance) {
      // todo
      throw new NotImplementedException("Not yet implemented");
    }
    
    return objMap;
  }

  /**
   * @effects 
   *  return the service Url of <tt>assocType</tt>
   */
  private String getTypeUrl(Class assocType) {
    return OpaUrl.opaUrls.get(assocType);
  }

  private boolean saveIssueAsset(List<Issue> issues, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Issue issue : issues) {
			IssueAsset issueAsset = convertIssueToIssueAsset(issue);
			String requestData = convertObjectToJSON(issueAsset);
			
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_ISSUE_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_ISSUE_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			if (!saveIssueComment(issue.getComments(), interfaceController)){
				return false;
			}
			
			return saveFileToAssetStorage(issue.getId(), issue.getAttachment(), interfaceController);
		}
		return true;
	}

	private IssueAsset convertIssueToIssueAsset(Issue issue) {
		return new IssueAsset(issue.getId(), issue.getName(), issue.getDescription(), issue.getStatus(),
				issue.getAttachment(), issue.getUserId(), issue.getParentIssueId(), issue.getSummary(),
				issue.getIssueType(), issue.getPriority(), issue.getProject().getId(), issue.getActivity().getId(),
				issue.getAssignee().getId(), issue.getCreateDate());
	}

	private boolean saveIssueComment(List<Comment> comments, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Comment comment : comments) {
			CommentAsset commentAsset = convertCommentToCommentAsset(comment);
			String requestData = convertObjectToJSON(commentAsset);
			
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_ISSUE_COMMENT_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_ISSUE_SERVICE_COMMENT);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
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

	private boolean saveRiskAsset(List<Risk> risks, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Risk risk : risks) {
			RiskAsset riskAsset = convertRiskToRiskAsset(risk);
			String requestData = convertObjectToJSON(riskAsset);
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_RISK_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_RISK_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}

			return saveFileToAssetStorage(risk.getId(), risk.getAttachment(), interfaceController);
		}
		return true;
	}

	private RiskAsset convertRiskToRiskAsset(Risk risk) {
		return new RiskAsset(risk.getId(), risk.getName(), risk.getDescription(), risk.getStatus(),
				risk.getAttachment(), risk.getProject().getId(), risk.getActivity().getId(), risk.getUserId(), risk.getLevel(),
				risk.getOccurence(), risk.getImpact(), risk.getSolution());
	}

	private boolean saveDefectAsset(List<Defect> defects, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Defect defect : defects) {
			DefectAsset defectAsset = convertDefectToDefecAsset(defect);
			String requestData = convertObjectToJSON(defectAsset);
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_DEFECT_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_DEFECT_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}

			return saveFileToAssetStorage(defect.getId(), defect.getAttachment(), interfaceController);
		}
		return true;
	}

	private DefectAsset convertDefectToDefecAsset(Defect defect) {
		return new DefectAsset(defect.getId(), defect.getName(), defect.getDescription(), defect.getStatus(),
				defect.getAttachment(), defect.getUserId(), defect.getProject().getId(), defect.getActivity().getId(),
				defect.getLevel(), defect.getSolution());
	}

	private boolean savePlanAsset(List<Plan> plans, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Plan plan : plans) {
//			PlanAsset planAsset = convertPlanToPlanAsset(plan);
//			String requestData = convertObjectToJSON(planAsset);
//			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_PLAN_ASSET);
//			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
//			if (response.getStatusCode() != HttpStatus.OK) {
//				return false;
//			}
//			
//			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_PLAN_SERVICE);
//			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
//			if (response.getStatusCode() != HttpStatus.OK) {
//				return false;
//			}

			return saveFileToAssetStorage(plan.getId(), plan.getAttachment(), interfaceController);
		}
		return true;
	}

	private PlanAsset convertPlanToPlanAsset(Plan plan) {
		return new PlanAsset(plan.getId(), plan.getProject().getId(), plan.getActivity().getId(), plan.getName(),
				plan.getDescription(), plan.getStatus(), plan.getAttachment(), plan.getUserId());
	}
	
	private boolean saveMetricAsset(List<Metric> metrics, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Metric metric : metrics) {
			MetricAsset metricAsset = convertMetricToMetricAsset(metric);
			String requestData = convertObjectToJSON(metricAsset);
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_METRIC_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_METRIC_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}

			return saveFileToAssetStorage(metric.getId(), metric.getAttachment(), interfaceController);
		}
		return true;
	}

	private MetricAsset convertMetricToMetricAsset(Metric metric) {
		return new MetricAsset(metric.getId(), metric.getProject().getId(), metric.getActivity().getId(), metric.getName(),
				metric.getDescription(), metric.getStatus(), metric.getAttachment(), metric.getUserId());
	}
	
	private boolean saveConfigAsset(List<Config> configs, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Config config : configs) {
			ConfigAsset configAsset = convertConfigToConfigAsset(config);
			String requestData = convertObjectToJSON(configAsset);
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_CONFIG_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_CONFIG_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}

			return saveFileToAssetStorage(config.getId(), config.getAttachment(), interfaceController);
		}
		return true;
	}

	private ConfigAsset convertConfigToConfigAsset(Config config) {
		return new ConfigAsset(config.getId(), config.getProject().getId(), config.getActivity().getId(), config.getName(),
				config.getDescription(), config.getStatus(), config.getAttachment(), config.getUserId());
	}
	
	private boolean saveFinanceAsset(List<Finance> finances, InterfaceController<OPAInterface, Integer>  interfaceController) {
		for (Finance finance : finances) {
			FinanceAsset financeAsset = convertFinanceToFinanceAsset(finance);
			String requestData = convertObjectToJSON(financeAsset);
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, OpaUrl.PATH_OPA_FINANCE_ASSET);
			ResponseEntity<?> response = interfaceController.forwardRequest(localPath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}
			
			String subtypePath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE, OpaUrl.PATH_FINANCE_SERVICE);
			response = interfaceController.forwardRequest(subtypePath, HttpMethod.POST, requestData);
			if (response.getStatusCode() != HttpStatus.OK) {
				return false;
			}

			return saveFileToAssetStorage(finance.getId(), finance.getAttachment(), interfaceController);
		}
		return true;
	}

	private FinanceAsset convertFinanceToFinanceAsset(Finance fin) {
		return new FinanceAsset(fin.getId(), fin.getProject().getId(), fin.getActivity().getId(), fin.getName(),
				fin.getDescription(), fin.getStatus(), fin.getAttachment(), fin.getUserId());
	}

	private String convertObjectToJSON(Object object) {
		Gson gson = new Gson();

		return gson.toJson(object);
	}
	
	private boolean saveFileToAssetStorage(int id, String fileName, InterfaceController<OPAInterface, Integer>  interfaceController) {
		if(fileName.isEmpty()) {
			return true;
		}
		String storagePath = ControllerTk.getServiceUri(OpaUrl.PATH_ASSET_STORAGE_SERVICE, "");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap <String, Object> body = new LinkedMultiValueMap<>();
		File localFile = new File(fileStoragePath+File.separator+fileName);
		body.add("file", new FileSystemResource(localFile));
		body.add("id", id);
		body.add("name", fileName);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		
		ResponseEntity<OPAInterface> response = interfaceController.forwardRequest(storagePath, requestEntity);
		return response.getStatusCode() == HttpStatus.OK ? true : false;
	}

}
