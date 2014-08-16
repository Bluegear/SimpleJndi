package bluegear.simplejndi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet(name = "testServlet", urlPatterns = { "/" })
public class SimpleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5376060729315440392L;

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		DataSource dataSource = (DataSource) context
				.getBean("dataSource");
		
		String name = "";
		
		try {
			Connection conn = dataSource.getConnection();
			name = conn.getMetaData().getDatabaseProductName();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print("Simple JNDI: " + name);
	}

}
