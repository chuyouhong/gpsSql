import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UpdateGeo {
	public static void main(String[] args) throws IOException {
		List<Patient> list = DataUtils.getCitys();
		File file = new File("UpdateGeoTownFile.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWritter = new FileWriter(file.getName(), true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

		for (int i = 0; i < list.size(); i++) {
			Patient p =list.get(i);
			
			bufferWritter.write("update geo_town set latitude="+p.getLatitude()+",longitude="+p.getLongitude()+" where id='"+p.getId()+"';\r\n" );
		}
		bufferWritter.close();
		System.out.println("Done");
	}
}
