import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DataUtils {
	public static List<Patient> getPatients(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Patient> list = new ArrayList<Patient>();
		try {
			conn = TestJSch.getConnection();
			st = conn.createStatement();
			String sql = " select md.id,CONCAT(IFNULL(p.name,''),IFNULL(c.name,''),IFNULL(t.name,''),IFNULL(co.name,''),IFNULL(md.address,''))"+
					     " from member_patient md "+
						 " left join geo_province p on md.province_id=p.id "+
						 " LEFT JOIN geo_city c on c.id=md.city_id "+
						 " LEFT JOIN geo_district d on  d.id=md.district_id "+
						 " LEFT JOIN geo_town t on t.id=md.town_id "+
						 " LEFT JOIN geo_community co on  co.id=md.community_id "+
						 " where md.town_id is not null and md.town_id!=''"+
						 " and md.passwd is not NULL and (md.latitude is NULL or md.longitude is NULL)";
			rs = st.executeQuery(sql);
		    while(rs.next()){
			      Patient p = new Patient();
				  String id = rs.getString(1);
				  String address = rs.getString(2);
				  p.setId(id);
				  p.setAddress(address);
				  list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
		return list;
	}
	
	public static List<Patient> getTowns(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Patient> list = new ArrayList<Patient>();
		try {
			conn = TestJSch.getConnection();
			st = conn.createStatement();
			String sql = " select gt.id,CONCAT(gp.name,gc.name,gd.name,gt.name),gt.latitude from geo_town gt,geo_district gd,geo_city gc,geo_province gp where gt.district_id=gd.id and gp.id=gc.province_id and gd.city_id=gc.id and gt.name<>'其它' and (gt.latitude is NULL or gt.longitude is NULL or gt.longitude =0) ";
			rs = st.executeQuery(sql);
		    while(rs.next()){
			      Patient p = new Patient();
				  String id = rs.getString(1);
				  String address = rs.getString(2);
				  p.setId(id);
				  p.setAddress(address);
				  list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
		return list;
	}
	
	public static List<Patient> getDistrict(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Patient> list = new ArrayList<Patient>();
		try {
			conn = TestJSch.getConnection();
			st = conn.createStatement();
			String sql = "select gd.id,CONCAT(gp.name,gc.name,gd.name) from geo_district gd,geo_city gc,geo_province gp WHERE gd.city_id=gc.id and gp.id=gc.province_id  and ( gd.latitude is NULL or gd.longitude is null or gd.latitude=0)";
			rs = st.executeQuery(sql);
		    while(rs.next()){
			      Patient p = new Patient();
				  String id = rs.getString(1);
				  String address = rs.getString(2);
				  p.setId(id);
				  p.setAddress(address);
				  list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
		return list;
	}
	public static List<Hospital> getHospitals(){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Hospital> list = new ArrayList<Hospital>();
		try {
			conn = TestJSch.getConnection();
			st = conn.createStatement();
			String sql = "select id,CONCAT(IFNULL(province_name,''),IFNULL(city_name,''),IFNULL(district_name,''),IFNULL(town_name,''),IFNULL(name,'')) from geo_hospital where latitude is NULL or longitude is null";
			rs = st.executeQuery(sql);
		    while(rs.next()){
		    	  Hospital p = new Hospital();
				  String id = rs.getString(1);
				  String address = rs.getString(2);
				  p.setId(id);
				  p.setAddress(address);
				  list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
		return list;
	}

	public static List<Patient> getCitys() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Patient> list = new ArrayList<Patient>();
		try {
			conn = TestJSch.getConnection();
			st = conn.createStatement();
			String sql = "select id,name,latitude,longitude from geo_city  where latitude is NULL or longitude is null";
			rs = st.executeQuery(sql);
		    while(rs.next()){
			      Patient p = new Patient();
				  String id = rs.getString(1);
				  String address = rs.getString(2);
				  p.setId(id);
				  p.setAddress(address);
				  p.setLatitude(rs.getDouble(3)+"");
				  p.setLongitude(rs.getDouble(4)+"");
				  list.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtils.free(rs, st, conn);
		}
		return list;
	}
}
