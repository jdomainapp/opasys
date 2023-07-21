package jda.app.opasys.config.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.config.modules.configasset.model.ConfigAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageConfigAssetController {
	
	public final static String PATH_CONFIG = "/config";

	@RequestMapping(value = PATH_CONFIG + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<ConfigAsset, Integer> controller = ControllerRegistry.getInstance().get(ConfigAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
