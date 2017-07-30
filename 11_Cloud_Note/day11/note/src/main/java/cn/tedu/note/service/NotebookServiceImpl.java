package cn.tedu.note.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

@Service("notebookService")
public class NotebookServiceImpl
	implements NotebookService{
	
	@Resource
	private NotebookDao notebookDao;
	
	@Resource
	private UserDao userDao;
	
	@Transactional(readOnly=true,
			isolation=Isolation.READ_UNCOMMITTED)
	public List<Map<String, Object>> 
		listNotebooks(String userId) 
		throws UserNotFoundException {
		if(userId==null||userId.trim().isEmpty()){
			throw new UserNameException("id空");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNameException("查无此人");
		}
		return notebookDao
			.findNotebooksByUserId(userId);
	}
	
	@Resource
	private NoteService noteService;
	
	@Transactional(
		propagation=Propagation.REQUIRED)
	public void deleteNotebook(String id) {
		
		List<Map<String, Object>> list=
			noteService.listNotes(id);
		for (Map<String, Object> map : list) {
			String noteId=(String)map.get("id");
			noteService.deleteAll(noteId);
		}
		//String s=null;
		//s.length();
		notebookDao.deleteNotebook(id);
	}
}




