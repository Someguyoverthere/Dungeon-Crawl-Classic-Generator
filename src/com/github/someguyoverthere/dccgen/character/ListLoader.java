package com.github.someguyoverthere.dccgen.character;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class ListLoader. When instantiated, it will take a list and load them into an ArrayList as Strings that can be access as needed. This is mainly for rolling stuff.
 */
public class ListLoader {
	ArrayList<String> list;
	
	public ListLoader(File listFile){
		list = new ArrayList<>();
		
		loadList(listFile);
	}
	
	
	public void loadList(File listFile){
		Scanner scanner;
		
		try {
			scanner = new Scanner(listFile);
			while(scanner.hasNext()) {
				list.add(scanner.nextLine());	
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}

		
	}
	
	public String getItemAtIndex(int index){
		return list.get(index);
	}
	
	public int getListLength() {
		return list.size();
	}
}
