package core;

import easyaccept.EasyAccept;

//import java.util.ArrayList;
//import java.util.List;

//import easyaccept.EasyAcceptFacade;

public class popPlusTestClient {

	public static void main(String[] args) {

		args = new String[] { "core.Facade", "tests/use_case_1.txt", "tests/use_case_2.txt" , "tests/usecase_3.txt", "tests/usecase_4.txt"};
		EasyAccept.main(args);
		/*
		 * List<String> files = new ArrayList<String>(); // Put the us1.txt file
		 * into the "test scripts" list files.add("use_case_1.txt"); //
		 * Instantiate the Monopoly Game façade Facade popFacade = new Facade();
		 * // Instantiate EasyAccept façade EasyAcceptFacade eaFacade = new
		 * EasyAcceptFacade(popFacade, files); // Execute the tests
		 * eaFacade.executeTests(); // Print the tests execution results
		 * System.out.println(eaFacade.getCompleteResults());
		 */
	}

}
