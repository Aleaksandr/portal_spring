package com.portal.services;



import com.portal.pojos.Comment;
import com.portal.pojos.News;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 * Interface of CommentManager
 */

public interface CommentManager {
    List<Comment> getCommentsByItem(News nw);
}
