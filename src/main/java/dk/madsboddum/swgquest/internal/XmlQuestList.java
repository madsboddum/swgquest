package dk.madsboddum.swgquest.internal;

import javax.xml.bind.annotation.XmlElement;
import java.util.Collection;

public class XmlQuestList {
	@XmlElement(name="data")
	private Collection<XmlQuestData> dataCollection;
	
	public Collection<XmlQuestData> getDataCollection() {
		return dataCollection;
	}
}
