package jda.app.opasys.project.modules.opainterface;

import java.util.HashMap;
import java.util.Map;

import jda.app.opasys.project.modules.issuecomment.model.Comment;
import jda.app.opasys.project.modules.knowledgeelement.model.Config;
import jda.app.opasys.project.modules.knowledgeelement.model.Defect;
import jda.app.opasys.project.modules.knowledgeelement.model.Finance;
import jda.app.opasys.project.modules.knowledgeelement.model.Issue;
import jda.app.opasys.project.modules.knowledgeelement.model.Metric;
import jda.app.opasys.project.modules.knowledgeelement.model.Plan;
import jda.app.opasys.project.modules.knowledgeelement.model.Risk;

public class OpaUrl {
	
	public final static String PATH_ASSET_STORAGE_SERVICE = "asset-storage-service/file";
	
	public final static String PATH_LOCAL_OPA_SERVICE = "opa-service";
	
	public final static String PATH_SUBTYPE_OPA_SERVICE = "opa-service/redirect";
	
	public final static String PATH_OPA_PROJECT = "/project";
	public final static String PATH_OPA_PLAN_ASSET = "/plan_asset";
	public final static String PATH_OPA_METRIC_ASSET = "/metric_asset";
	public final static String PATH_OPA_CONFIG_ASSET = "/config_asset";
	public final static String PATH_OPA_FINANCE_ASSET = "/finance_asset";
	public final static String PATH_OPA_RISK_ASSET = "/risk_asset";
	public final static String PATH_OPA_DEFECT_ASSET = "/defect_asset";
	public final static String PATH_OPA_ISSUE_ASSET = "/issue_asset";
	public final static String PATH_OPA_ISSUE_COMMENT_ASSET = "/issue_comment";
	
	public final static Map<Class, String> opaUrls = new HashMap<>();
	
	static {
	  opaUrls.put(Plan.class, PATH_OPA_PLAN_ASSET);
	  opaUrls.put(Metric.class, PATH_OPA_METRIC_ASSET);
	  opaUrls.put(Config.class, PATH_OPA_CONFIG_ASSET);
	  opaUrls.put(Finance.class, PATH_OPA_FINANCE_ASSET);
	  opaUrls.put(Risk.class, PATH_OPA_RISK_ASSET);
	  opaUrls.put(Defect.class, PATH_OPA_DEFECT_ASSET);
	  opaUrls.put(Issue.class, PATH_OPA_ISSUE_ASSET);
	  opaUrls.put(Comment.class, PATH_OPA_ISSUE_COMMENT_ASSET);
	}
}
