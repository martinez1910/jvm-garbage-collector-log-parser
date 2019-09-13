package logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String readPath = "arranque.log.2019-05-02_1742";
		String writePath = "arranque.log.2019-05-02_1742.csv";
		String line;
		
		File f = new File(writePath);
		if(f.exists())
			f.delete();
		f = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader(readPath));
			BufferedWriter bw = new BufferedWriter(new FileWriter(writePath));){
			bw.write("Time(S);YoungBefore(KB);YoungAfter(KB);YoungAllocated(KB);OldBefore(KB);OldAfter(KB);OldAllocated(KB);OldYoungBefore(KB);OldYoungAfter(KB);OldYoungAllocated(KB);PermBefore(KB);PermAfter(KB);PermAllocated(KB);TotalBefore(KB);TotalAfter(KB);TotalAllocated(KB)\n");
			boolean ignoreFirstOccurrence = false;
			while((line = br.readLine()) != null){
				if(!line.contains("GC ") || line.contains("at "))
					continue;
				if(!ignoreFirstOccurrence){
					ignoreFirstOccurrence = true;
					continue;
				}
				
				String time, young, youngBefore, youngAfter, youngAllocated, oldYoung, oldYoungBefore, oldYoungAfter, oldYoungAllocated, perm, permBefore="", permAfter="", permAllocated="";
				int oldBefore, oldAfter, oldAllocated, totalBefore, totalAfter, totalAllocated;
				String[] split = line.split(" ");
				time = split[0].split(":")[0];
				
				if(line.contains("Full GC")){
					young = split[4];
					youngBefore = young.split("->")[0];
					youngBefore = youngBefore.substring(0, youngBefore.length()-1);
					youngAfter = young.split("->")[1].split("\\(")[0];
					youngAfter = youngAfter.substring(0, youngAfter.length()-1);
					youngAllocated = young.split("\\(")[1];
					youngAllocated = youngAllocated.substring(0, youngAllocated.length()-3);
					
					oldYoung = split[7];
					oldYoungBefore = oldYoung.split("->")[0];
					oldYoungBefore = oldYoungBefore.substring(0, oldYoungBefore.length()-1);
					oldYoungAfter = oldYoung.split("->")[1].split("\\(")[0];
					oldYoungAfter = oldYoungAfter.substring(0, oldYoungAfter.length()-1);
					oldYoungAllocated = oldYoung.split("\\(")[1];
					oldYoungAllocated = oldYoungAllocated.substring(0, oldYoungAllocated.length()-2);
					
					
					
					perm = split[9];
					permBefore = perm.split("->")[0];
					permBefore = permBefore.substring(0, permBefore.length()-1);
					permAfter = perm.split("->")[1].split("\\(")[0];
					permAfter = permAfter.substring(0, permAfter.length()-1);
					permAllocated = perm.split("\\(")[1];
					permAllocated = permAllocated.substring(0, permAllocated.length()-4);
				}else{					
					young = split[3];
					oldYoung = split[4];
					
					youngBefore = young.split("->")[0];
					youngBefore = youngBefore.substring(0, youngBefore.length()-1);
					youngAfter = young.split("->")[1].split("\\(")[0];
					youngAfter = youngAfter.substring(0, youngAfter.length()-1);
					youngAllocated = young.split("\\(")[1];
					youngAllocated = youngAllocated.substring(0, youngAllocated.length()-3);
					
					oldYoungBefore = oldYoung.split("->")[0];
					oldYoungBefore = oldYoungBefore.substring(0, oldYoungBefore.length()-1);
					oldYoungAfter = oldYoung.split("->")[1].split("\\(")[0];
					oldYoungAfter = oldYoungAfter.substring(0, oldYoungAfter.length()-1);
					oldYoungAllocated = oldYoung.split("\\(")[1];
					oldYoungAllocated = oldYoungAllocated.substring(0, oldYoungAllocated.length()-3);
				}
				oldBefore = Integer.valueOf(oldYoungBefore) - Integer.valueOf(youngBefore);
				oldAfter = Integer.valueOf(oldYoungAfter) - Integer.valueOf(youngAfter);
				oldAllocated = Integer.valueOf(oldYoungAllocated) - Integer.valueOf(youngAllocated);
				if(permBefore.equals("")){
					totalBefore = 0;
					totalAfter = 0;
					totalAllocated = 0;
				}
				else{
					totalBefore = Integer.valueOf(oldYoungBefore) + Integer.valueOf(permBefore);
					totalAfter = Integer.valueOf(oldYoungAfter) + Integer.valueOf(permAfter);
					totalAllocated = Integer.valueOf(oldYoungAllocated) + Integer.valueOf(permAllocated);					
				}
				
				bw.write(time +";" +youngBefore +";" +youngAfter +";" +youngAllocated +";" +oldBefore +";" +oldAfter +";" +oldAllocated +";" +oldYoungBefore +";" +oldYoungAfter +";" +oldYoungAllocated +";" +permBefore +";" +permAfter +";" +permAllocated +";" +(totalBefore==0?"":totalBefore) +";" +(totalAfter==0?"":totalAfter) +";" +(totalAllocated==0?"":totalAllocated) +"\n");
			}		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}


