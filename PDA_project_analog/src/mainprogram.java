import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.naming.PartialResultException;


public class mainprogram {
	
	
	public static void main(String[] args){
		
		String testcase_name = "COMPARATOR_V2_VAR_K2";
		String block_file_path = "files/" + testcase_name + "/" + testcase_name + ".block";
		String netlist_file_path = "files/" + testcase_name + "/" + testcase_name + ".net";
		String sym_file_path = "files/" + testcase_name + "/" + testcase_name + ".sym";
		
		
		ArrayList<block> block_list = Readinput.ReadBlockFile(block_file_path);
		
		
//		The following part will test the Readin Block
//		System.out.println("****************Following deal with Read in block file***************************");
//		for(int i=0; i<block_list.size();i++){
//			System.out.println("the blcok_index is " + i + " and the name of the block is " + block_list.get(i).name + " with size as " + block_list.get(i).width + " & " + block_list.get(i).height );
//		}
		
		
		
		
		ArrayList<net> net_list = Readinput.ReadNetlistFile(netlist_file_path,block_list);
		
		
//		The following Part tests the result of read netlist
		
//		System.out.println("*************************The following part will deal with read in nets******************");
//		for (int i =0;i<net_list.size();i++){
//			ArrayList<Integer> connect = net_list.get(i).connections;
//			
//			System.out.println("id is "+ i + " length is " + connect.size());
//			for (int j=0; j< connect.size();j++){
//				System.out.printf(block_list.get(connect.get(j).intValue()).name + " ");
//			}
//			
//			System.out.println(" ");
//			for (int j=0; j< connect.size();j++){
//				System.out.printf(connect.get(j).intValue()+ " ");
//			}
//			
//			
//			
//			System.out.println("\n&&&&&&&&&&&&&&&&");
//			
//
//		}
		
		HashMap<Integer, Integer> sym_relationship = Readinput.ReadSymFile(sym_file_path, block_list);
		

		
//		The following code is used to make sure the ReadSym work correctly
//		System.out.println("**************************The following part will deal with Read sym!****************************");
//		for(Integer index : sym_relationship.keySet()){
//			System.out.println("the key is " + block_list.get(index).name + " and the value is : " + block_list.get(sym_relationship.get(index)).name);
//			
//		}

		

		
		
	
	}
	
	

}
