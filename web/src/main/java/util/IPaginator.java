package util;


import com.portal.exception.PersistException;
import com.portal.pojos.News;

import java.util.List;

/**
 * Created by hirs akeaksandr on 25.04.15.
 */

public interface IPaginator {

    /** use this method for receiving list of News on the page*/
    public List<News> getList(String pageCommand) throws PersistException;
}
