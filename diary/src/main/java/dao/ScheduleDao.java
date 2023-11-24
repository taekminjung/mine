package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ScheduleDao {
   public List<Map<String, Object>> selectScheduleByMonth(String memberId, int year, int month) {
      List<Map<String, Object>> list = new ArrayList<>();

      Connection conn = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;

      try {
         Context context = new InitialContext();
         // context.xml에서 커넥션풀 객체 로드
         DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
         conn = ds.getConnection();

         String sql = """
                  SELECT
                     DAY(schedule_date) scheduleDay,
                     COUNT(*) cnt,
                     GROUP_CONCAT(substr(schedule_memo, 1, 5)) memo
                  FROM schedule
                  WHERE member_id = ? 
                  AND YEAR(schedule_date) = ?
                  AND MONTH(schedule_date) = ?
                  GROUP BY schedule_date
                  ORDER BY schedule_date ASC
            """;
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, memberId);
         stmt.setInt(2, year);
         stmt.setInt(3, month+1);
         System.out.println(stmt + " <-- stmt");
         rs = stmt.executeQuery();
         while(rs.next()) {
            Map<String, Object> m = new HashMap<>();
            m.put("scheduleDay", rs.getString("scheduleDay"));
            m.put("cnt", rs.getInt("cnt"));
            m.put("memo", rs.getString("memo"));
            list.add(m);
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            rs.close();
            stmt.close();
            conn.close();
         } catch (SQLException e1) {
            e1.printStackTrace();
         }
      }
      return list;
   }
    public List<Map<String, Object>> selectScheduleByDay(String memberId, int year, int month, int day ){
    	List<Map<String, Object>> list = new ArrayList<>();
    	
    	 Connection conn = null;
         PreparedStatement stmt = null;
         ResultSet rs = null;
         
         try {
             Context context = new InitialContext();
             // context.xml에서 커넥션풀 객체 로드
             DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/diary");
             conn = ds.getConnection();

             String sql = """
                      SELECT
             		     YEAR(schedule_date) scheduleYear,
             		     MONTH(schedule_date) scheduleMonth,
                         DAY(schedule_date) scheduleDay,
                         schedule_memo memo,
                         schedule_no scheduleNo,
                         member_id memberId
                      FROM schedule
                      WHERE member_id = ? 
                      AND YEAR(schedule_date) = ?
                      AND MONTH(schedule_date) = ?
                      AND DAY(schedule_date)= ?
                      ORDER BY schedule_date ASC
                """;
             stmt = conn.prepareStatement(sql);
             stmt.setString(1, memberId);
             stmt.setInt(2, year);
             stmt.setInt(3, month);
             stmt.setInt(4, day);
             System.out.println(stmt+"<--stmt");
             rs = stmt.executeQuery();
             while(rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("scheduleYear", rs.getString("scheduleYear"));
                m.put("scheduleMonth", rs.getString("scheduleMonth"));
                m.put("scheduleDay", rs.getString("scheduleDay"));
                m.put("memo", rs.getString("memo"));
                m.put("scheduleNo", rs.getInt("scheduleNo"));
                m.put("memberId", rs.getString("memberId"));
                
                System.out.println("Memo: " + m.get("memo")+"<-- memo");
                list.add(m);
                System.out.println(m+"<--map");
             }
          } catch (Exception e) {
             e.printStackTrace();
          } finally {
             try {
                rs.close();
                stmt.close();
                conn.close();
             } catch (SQLException e1) {
                e1.printStackTrace();
             }
          }
          return list;
    }
	 public int updateSchedule(String memo, int scheduleNo,  String memberId, int targetY, int targetM, int targetD) {
		 int row = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			//커넥션풀
			try {
				//톰캣 context.xml 설정 로드
				Context context = new InitialContext();
				//context.xml 커넥션 풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				//conn 디버깅
				System.out.println(conn+" <--conn");
				
				String sql = "UPDATE schedule SET schedule_memo=? WHERE schedule_no=? AND member_id= ? AND YEAR(schedule_date)=? and MONTH(schedule_date)=? and DAY(schedule_date)=?;";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memo);
				stmt.setInt(2, scheduleNo);
				stmt.setString(3, memberId);
				stmt.setInt(4, targetY);
				stmt.setInt(5, targetM);
				stmt.setInt(6, targetD);

				row = stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			stmt.close();
			conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	 
	 public int DeleteSchedule(int scheduleNo, String memberId) {
		 int row = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			//커넥션풀
			try {
				//톰캣 context.xml 설정 로드
				Context context = new InitialContext();
				//context.xml 커넥션 풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				//conn 디버깅
				System.out.println(conn+" <--conn");
				
				String sql = "DELETE FROM schedule WHERE schedule_no=? AND member_id=? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, scheduleNo);
				stmt.setString(2, memberId);
				row = stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			stmt.close();
			conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	 public int InsertSchedule(String memberId, String scheduleMemo, int targetY, int targetM, int targetD) {
		 int row = 0;
			Connection conn = null;
			PreparedStatement stmt = null;
			//커넥션풀
			try {
				//톰캣 context.xml 설정 로드
				Context context = new InitialContext();
				//context.xml 커넥션 풀 객체 로드
				DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/diary");
				conn = ds.getConnection();
				//conn 디버깅
				System.out.println(conn+" <--conn");
				
				String sql = "INSERT INTO schedule( member_id, YEAR(schedule_date), MONTH(schedule_date), DAY(schedule_date), schedule_memo)VALUE(?,?,?,?,?);";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, memberId);
				stmt.setInt(2, targetY);
				stmt.setInt(3, targetM);
				stmt.setInt(4, targetD);
				stmt.setString(5, scheduleMemo);
				row = stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
			stmt.close();
			conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}