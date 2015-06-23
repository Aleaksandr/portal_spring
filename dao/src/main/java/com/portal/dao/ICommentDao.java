package com.portal.dao;


import com.portal.pojos.Comment;
import com.portal.pojos.News;
import com.portal.pojos.User;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Extended interface class for CommentDao
 */

public interface ICommentDao extends Dao<Comment> {

    /** Gets the the appropriate List<Comment> by News object */
    List<Comment> getCommentByItem(News nw);

    /** Gets the the appropriate List<Comment> by user parameter value */
    List<Comment> getCommentByUser(User user);
}