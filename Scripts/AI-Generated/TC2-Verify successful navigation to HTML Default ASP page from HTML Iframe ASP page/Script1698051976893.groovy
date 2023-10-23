import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

'Initialize test session: Open browser and set view port'

@com.kms.katalon.core.annotation.SetUp
def setup() {
	WebUI.openBrowser('')
	WebUI.setViewPortSize(1920, 1080)	
}

'step 1: Navigate to Page html html iframe asp'

WebUI.navigateToUrl(GlobalVariable.application_domain + '/html/html_iframe.asp')

'step 2: At Page html html iframe asp click on hyperlink default asp --> navigate to Page html default asp'

testObj = findTestObject('Object Repository/Page_html_html_iframe_asp/hyperlink_default_asp')

WebUI.delay(3)

WebUI.takeScreenshot()

WebUI.verifyElementPresent(testObj, 20, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.verifyMatch(WebUI.getUrl(), '.*/html/html_iframe.asp(?:#.*)?(?:\\?.*)?$', true)

WebUI.enhancedClick(testObj)

'step 3: Add visual checkpoint at Page html default asp'

WebUI.takeFullPageScreenshotAsCheckpoint('TC2-Verify successful navigation to HTML Default ASP page from HTML Iframe ASP page_visual_checkpoint')

'Terminate test session: Close browser'

@com.kms.katalon.core.annotation.TearDown
def teardown() {
	WebUI.closeBrowser()
}
