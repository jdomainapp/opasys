package jda.app.opasys.opa.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jda.app.opasys.opa.modules.issueasset.model.CommentAsset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jda.app.opasys.common.model.KnowledgeAsset;
import jda.app.opasys.common.model.OPA;
import jda.app.opasys.opa.modules.activityasset.model.ActivityAsset;
import jda.app.opasys.opa.modules.orgasset.model.OrgAsset;
import jda.app.opasys.opa.modules.projectasset.model.ProjectAsset;
import jda.modules.msacommon.connections.CachedBodyHttpServletRequest;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@RestController
@RequestMapping(value = "/")
public class ManageOPAController {
	public final static String PATH_PROJECT = "/project";
	public final static String PATH_ACTIVITY = "/activity";
	public final static String PATH_OPA_ORG_ASSET = "/org_asset";
	public final static String PATH_OPA_SUBTYPE = "/subtype";
	public final static String PATH_OPA_LOCAL = "/local";
	public final static String PATH_STORAGE_FOR_KNOWLEDGE_ASSET = "asset-storage-service/knowledgeasset";
	public final static String PATH_STORAGE_FOR_ORG_ASSET = "asset-storage-service/orgasset";
	
	@RequestMapping(value = PATH_OPA_ORG_ASSET + "/**",method = {RequestMethod.DELETE, RequestMethod.GET})
	public ResponseEntity<?> handleGetOrgAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_OPA_ORG_ASSET + "/**", method = {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<?> handleCreateOrgAsset(@RequestPart("data") String entity, @RequestPart("file") MultipartFile fileUpload, HttpServletRequest req, HttpServletResponse res) throws IOException {
		String storageUrl = ControllerTk.getServiceUri("", PATH_STORAGE_FOR_ORG_ASSET);
		OrgAsset orgAsset = (OrgAsset)new ObjectMapper().readValue(entity, OrgAsset.class);
		sendFile(storageUrl, orgAsset.getId(), orgAsset.getAttachment(), fileUpload);
		HttpServletRequest cloneRequest = new CachedBodyHttpServletRequest(req, entity.getBytes());
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(cloneRequest, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_PROJECT + "/**", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<?> handleProject(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ProjectAsset, Integer> controller = ControllerRegistry.getInstance().get(ProjectAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = PATH_ACTIVITY + "/**", method = {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<?> handleActivity(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ActivityAsset, Integer> controller = ControllerRegistry.getInstance().get(ActivityAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = PATH_OPA_LOCAL + "_**")
	public ResponseEntity<?> handleCreateAsset(@RequestPart("data") String entity, @RequestPart(value = "file", required = false) MultipartFile fileUpload, HttpServletRequest req, HttpServletResponse res ) throws IOException {
		String className = req.getServletPath().split("/")[1];
		Class<?> clazz = SubtypeUrls.opaUrls.get(className);
		HttpServletRequest cloneRequest = new CachedBodyHttpServletRequest(req, entity.getBytes());
		if(fileUpload!=null) {
			String storageUrl = ControllerTk.getServiceUri("", PATH_STORAGE_FOR_KNOWLEDGE_ASSET);
			KnowledgeAsset knowledgeAsset = (KnowledgeAsset) new ObjectMapper().readValue(entity, clazz);
			sendFile(storageUrl, knowledgeAsset.getId(), knowledgeAsset.getAttachment(), fileUpload);
		}
		handleAssetSubtype(cloneRequest, res);
		
		DefaultController<?, Integer> controller = ControllerRegistry.getInstance().get(clazz);
		return controller != null ? controller.handleRequest(cloneRequest, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
    private boolean sendFile(String storageUrl, int id, String name, MultipartFile fileUpload) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();		
		body.add("file", fileUpload.getResource());
		body.add("id", id);
		body.add("name", name);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		InterfaceController<OPA, Integer> interfaceController = InterfaceControllerRegistry.getInstance().get(OPA.class);
		
		ResponseEntity<OPA> response = interfaceController.forwardRequest(storageUrl, requestEntity);
		return response.getStatusCode() == HttpStatus.OK ? true : false;
	}
	
	@GetMapping(value = PATH_OPA_LOCAL + "_**/**")
	public ResponseEntity<?> handleGetLocalAsset(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String className = req.getServletPath().split("/")[1];
		Class<?> clazz = SubtypeUrls.opaUrls.get(className);
		DefaultController<?, Integer> controller = ControllerRegistry.getInstance().get(clazz);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	
	@GetMapping(value = PATH_OPA_SUBTYPE + "/**")
	public ResponseEntity<?> handleAssetSubtype(HttpServletRequest req, HttpServletResponse res) throws IOException {
		InterfaceController<OPA, Integer> controller = InterfaceControllerRegistry.getInstance().get(OPA.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}