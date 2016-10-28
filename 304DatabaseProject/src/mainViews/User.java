package mainViews;

import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by kseniapinski on 2016-10-26.
 */
public interface User {

    //Currently returns int, but can be changed to a more suitable return type in the future.
    Map<String, String[]> generateWorkList();

    Map<String, String[]> generateSampleList();
}
