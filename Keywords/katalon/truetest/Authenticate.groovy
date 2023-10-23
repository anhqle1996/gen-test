package katalon.truetest

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.internal.PathUtil
import com.kms.katalon.core.util.KeywordUtil
import groovy.transform.CompileStatic

import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver


class Authenticate {
	/**
	 * basic authentication
	 */
	@Keyword
	def basic_auth(String url, String userName, String password) {
		basic_auth(url, userName, password, FailureHandling.STOP_ON_FAILURE)
	}
	
	/**
	 * basic authentication with FailureHandling
	 */
	@Keyword
	def basic_auth(String url, String userName, String password, FailureHandling failureHandling) {
		KeywordUtil.logInfo("Authenticating using basic-auth")
		try {
			String usernamePasswordURL = getAuthenticatedUrl(PathUtil.getUrl(url, "https"), userName, password)
			WebDriver webDriver = DriverFactory.getWebDriver()
			webDriver.get(usernamePasswordURL)
			KeywordUtil.markPassed("Authenticated successfully!")
		} catch (Exception exception) {
			KeywordUtil.markFailed("Authenticated failed!")
			if (failureHandling == FailureHandling.STOP_ON_FAILURE)
				throw exception
		}
	}

	@CompileStatic
	private String getAuthenticatedUrl(URL url, String userName, String password) {
		StringBuilder getAuthenticatedUrl = new StringBuilder()

		getAuthenticatedUrl.append(url.getProtocol())
		getAuthenticatedUrl.append("://")
		getAuthenticatedUrl.append(userName)
		getAuthenticatedUrl.append(":")
		getAuthenticatedUrl.append(password)
		getAuthenticatedUrl.append("@")
		getAuthenticatedUrl.append(url.getHost())
		int portNumber = url.getPort()
		getAuthenticatedUrl.append(portNumber == -1 ? "" : ":" + portNumber)
		getAuthenticatedUrl.append(url.getPath())

		return getAuthenticatedUrl.toString()
	}
}

