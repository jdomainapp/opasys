package jda.app.opasys.project.modules.knowledgeelement;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.project.modules.knowledgeelement.model.KnowledgeElement;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class KnowledgeElementController extends DefaultController<KnowledgeElement, Integer>{
	
	//@Value("${filestorage.path}")
	private String fileStoragePath;
	
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return  super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}

	@Override
	public ResponseEntity<?> handleRequestWithFile(String httpMethod, KnowledgeElement entity, Integer id, MultipartFile fileUpload) {
		if (fileUpload != null) {
			String attachmentPath = entity.getProject().getId() + File.separator+ entity.getType() + File.separator+  fileUpload.getOriginalFilename();
			entity.setAttachment(attachmentPath);
			String filePath = fileStoragePath+ File.separator+ attachmentPath;
			return super.handleRequestWithFile(httpMethod, entity, id, fileUpload, filePath);
		}else {
			return super.handleReques(httpMethod, entity, id);
		}
	}
	

}
