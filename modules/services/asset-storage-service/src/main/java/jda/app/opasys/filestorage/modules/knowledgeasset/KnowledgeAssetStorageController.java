package jda.app.opasys.filestorage.modules.knowledgeasset;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.filestorage.modules.knowledgeasset.model.KnowledgeAssetStorage;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class KnowledgeAssetStorageController extends DefaultController<KnowledgeAssetStorage, Integer> {
	
	@Value("${filestorage.path}")
	private String fileStoragePath;

	@Override
	public ResponseEntity<?> handleRequestWithFile(String httpMethod, KnowledgeAssetStorage entity, Integer id, MultipartFile fileUpload) {
		if (fileUpload != null) {
			String fullFilePath = fileStoragePath+ File.separator+ entity.getFilePath();
			return super.handleRequestWithFile(httpMethod, entity, id, fileUpload, fullFilePath);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		//TODO: send attachment file in response
		return super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}
	
	

}
