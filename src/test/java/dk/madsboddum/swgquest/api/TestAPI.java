package dk.madsboddum.swgquest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestAPI {
	
	@Nested
	class PurvisKillZealots {
		
		private SWGQuest swgQuest;
		
		@BeforeEach
		public void loadQuest() {
			InputStream inputStream = TestAPI.class.getClassLoader().getResourceAsStream("purvis_kill_zealots.qst");
			swgQuest = SWGQuestFactory.create(inputStream);
		}
		
		@Test
		public void testJournalEntryTitle() {
			String journalEntryTitle = swgQuest.getJournalEntryTitle();
			
			assertEquals("Tusken Infestation", journalEntryTitle);
		}
		
		@Test
		public void testJournalEntryDescription() {
			String journalEntryTitle = swgQuest.getJournalEntryDescription();
			
			assertEquals("The Tusken raids have been getting worse and they need your help to put them down.", journalEntryTitle);
		}
		
		@Test
		public void testJournalEntryCompletionSummary() {
			String journalEntryTitle = swgQuest.getJournalEntryCompletionSummary();
			
			assertEquals("You've been a great help in putting down the Tuskan raids. Mos Eisley is a bit safer because of you.", journalEntryTitle);
		}
		
		@Test
		public void testBankCredits() {
			Integer bankCredits = swgQuest.getBankCredits();
			
			assertEquals(1650, bankCredits);
		}
		
		@Test
		public void testRepeatable() {
			boolean repeatable = swgQuest.isRepeatable();
			
			assertFalse(repeatable);
		}
		
		@Test
		public void testLevel() {
			Integer level = swgQuest.getLevel();
			
			assertEquals(5, level);
		}
		
		@Test
		public void testFactionAmount() {
			Integer factionAmount = swgQuest.getFactionAmount();
			
			assertEquals(0, factionAmount);
		}
		
		@Test
		public void testFactionName() {
			String factionName = swgQuest.getFactionName();
			
			assertEquals("Rebel", factionName);
		}
		
		@Test
		public void testExperienceAmount() {
			Integer experienceAmount = swgQuest.getExperienceAmount();
			
			assertEquals(0, experienceAmount);
		}
		
		@Test
		public void testExperienceType() {
			String experienceType = swgQuest.getExperienceType();
			
			assertEquals("quest_combat", experienceType);
		}
		
		@Test
		public void testTier() {
			Integer tier = swgQuest.getTier();
			
			assertEquals(5, tier);
		}
		
		@Test
		public void testTasks() {
			SWGQuestTask task = swgQuest.getTask();
			
			assertEquals(SWGQuestTaskType.DESTROY_MULTIPLE, task.getType());
			assertEquals(Boolean.FALSE, task.getTaskOnFail());
			assertEquals("Kill 5 Tusken Zealots", task.getJournalEntryTitle());
			assertEquals("Purvis, the old soldier, has told you the Tuskens have been nesting in an outlying area called Junktown. He needs help and wants to see if you've got what it takes. He has asked you to take out 5 of the Tusken Raider Zealots", task.getJournalEntryDescription());
			assertEquals("quest/purvis_kill_soldiers", task.getGrantQuestOnComplete());
			assertEquals("tusken_raider_zealot", task.getTargetServerTemplate());
			assertEquals(5, task.getCount());
		}
		
		@Test
		public void testEveryTaskCompleteAtStep1() {
			boolean complete = swgQuest.isEveryTaskCompleted(1);
			
			assertTrue(complete);
		}
	}
	
	@Nested
	class PurvisKillSoldiers {
		
		private SWGQuest swgQuest;
		
		@BeforeEach
		public void loadQuest() {
			InputStream inputStream = TestAPI.class.getClassLoader().getResourceAsStream("purvis_kill_soldiers.qst");
			swgQuest = SWGQuestFactory.create(inputStream);
		}
		
		@Test
		public void testCommTask() {
			SWGQuestTask commPlayerTask = swgQuest.getTask();
			
			assertEquals(SWGQuestTaskType.COMM_PLAYER, commPlayerTask.getType());
			assertEquals("Well done! Now I need you to take out some of their Soldiers. They're a bit further out from Junktown and they're a bit tougher, but I think you can do it. ", commPlayerTask.getCommMessageText());
			assertEquals("object/mobile/dressed_purvis_arrison.iff", commPlayerTask.getNpcAppearanceServerTemplate());
			assertEquals(Boolean.FALSE, commPlayerTask.getVisible());
		}
		
		@Test
		public void testDestroyMultipleTask() {
			SWGQuestTask destroyMultipleTask = swgQuest.getTask().getSubTasks().get(0);
			
			assertEquals("tusken_raider_soldier", destroyMultipleTask.getTargetServerTemplate());
		}
	}
	
	@Nested
	class PurvisKillWarriors {
		
		private SWGQuest swgQuest;
		
		@BeforeEach
		public void loadQuest() {
			InputStream inputStream = TestAPI.class.getClassLoader().getResourceAsStream("purvis_kill_warriors.qst");
			swgQuest = SWGQuestFactory.create(inputStream);
		}
		
		@Test
		public void testSubTasks() {
			SWGQuestTask swgQuestTask = swgQuest.getTask();
			List<SWGQuestTask> subTasks = swgQuestTask.getSubTasks();
			
			assertEquals(2, subTasks.size());
			
			SWGQuestTask subTask1 = subTasks.get(0);
			SWGQuestTask subTask2 = subTasks.get(1);
			
			assertEquals("Kill 5 Tusken Warriors", subTask1.getJournalEntryTitle());
			assertEquals("Incoming Message From Jabba's Palace", subTask2.getJournalEntryTitle());
		}
		
		@Test
		public void testGetTasksForStep2() {
			List<SWGQuestTask> tasksForStep = swgQuest.getTasksForStep(2);
			
			SWGQuestTask firstTask = tasksForStep.get(0);
			assertEquals("Kill 5 Tusken Warriors", firstTask.getJournalEntryTitle());
			
			SWGQuestTask secondTask = tasksForStep.get(1);
			assertEquals("Incoming Message From Jabba's Palace", secondTask.getJournalEntryTitle());
		}
		
		@Test
		public void testGetTasksForStep3() {
			List<SWGQuestTask> tasksForStep = swgQuest.getTasksForStep(3);
			
			SWGQuestTask firstTask = tasksForStep.get(0);
			assertEquals("His Honor", firstTask.getJournalEntryTitle());
			
			SWGQuestTask secondTask = tasksForStep.get(1);
			assertEquals("Are you still doing tasks for the mayor? Hurry up, will you? Jabba's becoming impatient!", secondTask.getCommMessageText());
		}
		
		@Test
		public void testEveryTaskNotCompleteAtStep4() {
			boolean complete = swgQuest.isEveryTaskCompleted(4);
			
			assertFalse(complete);
		}
		
		@Test
		public void testEveryTaskCompleteAtStep5() {
			boolean complete = swgQuest.isEveryTaskCompleted(5);
			
			assertTrue(complete);
		}
	}
	
}
