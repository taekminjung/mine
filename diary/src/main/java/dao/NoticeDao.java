package dao;

import java.util.List;
import java.util.Map;

import vo.Notice;

public class NoticeDao {
	//member_level >= 0 
	public int insertNotice(Notice notice) {
		return 0;
	}
	////member_level >= 0
	public Notice selectnoticeOne(Notice noticeNo) {
		return null;
	}
	//member_level >= 0
	public List<Notice> selectNoticeList(Map<String, Object> paramMap){
		return null;
	}
	//member_level > 0 and pw일치
	public int deleteNotice(Notice notice) {
		return 0;
	}
	//member_level > 0 and pw일치
		public int updateNotice(Notice notice) {
			return 0;
		}
}
