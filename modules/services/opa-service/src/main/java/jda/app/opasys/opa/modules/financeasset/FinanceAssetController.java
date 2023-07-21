package jda.app.opasys.opa.modules.financeasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.opa.controller.ManageOPAController;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class FinanceAssetController extends DefaultController<PlanAsset, Integer>{

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}
}
