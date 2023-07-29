package jda.app.opasys.filestorage.modules.fileasset;

import java.io.File;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.filestorage.modules.fileasset.model.FileStorage;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class FileStorageController extends DefaultController<FileStorage, Integer> {
	
	@Value("${filestorage.path}")
	private String fileStoragePath;

	@Override
	public ResponseEntity<?> handleRequestWithFile(String httpMethod, FileStorage entity, Integer id, MultipartFile fileUpload) {
		if (fileUpload != null) {
			String fullFilePath = fileStoragePath+ File.separator+ entity.getFilePath();
			return super.handleRequestWithFile(httpMethod, entity, id, fileUpload, fullFilePath);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

}
