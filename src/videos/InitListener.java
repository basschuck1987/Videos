package videos;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import videos.dao.ConnectionManager;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ConnectionManager.close();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("inicijalizacija...");

    	ConnectionManager.open();

		System.out.println("zavrseno!");
    }
	
}
