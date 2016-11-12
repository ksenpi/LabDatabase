package mainViews;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kseniapinski on 2016-11-12.
 */
public class LabManagerTest {
    @Test
    public void addFridge() throws Exception {

        LabManager lb = new LabManager();
        String result = lb.addFridge(-10, 0);

        assertEquals(result, "OK");


        //lb.addFridge(2, -10, 10);           //this works! yay
        //lb.addLabManager("Darius Bird");   //this works! yay
        //lb.addResearcher("Zac Efron");     //this works! yay
        //lb.removeLabManager(10);           //this works! yay
        //lb.start(null);

    }

    @Test
    public void removeFridge() throws Exception {

        LabManager lb = new LabManager();
        String result = lb.removeFridge(5);
        assertEquals(result, "OK");

        String result2 = lb.removeFridge(5);
        assertEquals(result2, "Error_Does_NOT_Exist");

    }

    @Test
    public void addResearcher() throws Exception {

    }

    @Test
    public void addLabManager() throws Exception {

    }

    @Test
    public void removeLabManager() throws Exception {

    }

    @Test
    public void addSampleToBox() throws Exception {

    }

}