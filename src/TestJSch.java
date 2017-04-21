import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class TestJSch {

	static int lport = 3333;//鏈湴绔彛
	static String rhost = "10.168.49.204";//杩滅▼MySQL鏈嶅姟鍣�
	static int rport = 3309;//杩滅▼MySQL鏈嶅姟绔彛
	static JSch jsch = new JSch();

	public static void go() {
		String user = "admin";//SSH杩炴帴鐢ㄦ埛鍚�
//		String password = "password";//SSH杩炴帴瀵嗙爜
		String host = "120.26.56.80";//SSH鏈嶅姟鍣�
		int port = 2209;//SSH璁块棶绔彛
		try {
			Session session = jsch.getSession(user, host, port);
			jsch.addIdentity("C:\\Users\\Administrator\\.ssh\\id_rsa");
//			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();
			System.out.println(session.getServerVersion());//杩欓噷鎵撳嵃SSH鏈嶅姟鍣ㄧ増鏈俊鎭�
			int assinged_port = session.setPortForwardingL(lport, rhost, rport);
			System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		go();
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3333/docplus?characterEncoding=utf8&autoReconnect=true", "docplus", "H1E4cPEAlJYL6k1l");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return conn;
		}
	}

}