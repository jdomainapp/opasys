package jda.app.opasys.orgasset.modules.orgasset;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.orgasset.controller.MaterialiseOPA;
import jda.app.opasys.orgasset.modules.opainterface.modelasset.OPAInterface;
import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;
import jda.modules.msacommon.controller.InterfaceController;
import jda.modules.msacommon.controller.InterfaceControllerRegistry;

@Controller
public class OrgAssetController extends DefaultController<OrgAsset, Integer>{
	@Value("${filestorage.path}")
	private String fileStoragePath;
	
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}
	
	@Override
	public ResponseEntity<?> handleRequestWithFile(String httpMethod, OrgAsset entity, Integer id, MultipartFile fileUpload) {
		if (fileUpload != null) {
			String attachmentPath = entity.getProjectType() + File.separator+ entity.getKnowledgeType() + File.separator+  fileUpload.getOriginalFilename();
			entity.setAttachment(attachmentPath);
			String filePath = fileStoragePath+ File.separator+ attachmentPath;
			ResponseEntity<?> response = super.handleRequestWithFile(httpMethod, entity, id, fileUpload, filePath);
			if (response.getStatusCode() == HttpStatus.OK) {
				InterfaceController<OPAInterface, Integer> interfaceController = InterfaceControllerRegistry.getInstance()
						.get(OPAInterface.class);
				OrgAsset orgAsset = (OrgAsset) response.getBody();
				new MaterialiseOPA(fileStoragePath).processCreateOPA(orgAsset, HttpMethod.valueOf(httpMethod), interfaceController);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}else {
			return super.handleReques(httpMethod, entity, id);
		}
		
	}
}
