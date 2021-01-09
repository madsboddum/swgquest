package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlAttribute;

public class XmlQuestData {
	@XmlAttribute
	private String value;
	
	@XmlAttribute
	private String name;
	
	public String getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
}
