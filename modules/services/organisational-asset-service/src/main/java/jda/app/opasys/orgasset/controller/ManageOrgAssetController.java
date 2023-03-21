package jda.app.opasys.orgasset.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageOrgAssetController {
	public final static String PATH = "/org_asset";

	@RequestMapping(value = PATH + "/**")
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<OrgAsset, Integer> controller = ControllerRegistry.getInstance().get(OrgAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	//TODO: upload and download file
}
