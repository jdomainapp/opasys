package jda.app.opasys.filestorage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jda.app.opasys.filestorage.modules.fileasset.model.FileStorage;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageFileStorageController {
	public final static String PATH = "/file";
	public final static String SERVICE_NAME = "asset-storage-service";


	@PostMapping(value = PATH + "/**")
	public ResponseEntity<?> handleRequest(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("file") MultipartFile fileUpload) throws IOException {
		DefaultController<FileStorage, Integer> controller = ControllerRegistry.getInstance().get(FileStorage.class);
		FileStorage entity = new FileStorage(id, name);
		return controller != null ? controller.handleRequestWithFile(HttpMethod.POST.name(), entity,id, fileUpload)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
