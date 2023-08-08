package jda.app.opasys.filestorage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.filestorage.modules.knowledgeasset.model.KnowledgeAssetStorage;
import jda.app.opasys.filestorage.modules.orgasset.model.OrgAssetStorage;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageFileStorageController {
	public final static String PATH_KNOWLEDGE_ASSET = "/knowledgeasset";
	public final static String PATH_ORG_ASSET = "/orgasset";
	public final static String SERVICE_NAME = "asset-storage-service";


	@PostMapping(value = PATH_KNOWLEDGE_ASSET + "/**")
	public ResponseEntity<?> handleKnowledgeAssetPostRequest(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("file") MultipartFile fileUpload) throws IOException {
		DefaultController<KnowledgeAssetStorage, Integer> controller = ControllerRegistry.getInstance().get(KnowledgeAssetStorage.class);
		KnowledgeAssetStorage entity = new KnowledgeAssetStorage(id, name);
		return controller != null ? controller.handleRequestWithFile(HttpMethod.POST.name(), entity,id, fileUpload)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = PATH_KNOWLEDGE_ASSET + "/**")
	public ResponseEntity<?> handleKnowledgeAssetGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<KnowledgeAssetStorage, Integer> controller = ControllerRegistry.getInstance().get(KnowledgeAssetStorage.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(value = PATH_ORG_ASSET + "/**")
	public ResponseEntity<?> handleOrgAssetPostRequest(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("file") MultipartFile fileUpload) throws IOException {
		DefaultController<OrgAssetStorage, Integer> controller = ControllerRegistry.getInstance().get(OrgAssetStorage.class);
		OrgAssetStorage entity = new OrgAssetStorage(id, name);
		return controller != null ? controller.handleRequestWithFile(HttpMethod.POST.name(), entity,id, fileUpload)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping(value = PATH_ORG_ASSET + "/**")
	public ResponseEntity<?> handleOrgAssetGetRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAssetStorage, Integer> controller = ControllerRegistry.getInstance().get(OrgAssetStorage.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
