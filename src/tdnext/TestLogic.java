package tdnext;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestLogic {
	private static String EMPTY_STRING = "";
	private static String ADD_TASK1 = "ADD task1";
	private static String ADD_TASK2 = "ADD task2";
	private static String ADD_TASK3_WITHDATE = "ADD task3 BY 30/10/2015";
	private static String ADD_HOMEWORK1 = "ADD homework1";
	private static String DELETE_INDEX1 = "DELETE 1";
	private static String DELETE_INDEXOUTOFBOUND = "DELETE 100";
	private static String EDIT_INDEX1 = "EDIT 1 taskEdit";
	private static String EDIT_INDEXOUTOFBOUND = "EDIT 100 taskEdit";
	private static String SEARCH_HOME = "SEARCH home";
	
	TDNextLogicAPI _testLogic = new TDNextLogicAPI();
	ArrayList<Task> _output = new ArrayList<Task>();

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddWithSimpleTask() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
			fail();
		}
	}
	
	@Test
	public void testAddWithTwoSimpleTasks() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
			fail();
		}
	}
	
	@Test
	public void testAddWithTaskWithDate() {
		ArrayList<String> allInputs = new ArrayList<String>();
		allInputs.add(ADD_TASK1);
		allInputs.add(ADD_TASK2);
		allInputs.add(ADD_TASK3_WITHDATE);
		
		try {
			for(int i = 0; i < allInputs.size(); i++){
				_output = _testLogic.executeCommand(allInputs.get(i));
			}
			String desiredOutput = "task3 BY 30/10/2015\n" + "task1\n" + "task2\n";
			String testOutput = new String();
			for(int i = 0; i < _output.size(); i++) {
				testOutput = testOutput + _output.get(i).toString() + "\n";
			}
			assertEquals(EMPTY_STRING, desiredOutput, testOutput);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testDelete() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
			fail();
		}
	}
	
	@Test
	public void testDeleteWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
	public void testEdit() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
			fail();
		}
	}
	
	@Test
	public void testEditWithIndexOutOfBound() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
	public void testSearch() {
		ArrayList<String> allInputs = new ArrayList<String>();
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
			fail();
		}
	}
}
