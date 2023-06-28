package jda.app.opasys.filestorage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.filestorage.modules.fileasset.model.FileStorage;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageFileStorageController {
	public final static String PATH = "/file";
	public final static String SERVICE_NAME = "file-storage-service";
	
	@Value("${filestorage.path}")
	private String fileStoragePath;

	@RequestMapping(value = PATH + "/**")
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<FileStorage, Integer> controller = ControllerRegistry.getInstance().get(FileStorage.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
