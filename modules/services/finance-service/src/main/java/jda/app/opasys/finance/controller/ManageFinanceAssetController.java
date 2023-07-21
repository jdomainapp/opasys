package jda.app.opasys.finance.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jda.app.opasys.finance.modules.financeasset.model.FinanceAsset;
import jda.modules.msacommon.controller.ControllerRegistry;
import jda.modules.msacommon.controller.DefaultController;

@RestController
@RequestMapping(value = "/")
public class ManageFinanceAssetController {
	
	public final static String PATH_FINANCE = "/finance";

	@RequestMapping(value = PATH_FINANCE + "/**")
	public ResponseEntity<?> handlePlan(HttpServletRequest req, HttpServletResponse res) throws IOException {
		DefaultController<FinanceAsset, Integer> controller = ControllerRegistry.getInstance().get(FinanceAsset.class);
		return controller != null ? controller.handleRequest(req, res)
				: ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
}
