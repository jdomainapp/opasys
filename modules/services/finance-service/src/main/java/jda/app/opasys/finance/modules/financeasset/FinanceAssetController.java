package jda.app.opasys.finance.modules.financeasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import jda.app.opasys.finance.controller.ManageFinanceAssetController;
import jda.app.opasys.finance.modules.financeasset.model.FinanceAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController;

@Controller
public class FinanceAssetController extends DefaultController<FinanceAsset, Integer>{

	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		return super.handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
	}
}
