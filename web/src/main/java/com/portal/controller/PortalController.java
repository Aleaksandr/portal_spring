
package com.portal.controller;
import com.portal.exception.PersistException;
import com.portal.pojos.Comment;
import com.portal.pojos.News;
import com.portal.services.CommentManager;
import com.portal.services.NewsManager;
import com.portal.services.UserManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/portal")
public class PortalController extends Controller {
    private static Logger logger = Logger.getLogger(PortalController.class);

    @Autowired(required = true)
    private CommentManager commentManager;

    @Autowired(required = true)
    private NewsManager newsManager;

    @Autowired(required = true)
    private UserManager userManager;

    @RequestMapping(value = "/secure", method = {RequestMethod.GET, RequestMethod.POST})
    public String start(ModelMap model){
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model){
        return "login";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(ModelMap model){
        return "error";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(ModelMap model){
        return "index";
    }

    @RequestMapping(value = "/portaln", method = RequestMethod.GET)
    public String mainPage(ModelMap model, HttpServletRequest request) throws PersistException {
        fillModel(model, request);
        request.getSession().setAttribute("model", model);
        return "portal/main";
    }

    @RequestMapping(value = "/item_id", method = {RequestMethod.GET, RequestMethod.POST})
    public String chengeItem(@ModelAttribute("param") String id, ModelMap model, HttpServletRequest request) throws PersistException {

        List<Comment> commentListByItem;
        News newsitem;
        newsitem = newsManager.get(Integer.valueOf(id));
        commentListByItem = newsitem.getComments();
        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));
        model.put("newsitem", newsitem);
        model.put("commentlist", commentListByItem);
        request.getSession().setAttribute("model", model);

        return "portal/main";
    }

    @RequestMapping(value = "/next", method = {RequestMethod.GET, RequestMethod.POST})
    public String chengePage(@ModelAttribute("page") String action, ModelMap model,
                             HttpServletRequest request) throws PersistException {

        System.out.println("Next action=" + action);
        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));
        model.put("pageCommand", action);
        List<News> newslist = getList(model, action, request);
        model.put("newslist", newslist);
        request.getSession().setAttribute("model", model);

        return "portal/main";
    }

}
