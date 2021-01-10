package dk.madsboddum.swgquest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestSWGQuestFactory {
	
	@Nested
	class PurvisKillZealots {
		
		private SWGQuest swgQuest;
		
		@BeforeEach
		public void loadQuest() {
			InputStream inputStream = TestSWGQuestFactory.class.getClassLoader().getResourceAsStream("purvis_kill_zealots.qst");
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
			Collection<SWGQuestTask> tasks = swgQuest.getTasks();
			
			assertEquals(1, tasks.size(), "The quest should have exactly one task");
			
			for (SWGQuestTask task : tasks) {
				assertEquals(SWGQuestTaskType.DESTROY_MULTIPLE, task.getType());
				assertEquals(Boolean.FALSE, task.getTaskOnFail());
				assertEquals("Kill 5 Tusken Zealots", task.getJournalEntryTitle());
				assertEquals("Purvis, the old soldier, has told you the Tuskens have been nesting in an outlying area called Junktown. He needs help and wants to see if you've got what it takes. He has asked you to take out 5 of the Tusken Raider Zealots", task
						.getJournalEntryDescription());
				assertEquals("quest/purvis_kill_soldiers", task.getGrantQuestOnComplete());
				assertEquals("tusken_raider_zealot", task.getTargetServerTemplate());
				assertEquals(5, task.getCount());
			}
		}
	}
	
	@Nested
	class PurvisKillSoldiers {
		
		private SWGQuest swgQuest;
		
		@BeforeEach
		public void loadQuest() {
			InputStream inputStream = TestSWGQuestFactory.class.getClassLoader().getResourceAsStream("purvis_kill_soldiers.qst");
			swgQuest = SWGQuestFactory.create(inputStream);
		}
		
		@Test
		public void testCommTask() {
			List<SWGQuestTask> tasks = swgQuest.getTasks();
			SWGQuestTask commPlayerTask = tasks.get(0);
			
			assertEquals(SWGQuestTaskType.COMM_PLAYER, commPlayerTask.getType());
			assertEquals("Well done! Now I need you to take out some of their Soldiers. They're a bit further out from Junktown and they're a bit tougher, but I think you can do it. ", commPlayerTask.getCommMessageText());
			assertEquals("object/mobile/dressed_purvis_arrison.iff", commPlayerTask.getNpcAppearanceServerTemplate());
			assertEquals(Boolean.FALSE, commPlayerTask.getVisible());
		}
		
		@Test
		public void testDestroyMultipleTask() {
			List<SWGQuestTask> tasks = swgQuest.getTasks();
			SWGQuestTask destroyMultipleTask = tasks.get(1);
			
			assertEquals("tusken_raider_soldier", destroyMultipleTask.getTargetServerTemplate());
		}
	}
	
}
