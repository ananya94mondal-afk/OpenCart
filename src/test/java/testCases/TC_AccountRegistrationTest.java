package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC_AccountRegistrationTest extends BaseTest {

    @Test(groups={"Sanity","Master"})
    public void verifyAccountRegistration() {

        logger.info("************ Execution Started TC_AccountRegistrationTest ******** ");

        try {
            HomePage homepage = new HomePage(getDriver());

            logger.info("*** Click on My Account Link ***");
            homepage.clikOnMyAccountLnk();

            logger.info("*** Click on Registration Link ***");
            homepage.clickOnRegistrationLnk();

            AccountRegistrationPage accountregistrationpage = new AccountRegistrationPage(getDriver());

            logger.info("*** Fill the Registration form ***");

            String firstName = randomAlphabetic();
            String lastName = randomAlphabetic();
            String email = randomAlphabetic() + "@gmail.com";
            String password = randomAlphaNumeric();

            accountregistrationpage.setFirstName(firstName);
            accountregistrationpage.setLastName(lastName);
            accountregistrationpage.setEmail(email);
            accountregistrationpage.setPassword(password);

            accountregistrationpage.setPrivacyPolicy();

            logger.info("*** Click on the Continue button ***");
            accountregistrationpage.clickContinue();

            logger.info("*** Verify the Account Registration Confirmation! ***");

            String actual = accountregistrationpage.getConfirmationMsg();
            String expected = "Your Account Has Been Created!";

            logger.info("Expected: " + expected);
            logger.info("Actual: " + actual);
            if (expected.equals(actual)) {
                Assert.assertTrue(true);
            } else {
                throw new RuntimeException("Mismatch found. Expected: " + expected + " but Actual: " + actual);
            }

        } catch (Exception e) {
        		e.printStackTrace();
            logger.error("Test failed due to exception in execution flow", e);
            Assert.fail();
     
        }
    }
}