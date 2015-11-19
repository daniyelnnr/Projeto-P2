package core;

import easyaccept.EasyAccept;

//import java.util.ArrayList;
//import java.util.List;

//import easyaccept.EasyAcceptFacade;

public class PopPlusTest {

	public static void main(String[] args) {

		args = new String[] { "core.Facade", "tests/usecase_1.txt", "tests/usecase_2.txt", "tests/usecase_3.txt",
				"tests/usecase_4.txt", "tests/usecase_5.txt", "tests/usecase_6.txt", "tests/usecase_7.txt", "tests/usecase_8.txt", "tests/usecase_9.txt", "tests/usecase_910.txt" };
		EasyAccept.main(args);
		/*
		 * List<String> files = new ArrayList<String>(); // Put the us1.txt file
		 * into the "test scripts" list files.add("use_case_1.txt"); //
		 * Instantiate the Monopoly Game fa�ade Facade popFacade = new Facade();
		 * // Instantiate EasyAccept fa�ade EasyAcceptFacade eaFacade = new
		 * EasyAcceptFacade(popFacade, files); // Execute the tests
		 * eaFacade.executeTests(); // Print the tests execution results
		 * System.out.println(eaFacade.getCompleteResults());
		 */
	}

}
