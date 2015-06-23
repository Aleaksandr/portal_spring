package com.portal.services.simple;

import com.portal.dao.ICommentDao;
import com.portal.pojos.Comment;
import com.portal.pojos.News;
import com.portal.services.CommentManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended class of Comment
 */
@Service("commentManager")
@Transactional(propagation = Propagation.REQUIRED)
public class SimpleCommentManager implements CommentManager {

	private static Logger logger = Logger.getLogger(SimpleCommentManager.class);

	@Autowired
	ICommentDao commentDao;


	@Override
	public List<Comment> getCommentsByItem(News nw) {
		List<Comment> cList = null;
		cList = commentDao.getCommentByItem(nw);
		return cList;
	}
}
