package jda.app.opasys.opa.controller;

import java.util.HashMap;
import java.util.Map;

import jda.app.opasys.opa.modules.configasset.model.ConfigAsset;
import jda.app.opasys.opa.modules.defectasset.model.DefectAsset;
import jda.app.opasys.opa.modules.financeasset.model.FinanceAsset;
import jda.app.opasys.opa.modules.issueasset.model.CommentAsset;
import jda.app.opasys.opa.modules.issueasset.model.IssueAsset;
import jda.app.opasys.opa.modules.metricasset.model.MetricAsset;
import jda.app.opasys.opa.modules.planasset.model.PlanAsset;
import jda.app.opasys.opa.modules.riskasset.model.RiskAsset;

public class SubtypeUrls {
	
	public final static String PATH_PLAN_SERVICE = "/plan-service/plan";
	public final static String PATH_METRIC_SERVICE = "/metric-service/metric";
	public final static String PATH_CONFIG_SERVICE = "/config-service/config";
	public final static String PATH_FINANCE_SERVICE = "/finance-service/finance";
	public final static String PATH_RISK_SERVICE = "/risk-service/risk";
	public final static String PATH_DEFECT_SERVICE = "/defect-service/defect";
	public final static String PATH_ISSUE_SERVICE = "/issue-service/issue";
	public final static String PATH_ISSUE_SERVICE_COMMENT = "/issue-service/comment";
	
	public final static String PATH_OPA_PLAN_ASSET = "local_plan_asset";
	public final static String PATH_OPA_METRIC_ASSET = "local_metric_asset";
	public final static String PATH_OPA_CONFIG_ASSET = "local_config_asset";
	public final static String PATH_OPA_FINANCE_ASSET = "local_finance_asset";
	public final static String PATH_OPA_RISK_ASSET = "local_risk_asset";
	public final static String PATH_OPA_DEFECT_ASSET = "local_defect_asset";
	public final static String PATH_OPA_ISSUE_ASSET = "local_issue_asset";
	public final static String PATH_OPA_ISSUE_COMMENT_ASSET = "local_issue_comment";
	
	public final static Map<String, Class> opaUrls = new HashMap<>();
	
	static {
	  opaUrls.put(PATH_OPA_PLAN_ASSET, PlanAsset.class);
	  opaUrls.put(PATH_OPA_METRIC_ASSET, MetricAsset.class);
	  opaUrls.put(PATH_OPA_CONFIG_ASSET, ConfigAsset.class);
	  opaUrls.put(PATH_OPA_FINANCE_ASSET, FinanceAsset.class);
	  opaUrls.put(PATH_OPA_RISK_ASSET, RiskAsset.class);
	  opaUrls.put(PATH_OPA_DEFECT_ASSET, DefectAsset.class);
	  opaUrls.put(PATH_OPA_ISSUE_ASSET, IssueAsset.class);
	  opaUrls.put(PATH_OPA_ISSUE_COMMENT_ASSET, CommentAsset.class);
	}
	
	public final static Map<String, String> subtypeUrls = new HashMap<>();
	
	static {
		subtypeUrls.put(PATH_OPA_PLAN_ASSET, PATH_PLAN_SERVICE);
		subtypeUrls.put(PATH_OPA_METRIC_ASSET, PATH_METRIC_SERVICE);
		subtypeUrls.put(PATH_OPA_CONFIG_ASSET, PATH_CONFIG_SERVICE);
		subtypeUrls.put(PATH_OPA_FINANCE_ASSET, PATH_FINANCE_SERVICE);
		subtypeUrls.put(PATH_OPA_RISK_ASSET, PATH_RISK_SERVICE);
		subtypeUrls.put(PATH_OPA_DEFECT_ASSET, PATH_DEFECT_SERVICE);
		subtypeUrls.put(PATH_OPA_ISSUE_ASSET, PATH_ISSUE_SERVICE);
		subtypeUrls.put(PATH_OPA_ISSUE_COMMENT_ASSET, PATH_ISSUE_SERVICE_COMMENT);
	}
}
