package util;

import com.portal.dao.INewsDao;
import com.portal.exception.PersistException;
import com.portal.pojos.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import util.Const;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hirs akeaksandr on 25.04.15.
 * General interface of business logic
 */

@Controller
public class Paginator implements IPaginator {

    @Autowired
    INewsDao newsDao;

    private static Paginator paginatorInstance;

    Logger logger = Logger.getLogger(Paginator.class);

    List<News> listNews;
    Integer pageFirst;
    Integer listSize;
    HttpSession session;

    private Paginator(){
    }
    private Paginator(HttpSession session) {
        this.session = session;
    }

    public static synchronized Paginator getInstance(HttpSession session){
        if (paginatorInstance == null){
            paginatorInstance = new Paginator(session);
        }
        return paginatorInstance;
    }

    /**
     * use this method for receiving list of pagination numbers.
     * This numbers are used in view
     * @param pageCommand next or prev commands
     * @return
     */

    public List<News> getList(String pageCommand) throws PersistException {
        logger.info(newsDao);
        listSize = newsDao.loadAll().size();
        listNews = new ArrayList<News>();

        if (session.getAttribute("pagefirst") != null){
            pageFirst = (Integer) session.getAttribute("pagefirst");
        } else {
            pageFirst = Const.ZERO;
        }

        if (pageCommand == null) {
            listNews = newsDao.getNewsByPeriod(pageFirst, pageFirst + Const.MENUSIZE);
        } else if (pageCommand.matches("next")) {
            pageFirst = (listSize > pageFirst + Const.MENUSIZE)? pageFirst + Const.MENUSIZE: pageFirst;
            listNews = newsDao.getNewsByPeriod(pageFirst, pageFirst + Const.MENUSIZE);
            logger.info("Save FirstPage: "+ pageFirst);
            session.setAttribute("pagefirst", pageFirst);
        } else if (pageCommand.matches("prev")) {
            pageFirst = (pageFirst >= Const.MENUSIZE)? pageFirst - Const.MENUSIZE: pageFirst;
            listNews = newsDao.getNewsByPeriod(pageFirst, pageFirst + Const.MENUSIZE);
            logger.info("Save FirstPage: "+ pageFirst);
            session.setAttribute("pagefirst", pageFirst);
        }
        return listNews;
    }
}