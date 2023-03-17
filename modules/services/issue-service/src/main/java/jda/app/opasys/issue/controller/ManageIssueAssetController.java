package jda.app.opasys.issue.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.issue.modules.issueasset.model.IssueAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageIssueAssetController {
	public final static String PATH = "/issue";

	@RequestMapping(value = PATH + "/**")
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<IssueAsset, Integer> controller = ControllerRegistry.getInstance().get(IssueAsset.class);
		return controller.handleRequest(req, res);
	}
}
