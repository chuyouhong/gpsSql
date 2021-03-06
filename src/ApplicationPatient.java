import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ApplicationPatient {
	public static void main(String[] args) throws IOException {
		GPSUtils gps = new GPSUtils();
		List<Patient> list = DataUtils.getPatients();
		File file = new File("UpdatePatientFile.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(), true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		for (int i = 0; i < list.size(); i++) {
			Patient p =list.get(i);
			System.out.println(i);
			Object[] o = gps.getCoordinate(p.getAddress());
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
			bufferWritter.write("update member_patient set latitude="+o[1]+",longitude="+o[0]+" where id='"+p.getId()+"';\r\n" );
		}
		bufferWritter.close();
		System.out.println("Done");
	}
}
