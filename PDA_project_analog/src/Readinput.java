import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Readinput {
	
	public static ArrayList<block> ReadBlockFile(String file_path) {
		
		BufferedReader br = null;
		FileReader fr = null;
		
		ArrayList<block> readresultlist = new ArrayList<block>();
		
		try {
			fr = new FileReader(file_path);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				String[] block_info_list = sCurrentLine.split(" ");
				String[] new_block_info_list = new String[3];
				int index = 0;
				
				for(int i =0;i<block_info_list.length;i++){
					
					String curinfo = block_info_list[i];
					if (curinfo !=" " && curinfo.length()>=1) {
						new_block_info_list[index] = curinfo;
						index++;
					}
				}
				
				block block_temp = new block(new_block_info_list[0], Integer.parseInt(new_block_info_list[1]), Integer.parseInt(new_block_info_list[2]));
				
				readresultlist.add(block_temp);	
			}
			
			System.out.println("**********************READ IN BLOCK INFO FILE****************************");
		} catch (IOException e) {
			e.printStackTrace();
		}				
		return readresultlist;		
	}
	
	
	public static ArrayList<net> ReadNetlistFile(String file_path , ArrayList<block> block_info_list){
		
		BufferedReader br = null;
		FileReader fr = null;
		
		ArrayList<net> readresultlist = new ArrayList<net>();
		
		try {
			fr = new FileReader(file_path);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				String[] net_info_current_line = sCurrentLine.split(" +");
				ArrayList<Integer> current_net_connection = new ArrayList<>();			
				
				for(int i =1;i<net_info_current_line.length;i=i+3){
					
					String connected_name = net_info_current_line[i];
					for (int j=0;j < block_info_list.size();j++){
						
						if( connected_name.equals(block_info_list.get(j).name) && !current_net_connection.contains(new Integer(j)) ){
							
							current_net_connection.add(new Integer(j));
						}
					}
				}
				
				net net_temp = new net(net_info_current_line[0], current_net_connection);
				readresultlist.add(net_temp);
					

			}
			System.out.println("**********************READ IN NET INFO FILE****************************");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	
		return readresultlist;
	}

	public static HashMap<Integer, Integer> ReadSymFile(String file_path, ArrayList<block> block_info_list){
		BufferedReader br = null;
		FileReader fr = null;
		HashMap<Integer, Integer> symmetryRelationMapping = new HashMap<>();
		int mapping_first=0, mapping_second=0;
		
		
		
		try {
			fr = new FileReader(file_path);
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				
				String[] CurrentLine_split = sCurrentLine.split("\\s+");
				
//				System.out.println(CurrentLine_split.length);
				
				String item1 = CurrentLine_split[0];
//				
				String item2;
				
				if(CurrentLine_split.length > 1){
					item2 = CurrentLine_split[1];
				} 
				else{
					item2 = item1;
				}
				
//				System.out.println(item1 + " & " + item2);
				for (int i=0;i<block_info_list.size();i++){
					
//					System.out.println(block_info_list.get(i).name + " " + item1);
					
					if(item1.equals(block_info_list.get(i).name) ){						
						mapping_first = i;
					}
					if(item2.equals(block_info_list.get(i).name)){
						mapping_second = i;
					}
				}
				
//				System.out.println(mapping_first + " & " + mapping_second);
								
				symmetryRelationMapping.put(new Integer(mapping_first), new Integer(mapping_second));
				symmetryRelationMapping.put(new Integer(mapping_second), new Integer(mapping_first));
				
			}
			
			System.out.println("**********************READ IN SYMMETRY INFO FILE****************************");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
			
		
		
		
		
		return symmetryRelationMapping;
	}


}
