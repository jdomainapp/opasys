package jda.app.opasys.project.controller;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import jda.app.opasys.common.model.Asset;
import jda.app.opasys.common.model.KnowledgeAsset;
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
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;

public class MaterialiseOPA {

	private String fileStoragePath;

	public MaterialiseOPA(String fileStoragePath) {
		super();
		this.fileStoragePath = fileStoragePath;
	}
	
	public MaterialiseOPA() {
	}

	public ResponseEntity<?> processGetLocalOPA(InterfaceController<OPAInterface, Integer> interfaceController,
			HttpServletRequest req) {
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE,
				req.getServletPath().replace(ManageProjectController.PATH_OPA, "")).replace("local/","");
		return interfaceController.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processGetSubtypeOPA(InterfaceController<OPAInterface, Integer> interfaceController,
			HttpServletRequest req) {
		String targetPath = ControllerTk.getServiceUri(OpaUrl.PATH_SUBTYPE_OPA_SERVICE,
				req.getServletPath().replace(ManageProjectController.PATH_GET_SUBTYPE_OPA, ""));
		return interfaceController.forwardRequest(targetPath, HttpMethod.resolve(req.getMethod()), null);
	}

	public ResponseEntity<?> processCreateOPA(InterfaceController<OPAInterface, Integer> interfaceController,
			String path) {
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

			List<KnowledgeElement> knowledgeElements = knowledgeReponse.getBody();

//			if (saveProjectAsset(completedProject, interfaceController)
//				&& saveActivityAsset(activities, interfaceController)
			if(materialiseSubtypeAsset(KnowledgeElement.class, knowledgeElements, interfaceController)) {
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	private boolean saveProjectAsset(Project completedProject,
			InterfaceController<OPAInterface, Integer> interfaceController) {
		ProjectAsset projectAsset = convertProjectToAsset(completedProject);
		String requestData = ControllerTk.convertObjectToJSON(projectAsset);
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

	private boolean saveActivityAsset(List<Activity> activities,
			InterfaceController<OPAInterface, Integer> interfaceController) {
		for (Activity activity : activities) {
			ActivityAsset activityAsset = convertActivityToActivityAsset(activity);
			String requestData = ControllerTk.convertObjectToJSON(activityAsset);
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

	private <T> boolean materialiseSubtypeAsset(Class<T> type, Collection<T> objects,
			InterfaceController<OPAInterface, Integer> interfaceController) {
		for (T objRaw : objects) {
			Asset assetObj = convertObjectToAsset(objRaw);
			String subTypeOPAUrl = getTypeUrl(objRaw.getClass());
			String localPath = ControllerTk.getServiceUri(OpaUrl.PATH_LOCAL_OPA_SERVICE, subTypeOPAUrl);
			if (assetObj == null) {
				return false;
			}else if(assetObj instanceof KnowledgeAsset) {
				if (!sendSubtypeAndFile(localPath, (KnowledgeAsset) assetObj, interfaceController)) {
					return false;
				}
			}else {

				if (!sendSubtypeWithoutFile(localPath, assetObj, interfaceController)) {
					return false;
				}
			}
			
			if (!materialiseAssocAssets(type, objRaw, interfaceController)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @effects return the KnowledgeElementAsset object that represents the
	 *          materialised version of <tt>obj</tt>
	 */
	private Asset convertObjectToAsset(Object obj) {
		if (obj instanceof Defect) {
			Defect subtypeObj = (Defect) obj;
			return new DefectAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId(), subtypeObj.getLevel(),
					subtypeObj.getSolution());
		} else if (obj instanceof Issue) {
			Issue subtypeObj = (Issue) obj;
			return new IssueAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId(), subtypeObj.getParentIssueId(),
					subtypeObj.getSummary(), subtypeObj.getIssueType(), subtypeObj.getPriority(),
					subtypeObj.getAssignee().getId(), subtypeObj.getCreateDate());
		} else if (obj instanceof Risk) {
			Risk subtypeObj = (Risk) obj;
			return new RiskAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId(), subtypeObj.getLevel(),
					subtypeObj.getOccurence(), subtypeObj.getImpact(), subtypeObj.getSolution());

		} else if (obj instanceof Plan) {
			Plan subtypeObj = (Plan) obj;
			return new PlanAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId());
		} else if (obj instanceof Config) {
			Config subtypeObj = (Config) obj;
			return new ConfigAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId());
		} else if (obj instanceof Metric) {
			Metric subtypeObj = (Metric) obj;
			return new MetricAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId());
		} else if (obj instanceof Finance) {
			Finance subtypeObj = (Finance) obj;
			return new FinanceAsset(subtypeObj.getId(), subtypeObj.getName(), subtypeObj.getDescription(),
					subtypeObj.getStatus(), subtypeObj.getAttachment(), subtypeObj.getProject().getId(),
					subtypeObj.getActivity().getId(), subtypeObj.getUserId());
		} else if (obj instanceof Comment) {
			Comment subtypeObj = (Comment) obj;
			return new CommentAsset(subtypeObj.getId(), subtypeObj.getCommentUser().getId(),
					new IssueAsset(subtypeObj.getIssue().getId()), subtypeObj.getComment(),
					subtypeObj.getCreateDate());
		}
		return null;
	}

	/**
	 * @effects
	 * 
	 * @version
	 * 
	 */
	private boolean materialiseAssocAssets(Class<?> type, Object obj,
			InterfaceController<OPAInterface, Integer> interfaceController) {
		// get all associated objects
		Map<Class, Collection> objsMap = getAssociatedObjects(type, obj);

		// materialise each group
		for (Entry<Class, Collection> entry : objsMap.entrySet()) {
			Class assocType = entry.getKey();
			materialiseSubtypeAsset(assocType, entry.getValue(), interfaceController);
		}
		return true;
	}

	/**
	 * @effects return Map of the associated objects of <tt>obj</tt>: key is the
	 *          associated object type and value is Collection of the associated
	 *          objects of <tt>obj</tt> of that type
	 */
	private Map<Class, Collection> getAssociatedObjects(Class<?> type, Object obj) {
		// todo: use reflection or if...else block
		Map<Class, Collection> objMap = new HashMap<>();
		if (obj instanceof Issue) {
			objMap.put(Comment.class, ((Issue) obj).getComments());
		}

		return objMap;
	}

	/**
	 * @effects return the service Url of <tt>assocType</tt>
	 */
	private String getTypeUrl(Class assocType) {
		return OpaUrl.opaUrls.get(assocType);
	}

	private boolean sendSubtypeAndFile(String opaUrl, KnowledgeAsset asset, InterfaceController<OPAInterface, Integer> interfaceController) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		if(asset.getAttachment()!=null && !asset.getAttachment().isEmpty()) {
			File localFile = new File(fileStoragePath + File.separator + asset.getAttachment());
			body.add("file", new FileSystemResource(localFile));
		}
		body.add("data", asset);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<OPAInterface> response = interfaceController.forwardRequest(opaUrl, requestEntity);
		return response.getStatusCode() == HttpStatus.OK;
	}

	private boolean sendSubtypeWithoutFile(String opaUrl, Asset asset, InterfaceController<OPAInterface, Integer> interfaceController) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("data", asset);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<OPAInterface> response = interfaceController.forwardRequest(opaUrl, requestEntity);
		return response.getStatusCode() == HttpStatus.OK;
	}

}
