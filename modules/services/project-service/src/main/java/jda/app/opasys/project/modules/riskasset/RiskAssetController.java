package jda.app.opasys.project.modules.riskasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.project.controller.ManageProjectController;
import jda.app.opasys.project.modules.activity.model.Activity;
import jda.app.opasys.project.modules.riskasset.model.RiskAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;

@Controller
public class RiskAssetController extends DefaultController2<RiskAsset, Integer>{
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return ControllerTk.isPathContainId(ManageProjectController.PATH_RISK, path)
				? super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0))
				: ResponseEntity.badRequest().build();

	}
}