
package com.portal.controller;

import com.portal.exception.PersistException;
import com.portal.pojos.News;
import com.portal.pojos.User;
import com.portal.services.NewsManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("/news")
public class NewsController extends Controller {
    private static Logger logger = Logger.getLogger(NewsController.class);

    @Autowired(required = true)
    private NewsManager newsManager;

    @RequestMapping(value = "/addnewsform.form", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNewsForm(ModelMap model) throws PersistException {
        return "/news/addnewsform";
    }

    @RequestMapping(value = "/addnews.form", method = {RequestMethod.GET, RequestMethod.POST})
    public String addNews(@ModelAttribute("title") String title,
                          @ModelAttribute("title4menu") String title4menu,
                          @ModelAttribute("item") String item,
                          ModelMap model, HttpServletRequest request) throws PersistException {
        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));
        User user = (User) model.get("user");
        News regNews = new News();
        regNews.setTitle(title);
        regNews.setTitle4menu(title4menu);
        regNews.setAuthor(user.getEmail());
        regNews.setDate(new Date());
        regNews.setItem(item);
        newsManager.add(regNews);
        logger.info("News After Save: " + regNews);

        return "redirect:/portal/portaln.form";
    }

    @RequestMapping(value = "/deletenews.form", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteNews(HttpServletRequest request, ModelMap model) throws PersistException {

        model.addAllAttributes((ModelMap) request.getSession().getAttribute("model"));
        News delNews = (News) model.get("newsitem");
        if (delNews != null) {
            newsManager.delete(delNews);
            model.clear();
        }

        request.getSession().setAttribute("model", model);

        return "redirect:/portal/portaln.form";
    }
}
