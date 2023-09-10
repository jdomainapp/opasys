package jda.app.opasys.orgasset.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageOrgAssetController {
	public final static String PATH = "/org_asset";
	public final static String SERVICE_NAME = "org-asset-service";
	public final static String PATH_LOCAL_OPA_SERVICE ="opa-service";
	public final static String PATH_OPA_ORG_ASSET = "/org_asset";
	
	@PostMapping(value = PATH + "/**")
	public ResponseEntity<?> handleKnowledgePostRequest(@RequestPart("data") OrgAsset entity, @RequestPart("file") MultipartFile fileUpload) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequestWithFile(HttpMethod.POST.name(), entity,null, fileUpload)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PutMapping(value = PATH + "/**"+"/{id}")
	public ResponseEntity<?> handleKnowledgePutRequest(@RequestPart("data") OrgAsset entity, @RequestPart("file") MultipartFile fileUpload, @PathVariable int id) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequestWithFile(HttpMethod.PUT.name(), entity, id, fileUpload)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = PATH + "/**")
	@DeleteMapping(value = PATH + "/**")
	public ResponseEntity<?> handleKnowledgeGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
