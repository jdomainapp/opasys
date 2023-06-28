package jda.app.opasys.filestorage.modules.fileasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.filestorage.modules.fileasset.model.FileStorage;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class FileStorageController extends DefaultController<FileStorage, Integer> {

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		ResponseEntity<?> responseEntity = handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
		
		return responseEntity;
	}

}
