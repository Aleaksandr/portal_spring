package com.portal.controller;

import com.portal.exception.PersistException;
import com.portal.pojos.Comment;
import com.portal.pojos.News;
import com.portal.pojos.User;
import com.portal.services.NewsManager;
import com.portal.services.UserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import util.Const;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NotePad on 22.06.2015.
 */
public class Controller {

    private static Logger logger = Logger.getLogger(Controller.class);

    @Autowired(required = true)
    private NewsManager newsManager;

    @Autowired(required = true)
    private UserManager userManager;

    public void fillModel(ModelMap model, HttpServletRequest request) throws PersistException {

        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));
        String pageCommand = (String) model.get("pageCommand");
        List<News> newslist = getList(model, pageCommand, request);
        List<Comment> commentListByItem = null;
        List<User> userlist;
        News newsitem;

        UserDetails curUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String usertype = curUser.getAuthorities().toString();
        String email = curUser.getUsername();
        System.out.println("User: "+ email);
        User user = userManager.getUserByEmail(email);
        userlist = userManager.loadAll();
        System.out.println("UserList: "+ userlist);


        if (model.get("newsitem") == null) {
            newsitem = newslist.get(0);
        } else {
            newsitem = (News) model.get("newsitem");
        }
        commentListByItem = newsitem.getComments();

        model.put("userlist", userlist);
        model.put("user", user);
        model.put("newsitem", newsitem);
        model.put("newslist", newslist);
        model.put("commentlist", commentListByItem);
        model.remove("pageCommand");
        model.put("usertype", usertype);
        request.getSession().setAttribute("model", model);

    }

    public List<News> getList(ModelMap model, String pageCommand, HttpServletRequest request) throws PersistException {
        List<News> listNews;
        Integer pageFirst = null;
        Integer listSize;

        listSize = newsManager.getAll().size();
        listNews = new ArrayList<News>();
        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));

        if (model.get("pagefirst") != null){
            pageFirst = (Integer) model.get("pagefirst");
            System.out.println("pageFirst: "+pageFirst);
        } else {
            pageFirst = Const.ZERO;
            System.out.println("pageFirst: "+pageFirst);
        }
        if (pageCommand == null) {
            if (pageFirst >= listSize) {
                pageFirst = pageFirst - Const.MENUSIZE;
            }
            Integer b = (pageFirst + Const.MENUSIZE <= listSize)?pageFirst + Const.MENUSIZE: listSize-pageFirst;
            listNews = newsManager.getNewsByPeriod(pageFirst, b) ;
        } else if (pageCommand.matches("next")) {
            logger.info("FirstPage: "+ pageFirst);
            logger.info("ListSize: "+ listSize);
            pageFirst = (pageFirst + Const.MENUSIZE < listSize)? pageFirst + Const.MENUSIZE: pageFirst;
            Integer b = (pageFirst + Const.MENUSIZE <= listSize)?pageFirst + Const.MENUSIZE: listSize-pageFirst;
            listNews = newsManager.getNewsByPeriod(pageFirst, b);
            logger.info("Save FirstPage: "+ pageFirst);
            model.put("pagefirst", pageFirst);
            System.out.println("pageFirst after next: " + pageFirst);
        } else if (pageCommand.matches("prev")) {
            pageFirst = (pageFirst >= Const.MENUSIZE)? pageFirst - Const.MENUSIZE: pageFirst;
            Integer b = (pageFirst + Const.MENUSIZE <= listSize)?pageFirst + Const.MENUSIZE: listSize-pageFirst;
            listNews = newsManager.getNewsByPeriod(pageFirst, b);
            logger.info("Save FirstPage: "+ pageFirst);
            model.put("pagefirst", pageFirst);
        }
        model.put("newslist", listNews);
        request.getSession().setAttribute("model", model);
        return listNews;
    }
}
