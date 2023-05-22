package jda.app.opasys.orgasset.modules.orgasset;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import jda.app.opasys.orgasset.controller.ManageOrgAssetController;
import jda.app.opasys.orgasset.kafka.source.SimpleSourceBean;
import jda.app.opasys.orgasset.modules.orgasset.model.OrgAsset;
import jda.modules.msacommon.controller.ControllerTk;
import jda.modules.msacommon.controller.DefaultController2;
import jda.modules.msacommon.messaging.kafka.KafkaChangeAction;

@Controller
public class OrgAssetController extends DefaultController2<OrgAsset, Integer>{
	
	@Autowired
	SimpleSourceBean sourceBean;
	
	
	@Override
	public ResponseEntity<?> handleRequest(HttpServletRequest req, HttpServletResponse res) {
		String path = req.getServletPath();
		List<Integer> ids = ControllerTk.findIntegers(path);
		if(ControllerTk.isPathContainId(ManageOrgAssetController.PATH, path)) {
			ResponseEntity<?> responseEntity = handleRequest(req, res, ids.isEmpty() ? null : ids.get(0));
			String requestMethod = req.getMethod();
			String kafkaPath= ControllerTk.getServiceUri(ManageOrgAssetController.SERVICE_NAME, ManageOrgAssetController.PATH+"/{id}");
			if (requestMethod.equals(RequestMethod.POST.toString())) {
				OrgAsset responseUser = (OrgAsset) responseEntity.getBody();
				sourceBean.publishChange(OrgAsset.class.getTypeName(), KafkaChangeAction.CREATED, responseUser.getId(), kafkaPath);
			}else if (requestMethod.equals(RequestMethod.PUT.toString())) {
				OrgAsset responseUser = (OrgAsset) responseEntity.getBody();
				sourceBean.publishChange(OrgAsset.class.getTypeName(), KafkaChangeAction.UPDATED, responseUser.getId(), kafkaPath);
			}else if (requestMethod.equals(RequestMethod.DELETE.toString())) {
				sourceBean.publishChange(OrgAsset.class.getTypeName(), KafkaChangeAction.DELETED, Integer.parseInt((String)responseEntity.getBody()), kafkaPath);
			}
			
			return responseEntity;
		}
			return ResponseEntity.badRequest().build();
		

	}
}
