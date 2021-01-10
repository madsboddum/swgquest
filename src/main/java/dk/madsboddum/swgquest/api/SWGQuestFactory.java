package dk.madsboddum.swgquest.api;

import dk.madsboddum.swgquest.internal.XmlQuest;
import dk.madsboddum.swgquest.internal.XmlQuestData;
import dk.madsboddum.swgquest.internal.XmlQuestTask;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.Collection;

public class SWGQuestFactory {
	
	public static SWGQuest create(InputStream inputStream) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(XmlQuest.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			XmlQuest xmlQuest = (XmlQuest) unmarshaller.unmarshal(inputStream);
			
			return convertToApiModel(xmlQuest);
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}
	
	private static SWGQuest convertToApiModel(XmlQuest xmlQuest) {
		SWGQuest swgQuest = new SWGQuest();
		
		Collection<XmlQuestData> listDataCollection = xmlQuest.getList().getDataCollection();
		
		for (XmlQuestData xmlQuestData : listDataCollection) {
			String name = xmlQuestData.getName();
			String value = xmlQuestData.getValue();
			
			switch (name) {
				case "journalEntryTitle": swgQuest.setJournalEntryTitle(value); break;
				case "journalEntryDescription": swgQuest.setJournalEntryDescription(value); break;
				case "journalEntryCompletionSummary": swgQuest.setJournalEntryCompletionSummary(value); break;
				case "Bank Credits": swgQuest.setBankCredits(Integer.parseInt(value)); break;
				case "allowRepeats": swgQuest.setRepeatable(Boolean.getBoolean(value)); break;
				case "Level": swgQuest.setLevel(Integer.parseInt(value)); break;
				case "Faction Amount": swgQuest.setFactionAmount(Integer.parseInt(value)); break;
				case "Faction Name": swgQuest.setFactionName(value); break;
				case "Experience Amount": swgQuest.setExperienceAmount(Integer.parseInt(value)); break;
				case "Experience Type": swgQuest.setExperienceType(value); break;
				case "Tier": swgQuest.setTier(Integer.parseInt(value)); break;
			}
		}
		
		XmlQuestTask xmlQuestTask = xmlQuest.getTasks().getTask();
		SWGQuestTask swgQuestTask = convertTask(xmlQuestTask);
		swgQuest.setTask(swgQuestTask);
		
		return swgQuest;
	}
	
	private static SWGQuestTask convertTask(XmlQuestTask xmlQuestTask) {
		SWGQuestTask swgQuestTask = new SWGQuestTask();
		String type = xmlQuestTask.getType();
		
		switch (type) {
			case "Comm Player": swgQuestTask.setType(SWGQuestTaskType.COMM_PLAYER); break;
			case "Destroy Multiple": swgQuestTask.setType(SWGQuestTaskType.DESTROY_MULTIPLE); break;
		}
		
		Collection<XmlQuestData> dataCollection = xmlQuestTask.getDataCollection();
		
		for (XmlQuestData xmlQuestData : dataCollection) {
			String name = xmlQuestData.getName();
			String value = xmlQuestData.getValue();
			
			switch (name) {
				case "journalEntryTitle": swgQuestTask.setJournalEntryTitle(value); break;
				case "journalEntryDescription": swgQuestTask.setJournalEntryDescription(value); break;
				case "grantQuestOnComplete": swgQuestTask.setGrantQuestOnComplete(value); break;
				case "Target Server Template": swgQuestTask.setTargetServerTemplate(value); break;
				case "Count": swgQuestTask.setCount(Integer.valueOf(value)); break;
				case "Comm Message Text": swgQuestTask.setCommMessageText(value); break;
				case "NPC Appearance Server Template": swgQuestTask.setNpcAppearanceServerTemplate(value); break;
				case "isVisible": swgQuestTask.setVisible(Boolean.valueOf(value)); break;
				case "taskName": swgQuestTask.setName(value); break;
			}
		}
		
		Collection<XmlQuestTask> subXmlQuestTasks = xmlQuestTask.getSubTasks();
		
		if (subXmlQuestTasks != null) {
			for (XmlQuestTask subXmlQuestTask : subXmlQuestTasks) {
				SWGQuestTask subSwgQuestTask = convertTask(subXmlQuestTask);
				swgQuestTask.addSubTask(subSwgQuestTask);
			}
		}
		
		swgQuestTask.setTaskOnFail(xmlQuestTask.isTaskOnFail());
		
		return swgQuestTask;
	}
}
