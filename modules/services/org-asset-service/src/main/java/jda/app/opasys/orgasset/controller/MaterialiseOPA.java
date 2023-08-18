package jda.app.opasys.orgasset.controller;

import java.io.File;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import jda.app.opasys.common.model.KnowledgeAsset;
import jda.app.opasys.orgasset.modules.opainterface.modelasset.OPAInterface;
import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.InterfaceController;

public class MaterialiseOPA {

	private String fileStoragePath;

	public MaterialiseOPA(String fileStoragePath) {
		super();
		this.fileStoragePath = fileStoragePath;
	}


	public ResponseEntity<?> processCreateOPA(OrgAsset orgAsset,HttpMethod method, InterfaceController<OPAInterface, Integer> interfaceController) {

			if (materialiseSubtypeAsset(orgAsset, method, interfaceController)) {
				return ResponseEntity.ok("SAVE OPA SUCCESSFULLY!!!");
			}

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	private boolean materialiseSubtypeAsset(OrgAsset orgAsset,HttpMethod method,
			InterfaceController<OPAInterface, Integer> interfaceController) {

			String localPath = ControllerTk.getServiceUri(ManageOrgAssetController.PATH_LOCAL_OPA_SERVICE, ManageOrgAssetController.PATH_OPA_ORG_ASSET);
			return sendSubtypeAndFile(localPath, orgAsset, interfaceController);
	}


	private boolean sendSubtypeAndFile(String opaUrl, OrgAsset orgAsset, InterfaceController<OPAInterface, Integer> interfaceController) {
		if (orgAsset.getAttachment().isEmpty()) {
			return true;
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		File localFile = new File(fileStoragePath + File.separator + orgAsset.getAttachment());
		body.add("file", new FileSystemResource(localFile));
		body.add("data", orgAsset);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<OPAInterface> response = interfaceController.forwardRequest(opaUrl, requestEntity);
		return response.getStatusCode() == HttpStatus.OK ? true : false;
	}

}
