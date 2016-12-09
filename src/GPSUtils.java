import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GPSUtils {
	
	String key = "LutzhyP0qE07QUQ41DGt7oR3nKLQ9VIC";
	
	public static void main(String[] args) {
		GPSUtils gps = new GPSUtils();
		try {
			Object[] objs = gps.getCoordinateAddress("重庆重庆市酉阳土家族苗族自治县麻旺镇");
			System.out.println("latitude='"+objs[1]+"',longitude='"+objs[0]+"'"+objs[2]+objs[3]+objs[4]);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param addr
	 *            查询的地址
	 * @return
	 * @throws IOException
	 */
	public Object[] getCoordinate(String addr) throws IOException {
		String lng = null;// 经度
		String lat = null;// 纬度
		String address = null;
		try {
			address = java.net.URLEncoder.encode(addr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=xml&ak=%s", address, key);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String result = "";
				String data = null;
				while ((data = br.readLine()) != null) {
					result =  result + data; 
				}
				//结果返回成功
				if(result!=null&&!result.equals("")&&result.contains("<status>0</status>")){
					String location = result.substring(result.indexOf("<location>")+11,result.indexOf("</location>"));
					lat = location.substring(location.indexOf("<lat>")+5,location.indexOf("</lat>"));
					lng = location.substring(location.indexOf("<lng>")+5,location.indexOf("</lng>"));
//					lat = lat.substring(0, lat.indexOf(".")+6>lat.length()?lat.length():lat.indexOf(".")+6);
//					lng = lng.substring(0, lng.indexOf(".")+6>lng.length()?lng.length():lng.indexOf(".")+6);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return new Object[] { lng, lat };
	}
	
	/**
	 * @param addr
	 *            查询的地址
	 * @return
	 * @throws IOException
	 */
	public Object[] getCoordinateAddress(String address) throws IOException {
		String lng = null;// 经度
		String lat = null;// 纬度
		String city = null;// 纬度
		String district = null;
		String street = null;
		try {
			address = java.net.URLEncoder.encode(address, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=xml&ak=%s", address, key);
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				String result = "";
				String data = null;
				while ((data = br.readLine()) != null) {
					result =  result + data; 
				}
				//结果返回成功
				if(result!=null&&!result.equals("")&&result.contains("<status>0</status>")){
					String location = result.substring(result.indexOf("<location>")+11,result.indexOf("</location>"));
					if(result.contains("<lat>"))
					lat = location.substring(location.indexOf("<lat>")+5,location.indexOf("</lat>"));
					if(result.contains("<lng>"))
					lng = location.substring(location.indexOf("<lng>")+5,location.indexOf("</lng>"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr != null) {
				insr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		String urladdr = "http://api.map.baidu.com/geocoder/v2/?output=xml&location="+lat+","+lng+"&ak="+key;
		URL myURL1 = null;
		URLConnection httpsConn1 = null;
		try {
			myURL1 = new URL(urladdr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr1 = null;
		BufferedReader br1 = null;
		try {
			httpsConn1 = (URLConnection) myURL1.openConnection();// 不使用代理
			if (httpsConn1 != null) {
				insr1 = new InputStreamReader(httpsConn1.getInputStream(), "UTF-8");
				br1 = new BufferedReader(insr1);
				String data1 = null;
				String result1 = "";
				while ((data1 = br1.readLine()) != null) {
					result1 = result1 + data1;
				}
//				System.out.println(address);
//				System.out.println(result1);
				//结果返回成功
				if(result1!=null&&!result1.equals("")&&result1.contains("<status>0</status>")){
					if(result1.contains("<city>"))
					city = result1.substring(result1.indexOf("<city>")+6,result1.indexOf("</city>"));
					if(result1.contains("<district>"))
					district = result1.substring(result1.indexOf("<district>")+10,result1.indexOf("</district>"));
					if(result1.contains("<street>"))
					street = result1.substring(result1.indexOf("<street>")+8,result1.indexOf("</street>"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (insr1 != null) {
				insr1.close();
			}
			if (br != null) {
				br1.close();
			}
		}
		return new Object[] { lng, lat,city,district,street };
	}
}
