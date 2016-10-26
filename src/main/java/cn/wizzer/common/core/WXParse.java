package cn.wizzer.common.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WXParse {
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/Users/peter-zhang/Desktop/微信聊天记录.csv"));
			BufferedWriter write = new BufferedWriter(new FileWriter("/Users/peter-zhang/Desktop/无意义汇总.txt",true));
			String line = reader.readLine();
			Set<String> set = new HashSet<String>();
			while(line!=null){
				if(line.indexOf("你已添加了")>0){
					
				}else if(line.indexOf("微信红包")>0){
					
				}else{
					set.add(line.replaceAll("[^\\u4e00-\\u9fa5]", "").replaceAll("未知好友", ""));
				}
				line = reader.readLine();
			}
			for (String string : set) {
				write.write(string+"\r\n");
			}
			write.flush();
			write.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			//BufferedReader reader = new BufferedReader(new FileReader("/Users/peter-zhang/Desktop/question_汽车知识副本.txt"));
//			//BufferedReader reader = new BufferedReader(new FileReader("/Users/peter-zhang/Desktop/数据2/汽车知识/question.txt"));
//			BufferedReader reader = new BufferedReader(new FileReader("/Users/peter-zhang/Desktop/数据/汽车知识/question.txt"));
//			BufferedWriter write = new BufferedWriter(new FileWriter("/Users/peter-zhang/Desktop/汽车知识汇总.txt",true));
//			String line = reader.readLine();
//			while(line!=null){
//				write.write(line+"\r\n");
//				line = reader.readLine();
//			}
//			write.flush();
//			write.close();
//			reader.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
