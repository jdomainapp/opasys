package jda.app.opasys.project.modules.opainterface.modelasset;

import lombok.Getter;

@Getter
public class Asset {
	public int id;
	
	public String attachment="";
	
	public Asset(int id) {
		super();
		this.id = id;
	}

	public Asset(int id, String attachment) {
		super();
		this.id = id;
		this.attachment = attachment;
	}
	
	
}
