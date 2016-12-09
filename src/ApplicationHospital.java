import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ApplicationHospital {
	public static void main(String[] args) throws IOException {
		GPSUtils gps = new GPSUtils();
		List<Hospital> list = DataUtils.getHospitals();
		File file = new File("UpdateHospitalFile.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(), true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		for (int i = 0; i < list.size(); i++) {
			Hospital p =list.get(i);
			System.out.println(i);
			Object[] o = gps.getCoordinateAddress(p.getAddress());
			if(o[1]==null||o[1].equals("null")){
				o[1] = "null";
			}else{
				o[1] = "'"+o[1]+"'";
			}
			if(o[0]==null||o[0].equals("null")){
				o[0] = "null";
			}else{
				o[0] = "'"+o[0]+"'";
			}
			if(o[2]==null||o[2].equals("null")){
				o[2] = "null";
			}else{
				o[2] = "'"+o[2]+"'";
			}
			
			if(o[3]==null||o[3].equals("null")){
				o[0] = "null";
			}else{
				o[3] = "'"+o[3]+"'";
			}
			
			if(o[4]==null||o[4].equals("null")){
				o[4] = "null";
			}else{
				o[4] = "'"+o[4]+"'";
			}
			
			bufferWritter.write("update geo_hospital set "+
			" latitude="+o[1]+",longitude="+o[0]+",baidu_city="+o[2]+",baidu_district="+o[3]+",baidu_street="+o[4]+" "+
			" where id = "+p.getId()+";\r\n");
		}
		bufferWritter.close();
		System.out.println("Done");
	}
}
