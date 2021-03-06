package tdnext;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestLogic {
	private static String EMPTY_STRING = "";
	private static String ADD_TASK1 = "ADD task1";
	private static String ADD_TASK2 = "ADD task2";
	private static String ADD_TASK3_WITHDATE = "ADD task3 BY 31/10/2015";
	private static String ADD_TASK4_WITHDATE = "ADD task4 BY 01/12/2015";
	private static String ADD_TASK5_WITHIMPORTANCE = "ADD task5 IMPORTANT";
	private static String ADD_HOMEWORK1 = "ADD homework1";
	private static String ADD_HOMEWORK2 = "ADD homework2";
	private static String CLEAR = "CLEAR";
	private static String DELETE_INDEX0 = "DELETE 0";
	private static String DELETE_INDEX1 = "DELETE 1";
	private static String DELETE_INDEXOUTOFBOUND = "DELETE 100";
	private static String EDIT_INDEX1 = "EDIT 1 taskEdit";
	private static String EDIT_INDEXOUTOFBOUND = "EDIT 100 taskEdit";
	private static String SEARCH_HOME = "SEARCH home";
	private static String SORT_BY_NAME = "SORT NAME";
	private static String SORT_BY_DEADLINE = "SORT DEADLINE";
	private static String UNDO = "UNDO";
	
	TDNextLogicAPI _testLogic = new TDNextLogicAPI();
	ArrayList<Task> _output = new ArrayList<Task>();

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	// This is to test that the add function can work.
	public void testAddWithSimpleTask() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task1\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test that adding the second task will not overwrite the first
	// task
	public void testAddWithTwoSimpleTasks() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task1\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test that the task with date will retain the date
	// as part of its description
	public void testAddWithTaskWithDate() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 31/10/2015\n" + "task1\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of the 'within size' partition
	public void testDeleteWithIndex1() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(DELETE_INDEX1);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task1\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case for the 'greater than size' partition
	public void testDeleteWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(DELETE_INDEXOUTOFBOUND);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			fail();
		} catch (Exception e) {
			String desiredOutput = "Index: 99, Size: 3";
			String testOutput = e.getMessage();
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		}
	}
	
	@Test
	public void testDeleteWithIndex0() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(DELETE_INDEX0);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			fail();
		} catch (Exception e) {
			String desiredOutput = "-1";
			String testOutput = e.getMessage();
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		}
	}
	
	@Test
	// This is to test the boundary case of 'within size' partition
	public void testEditWithIndex1() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(EDIT_INDEX1);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "taskEdit\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of 'greater than size' partition
	public void testEditWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(EDIT_INDEXOUTOFBOUND);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			fail();
		} catch (Exception e) {
			String desiredOutput = "Index: 99, Size: 2";
			String testOutput = e.getMessage();
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		}
	}
	
	@Test
	// This is to test the boundary case of 'valid input' partition.
	// This is to test that the search is returning the correct output.
	public void testSearch() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(SEARCH_HOME);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "homework1\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of 'valid input' partition.
	// This is to test that if there are more than one task matching the input,
	// all the tasks will be displayed.
	public void testSearchWithMoreTasks() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SEARCH_HOME);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "homework1\n" + "homework2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of 'valid index' partition
	// This is to test that the task editted is within the list from the
	// search function and not the entire list of task.
	public void testEditAfterSearchWithIndex1 () {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SEARCH_HOME);
		allInputs.add(EDIT_INDEX1);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 31/10/2015\n" + "task1\n" +
									"task2\n" + "taskEdit\n" + "homework2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of 'valid index' partition.
	// This is to test that the task deleted is within the list from the
	// search function and not the entire list of task.
	public void testDeleteAfterSearchWithIndex1() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SEARCH_HOME);
		allInputs.add(DELETE_INDEX1);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 31/10/2015\n" + "task1\n" +
									"task2\n" + "homework2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// This is to test the boundary case of 'invalid index' partition.
	public void testEditAfterSearchWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SEARCH_HOME);
		allInputs.add(EDIT_INDEXOUTOFBOUND);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			fail();
		} catch (Exception e) {
			String desiredOutput = "Index: 99, Size: 2";
			String testOutput = e.getMessage();
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		}
	}
	
	@Test
	// This is to test the boundary case of 'invalid index' partition.
	public void testDeleteAfterSearchWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SEARCH_HOME);
		allInputs.add(DELETE_INDEXOUTOFBOUND);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			fail();
		} catch (Exception e) {
			String desiredOutput = "Index: 99, Size: 2";
			String testOutput = e.getMessage();
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		}
	}
	
	@Test
	public void testUndoAfterAdd() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(UNDO);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = EMPTY_STRING;
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testUndoAfterDelete() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(DELETE_INDEX1);
		allInputs.add(UNDO);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task1\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();			
			fail();
		}
	}
	
	@Test
	public void testUndoAfterEdit() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(EDIT_INDEX1);
		allInputs.add(UNDO);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task1\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSortDefault() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_TASK4_WITHDATE);
		allInputs.add(ADD_TASK5_WITHIMPORTANCE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 31/10/2015\n" +
									"task5 IMPORTANT\n" +
									"task4 BY 01/12/2015\n" + 
									"task1\n" + "task2\n" +
									"homework1\n" + "homework2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSortByName() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SORT_BY_NAME);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "homework1\n" + "homework2\n" + 
									"task1\n" + "task2\n" + 
									"task3 BY 31/10/2015\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testSortByDeadline() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_TASK4_WITHDATE);
		allInputs.add(ADD_TASK5_WITHIMPORTANCE);
		allInputs.add(ADD_HOMEWORK1);
		allInputs.add(ADD_HOMEWORK2);
		allInputs.add(SORT_BY_DEADLINE);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 31/10/2015\n" +
									"task4 BY 01/12/2015\n" + 
									"task5 IMPORTANT\n" +
									"task1\n" + "task2\n" +
									"homework1\n" + "homework2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testClear() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(CLEAR);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testStartProgram() {
		ArrayList<String> allInputs = new ArrayList<String>();
		//allInputs.add(CLEAR);
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		allInputs.add(ADD_TASK4_WITHDATE);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				System.out.println(allInputs.get(i));
				_testLogic.executeCommand(allInputs.get(i));
			}
			_output = _testLogic.startProgram();
			String desiredOutput = "task3 BY 31/10/2015\n" +
									"task4 BY 01/12/2015\n" +
									"task1\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
