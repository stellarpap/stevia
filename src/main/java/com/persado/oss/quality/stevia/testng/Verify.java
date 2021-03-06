package com.persado.oss.quality.stevia.testng;

/*
 * #%L
 * Stevia QA Framework - Core
 * %%
 * Copyright (C) 2013 - 2014 Persado
 * %%
 * Copyright (c) Persado Intellectual Property Limited. All rights reserved.
 *  
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  
 * * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *  
 * * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *  
 * * Neither the name of the Persado Intellectual Property Limited nor the names
 * of its contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *  
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */


import com.persado.oss.quality.stevia.selenium.core.SteviaContext;
import com.persado.oss.quality.stevia.selenium.core.WebComponent;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;

public class Verify extends WebComponent {

    /**
     * The log.
     */
    private static final Logger VERIFY_LOG = LoggerFactory.getLogger("VerifyLog");
    /**
     * The pass color.
     */
    private static final String PASS_COLOR = "Lime";
    /**
     * The fail color.
     */
    private static final String FAIL_COLOR = "OrangeRed";

    /*************************************
     * Constants used for logging messages
     *****************************************************/

    private static final String ELEMENT_LOCATOR = "The element with locator '";

    private static final String DESIRED_ELEMENT = "The desired element";

    private static final String TEXT = "The text '";

    private static final String IS_VISIBLE = "' is visible!";

    private static final String IS_NOT_VISIBLE = "' is not visible!";

    private static final String FOUND = "' was found!";

    private static final String NOT_FOUND = "' was not found!";

    private static final String FOUND_WITH_TEXT = "' was found with text '";

    private static final String NOT_FOUND_WITH_TEXT = "' was not found with text '";

    private static final String FOUND_CONTAINING_TEXT = "' was found to contain text '";

    private static final String NOT_FOUND_CONTAINING_TEXT = "' was not found to contain text '";

    private static final String FOUND_WITH_VALUE = "' was found with value '";

    private static final String NOT_FOUND_WITH_VALUE = "' was not found with value '";

    private static final String FOUND_CONTAINING_VALUE = "' was found to contain value '";

    private static final String NOT_FOUND_CONTAINING_VALUE = "' was not found to contain value '";

    private static final String FOUND_EDITABLE = "' was found editable!";

    private static final String FOUND_DISABLED = "' was found disabled!";

    private static final String FOUND_SELECTED = "' was found selected!";

    private static final String NOT_FOUND_SELECTED = "' was not found selected!";

    private static final String IS_EQUAL = "' is equal with '";

    private static final String IS_NOT_EQUAL = "' is not equal with '";

    private static final String ELEMENT_LOCATOR_ATTRIBUTE = "' attribute of element with locator '";

    private static final String CONTAINED_IN_LIST = "' is contained in the list'!";

    private static final String NOT_CONTAINED_IN_LIST = "' is not contained in the list'!";

    private static final String FOUND_WITH_OPTIONS = "' was found with options '";

    private static final String NOT_FOUND_WITH_OPTIONS = "' was not found options '";

    private static final String FOUND_WITH_SELECTED_OPTIONS = "' was found with selected options '";

    private static final String NOT_FOUND_WITH_SELECTED_OPTIONS = "' was not found with selected options '";

    private static final String FOUND_WITH_SELECTED_OPTION = "' was found with selected option '";

    private static final String NOT_FOUND_WITH_SELECTED_OPTION = "' was not found with selected option '";

    private static final String FOUND_ALERT_WITH_MESSAGE = "There was an alert with message '";

    private static final String NOT_FOUND_ALERT_WITH_MESSAGE = "There was an alert with message '";

    private static final String TABLE_ELEMENT = "The table element '";

    private static final String HAS_ATTRIBUTE = "' has the attribute '";

    private static final String HAS_NOT_ATTRIBUTE = "' has not the attribute '";

    private static final String SATISFY_REGEX = "' satisfy the regEx '";

    private static final String NOT_SATISFY_REGEX = "' does not satusfy the reg Ex '";


    /**
     * Instantiates a new verify.
     */
    public Verify() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    /**
     * Info.
     *
     * @param message the message
     */
    public void info(String message) {
        VERIFY_LOG.info(message);
        Reporter.log("<p class=\"testOutput\" style=\"color:green; font-size:1em;\">" + message + "</p>");
    }

    /**
     * Warn.
     *
     * @param message the message
     */
    public void warn(String message) {
        VERIFY_LOG.warn(message);
        Reporter.log("<p class=\"testOutput\" style=\"color:orange; font-size:1em;\">" + message + "</p>");
    }

    /**
     * Error.
     *
     * @param message the message
     */
    public void error(String message) {
        VERIFY_LOG.error(message);
        Reporter.log("<p class=\"testOutput\" style=\"color:red; font-size:1em;\">" + message + "</p>");
    }

    /**
     * Check that text is present.
     *
     * @param text the text
     */
    public void textPresent(String text) {
        try {
            Assert.assertTrue(controller().isTextPresent(text));
            info(TEXT + text + FOUND);
        } catch (AssertionError e) {
            error(TEXT + text + NOT_FOUND);
            throw e;
        }
    }

    /**
     * Check that text is not present.
     *
     * @param text the text under examination
     */
    public void textNotPresent(String text) {
        try {
            Assert.assertTrue(controller().isTextNotPresent(text));
            info(TEXT + text + NOT_FOUND);
        } catch (AssertionError e) {
            error(TEXT + text + FOUND);
            throw e;
        }
    }

    /**
     * Check that an Element is present
     *
     * @param locator the locator of an element
     */
    public void elementPresent(String locator) {
        try {
            Assert.assertTrue(controller().isComponentPresent(locator));
            info(ELEMENT_LOCATOR + locator + FOUND);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + NOT_FOUND);
            throw e;
        }
    }

    /**
     * Check that an Element is present for a specific timeframe.
     *
     * @param locator the locator Check that an Element is present
     * @param seconds the time in seconds that the element must remain present
     */
    public void elementPresent(String locator, long seconds) {
        try {
            Assert.assertTrue(controller().isComponentPresent(locator, seconds));
            info(ELEMENT_LOCATOR + locator + FOUND);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + NOT_FOUND);
            throw e;
        }
    }

    /**
     * Check that an Element is not present.
     *
     * @param locator the locator of the element
     */
    public void elementNotPresent(String locator) {
        try {
            Assert.assertTrue(controller().isComponentNotPresent(locator));
            info(ELEMENT_LOCATOR + locator + NOT_FOUND);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + FOUND);
            throw e;
        }
    }

    /**
     * Check that an Element is visible.
     *
     * @param locator the locator of an element
     */
    public void elementVisible(String locator) {
        try {
            Assert.assertTrue(controller().isComponentVisible(locator));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + IS_VISIBLE);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + IS_NOT_VISIBLE);
            throw e;
        }
    }

    /**
     * Check that an Element is visible.
     *
     * @param locator the locator of the element
     * @param seconds the least time in seconds that element must remain visible
     */
    public void elementVisible(String locator, long seconds) {
        try {
            Assert.assertTrue(controller().isComponentVisible(locator, seconds));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + IS_VISIBLE);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + IS_NOT_VISIBLE);
            throw e;
        }
    }

    /**
     * Check that an Element is visible.
     *
     * @param element the desired element
     */
    public void elementVisible(WebElement element) {
        try {
            Assert.assertTrue(element.isDisplayed());
            info(DESIRED_ELEMENT + IS_VISIBLE);
        } catch (AssertionError e) {
            error(DESIRED_ELEMENT + IS_NOT_VISIBLE);
            throw e;
        }
    }

    /**
     * Check that an Element is visible.
     *
     * @param parent       the parent element
     * @param childLocator the child locator
     */
    public void elementVisible(WebElement parent, String childLocator) {
        try {
            Assert.assertTrue(controller().findChildElement(parent, childLocator).isDisplayed());
            info("Child element with locator '" + childLocator + IS_VISIBLE);
        } catch (AssertionError | NoSuchElementException e) {
            error("Child element with locator '" + childLocator + IS_NOT_VISIBLE);
            throw e;
        }
    }

    /**
     * Check that an Element is not visible.
     *
     * @param locator the locator of the element
     */
    public void elementNotVisible(String locator) {
        try {
            Assert.assertTrue(controller().isComponentNotVisible(locator));
            info(ELEMENT_LOCATOR + locator + IS_NOT_VISIBLE);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + IS_VISIBLE);
            throw e;
        }
    }

    /**
     * Check that an Element is not visible.
     *
     * @param parent       the parent element
     * @param childLocator the child locator
     */
    public void elementNotVisible(WebElement parent, String childLocator) {
        try {
            Assert.assertTrue(controller().findAllChildElements(parent,childLocator).size() == 0 || !controller().findChildElement(parent,childLocator).isDisplayed());
            info("Child element with locator '" + childLocator + IS_NOT_VISIBLE);
        } catch (AssertionError e) {
            error("Child element with locator '" + childLocator + IS_VISIBLE);
            throw e;
        }
    }


    /**
     * Check that an Element is not visible.
     *
     * @param element the desired element
     */
    public void elementNotVisible(WebElement element) {
        try {
            Assert.assertFalse(element.isDisplayed());
            info(DESIRED_ELEMENT + IS_NOT_VISIBLE);
        } catch (AssertionError e) {
            error(DESIRED_ELEMENT + IS_VISIBLE);
            throw e;
        }
    }


    /**
     * Check that an Element is not visible.
     *
     * @param locator the locator of the element
     * @param seconds the max time in seconds after which element should become invisible
     */
    public void elementNotVisible(String locator, long seconds) {
        try {
            Assert.assertTrue(controller().isComponentNotVisible(locator, seconds));
            info(ELEMENT_LOCATOR + locator + IS_NOT_VISIBLE);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + IS_VISIBLE);
            throw e;
        }
    }

    /**
     * Check the value of an element .
     *
     * @param locator       the element locator
     * @param expectedValue the expected value
     */
    public void value(String locator, String expectedValue) {
        try {
            Assert.assertEquals(controller().getInputValue(locator), expectedValue);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_VALUE + expectedValue + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_VALUE + expectedValue + "'!");
            throw e;
        }
    }

    /**
     * Check text in an element.
     *
     * @param locator      the locator of the element
     * @param expectedText the expected text
     */
    public void text(String locator, String expectedText) {
        try {
            Assert.assertEquals(controller().getText(locator), expectedText);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_TEXT + expectedText + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_TEXT + expectedText + "'!");
            throw e;
        }
    }

    /**
     * Check text in an element with regEx.
     *
     * @param locator  the locator of the element
     * @param regEx the regular expression
     */

    public void textRegEx(String locator, String regEx) {

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(SteviaContext.getWebController().getText(locator));

        try {
            Assert.assertTrue(m.matches());
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + SATISFY_REGEX + " : " + regEx + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_SATISFY_REGEX + " : " + regEx + "'!");
            throw e;
        }
    }


    /**
     * Check text in an element based on presence (not visibility) of the element
     *
     * @param locator      the locator of the element
     * @param expectedText the expected text
     */
    public void textPresenceBased(String locator, String expectedText) {
        try {
            Assert.assertEquals(controller().waitForElementPresence(locator).getText(), expectedText);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_TEXT + expectedText + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_TEXT + expectedText + "'!");
            throw e;
        }
    }

    /**
     * Check text in an element.
     *
     * @param element      the web element
     * @param expectedText the expected text
     */
    public void text(WebElement element, String expectedText) {
        try {
            Assert.assertEquals(element.getText(), expectedText);
            info(DESIRED_ELEMENT + FOUND_WITH_TEXT + expectedText + "'!");
        } catch (AssertionError e) {
            error(DESIRED_ELEMENT + NOT_FOUND_WITH_TEXT + expectedText + "'!");
            throw e;
        }
    }

    /**
     * Check trimmed text in an element.
     *
     * @param locator      the locator of the element
     * @param expectedText the expected text
     */
    public void trimmedText(String locator, String expectedText) {
        try {
            Assert.assertEquals(controller().getText(locator).trim(), expectedText);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_TEXT + expectedText + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_TEXT + expectedText + "'!");
            throw e;
        }
    }

    /**
     * Check that an element contains a specific text.
     *
     * @param locator      the locator of the element
     * @param expectedText the expected text
     */
    public void containsText(String locator, String expectedText) {
        if (controller().getText(locator).contains(expectedText)) {
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_CONTAINING_TEXT + expectedText + "'!");
        } else {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_CONTAINING_TEXT + expectedText + "'!");
            throw new AssertionError(ELEMENT_LOCATOR + locator + NOT_FOUND_CONTAINING_TEXT + expectedText + "'!");
        }
    }

    /**
     * Checks if the specified input element is editable
     *
     * @param locator the locator of the element
     */
    public void editable(String locator) {
        try {
            Assert.assertTrue(controller().isComponentEditable(locator));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_EDITABLE);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + FOUND_DISABLED);
            throw e;
        }
    }

    /**
     * Checks if a component disabled. This means that no write/edit actions are allowed
     *
     * @param locator the locator of the component
     */
    public void disabled(String locator) {
        try {
            Assert.assertTrue(controller().isComponentDisabled(locator));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_DISABLED);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + FOUND_EDITABLE);
            throw e;
        }
    }

    /**
     * Check that an element (check box, radio button etc) is selected.
     *
     * @param locator the locator of the element
     */
    public void selected(String locator) {
        try {
            Assert.assertTrue(controller().isComponentSelected(locator));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_SELECTED);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_SELECTED);
            throw e;
        }
    }

    /**
     * Check that an element (check box, radio button etc) is selected.
     *
     * @param locator the locator of the element
     */
    public void selectedAndPresent(String locator) {
        try {
            Assert.assertTrue(controller().waitForElementPresence(locator).isSelected());
            info(ELEMENT_LOCATOR + locator + FOUND_SELECTED);
        } catch (AssertionError e) {
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_SELECTED);
            throw e;
        }
    }

    /**
     * Check that a check box, field, radio button is not selected.
     *
     * @param locator the locator of the field you want to find unchecked
     */
    public void notSelected(String locator) {
        try {
            Assert.assertTrue(controller().isComponentNotSelected(locator));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + NOT_FOUND_SELECTED);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + FOUND_SELECTED);
            throw e;
        }
    }

    /**
     * Text is contained in a table, under a specific table header, in the row of a specific element
     *
     * @param locator      the locator of the table
     * @param elementName  The element that is situated in the same row with the expected text
     * @param headerName   the header name under which the element is expected
     * @param expectedText the expected text
     */
    public void tableElementTextUnderHeader(String locator, String elementName, String headerName, String expectedText) {
        try {
            Assert.assertEquals(controller().getTableElementTextUnderHeader(locator, elementName, headerName), expectedText);
            highlightPass(controller().getTableElementSpecificHeaderLocator(locator, elementName, headerName));
            info(TABLE_ELEMENT + elementName + FOUND_WITH_TEXT + expectedText + "' for header '" + headerName + "'!");
        } catch (AssertionError e) {
            highlightFail(controller().getTableElementSpecificHeaderLocator(locator, elementName, headerName));
            error(TABLE_ELEMENT + elementName + NOT_FOUND_WITH_TEXT + expectedText + "' for header '" + headerName + "'!");
            throw e;
        }
    }

    /**
     * Text is contained in table in specific row and column.
     *
     * @param locator      the locator of the table
     * @param row          the row of the table where text is expected
     * @param column       the column of the table where text is expected
     * @param expectedText the expected text for the specified row and column of the table
     */
    public void tableElementTextForSpecificRowAndColumn(String locator, String row, String column, String expectedText) {
        try {
            Assert.assertEquals(controller().getTableElementTextForRowAndColumn(locator, row, column), expectedText);
            highlightPass(controller().getTableElementSpecificRowAndColumnLocator(locator, row, column));
            info("The table element in row '" + row + "' and column '" + column + FOUND_WITH_TEXT + expectedText + "'!");
        } catch (AssertionError e) {
            highlightFail(controller().getTableElementSpecificRowAndColumnLocator(locator, row, column));
            error("The table element in row '" + row + "' and column '" + column + NOT_FOUND_WITH_TEXT + expectedText + "'!");
            throw e;
        }
    }

    /**
     * Table element row. Check that an element can be found at a specific table row
     *
     * @param locator     the locator
     * @param elementName the element name you expect to find in table
     * @param expectedRow the expected row for your element
     */
    public void tableElementAtRowPosition(String locator, String elementName, String expectedRow) {
        try {
            Assert.assertEquals(controller().getTableElementRowPosition(locator, elementName), expectedRow);
            info(TABLE_ELEMENT + elementName + "' was found in row '" + expectedRow + "'!");
        } catch (AssertionError e) {
            error(TABLE_ELEMENT + elementName + "' was not found in row '" + expectedRow + "'!");
            throw e;
        }
    }

    /**
     * Table elements. Check that current table elements are the expected ones
     *
     * @param locator       the locator of the table
     * @param expectedArray the expected array for comparison with the actual array
     */
    public void tableElements(String locator, String[][] expectedArray) {
        String[][] actualArray = controller().getTableElements2DArray(locator);
        Assert.assertEquals(actualArray.length, expectedArray.length, "The arrays have not the same length");
        boolean errorFound = false;
        for (int i = 0; i < expectedArray.length; i++) {
            for (int j = 0; j < expectedArray[i].length; j++) {
                if (expectedArray[i][j].equals(actualArray[i][j])) {
                    highlightPass(controller().getTableElementSpecificRowAndColumnLocator(locator, String.valueOf(i + 1), String.valueOf(j + 1)));
                } else {
                    highlightFail(controller().getTableElementSpecificRowAndColumnLocator(locator, String.valueOf(i + 1), String.valueOf(j + 1)));
                    error("The table elements for row " + (i + 1) + " and column " + (j + 1) + " are not equal! EXPECTED VALUE: " + expectedArray[i][j] + " - ACTUAL VALUE: " + actualArray[i][j]);
                    errorFound = true;
                }
            }
        }
        if (errorFound) {
            throw new AssertionError("The table elements are not equal");
        }
        info("The table elements are equal");
    }


    /**
     * @param locator
     * @param headerName
     * @param tableRecords
     */
    public void allTableRecordsUnderHeader(String locator, String headerName, List<String> tableRecords) {
        List<String> actualRecords = controller().getTableRecordsUnderHeader(locator, headerName);
        try {
            Assert.assertEquals(actualRecords, tableRecords);
            for (String element : tableRecords) {
                highlightPass(controller().getTableElementSpecificHeaderLocator(locator, element, headerName));
            }
            info("The table records under header '" + headerName + "' are correct");
        } catch (AssertionError e) {
            error("The table records under header '" + headerName + "' are not correct");
            throw e;
        }
    }


    /**
     * Text is contained in table, in a column with a specific header.
     *
     * @param locator      the locator of the table
     * @param headerName   the header name of the column, where the text is expected
     * @param expectedText the expected text in the specified location
     */
    public void textIsContainedInTableRecordsUnderHeader(String locator, String headerName, String expectedText) {
        List<String> records = controller().getTableRecordsUnderHeader(locator, headerName);
        if (records.contains(expectedText)) {
            highlightPass(controller().getTableElementSpecificHeaderLocator(locator, expectedText, headerName));
            info("The '" + expectedText + "' was found with in table with locator '" + locator + "' under header '" + headerName + "'");
            return;
        }
        highlightFail(locator);
        error("The '" + expectedText + "' was not found with in table with locator '" + locator + "' under header '" + headerName + "'");
        throw new AssertionError();
    }

    /**
     * Text is not contained in a table, in a column with a specific header
     *
     * @param locator      the locator of the table
     * @param headerName   the header name of the column, where the text is not expected
     * @param expectedText the text that is not expected in the specified location
     */
    public void textIsNotContainedInTableRecordsUnderHeader(String locator, String headerName, String expectedText) {
        List<String> records = controller().getTableRecordsUnderHeader(locator, headerName);
        if (!records.contains(expectedText)) {
            highlightPass(locator);
            info("The '" + expectedText + "' was not found not with in table with locator '" + locator + "' under header '" + headerName + "'");
            return;
        }
        highlightFail(controller().getTableElementSpecificHeaderLocator(locator, expectedText, headerName));
        error("The '" + expectedText + "' was found with in table with locator '" + locator + "' under header '" + headerName + "'");
        throw new AssertionError();
    }

    /**
     * List options. Check all options available in a list
     *
     * @param locator         the locator of the list
     * @param expectedOptions the expected available options to found in a list
     */
    public void allListOptions(String locator, List<String> expectedOptions) {
        try {
            Assert.assertEquals(controller().getAllListOptions(locator), expectedOptions);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_OPTIONS + expectedOptions + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_OPTIONS + expectedOptions + "'!");
            throw e;
        }
    }

    /**
     * List options. Check all options available in a list without considering the elements order
     *
     * @param locator         the locator of the list
     * @param expectedOptions the expected available options to found in a list
     */
    public void allListOptionsNoOrder(String locator, List<String> expectedOptions) {
        List<String> actualOptions = controller().getAllListOptions(locator);
        try {
            Assert.assertEqualsNoOrder(actualOptions.toArray(new String[actualOptions.size()]), expectedOptions.toArray(new String[expectedOptions.size()]));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_OPTIONS + expectedOptions + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_OPTIONS + expectedOptions + "'!");
            throw e;
        }
    }


    /**
     * List options. Checks if some options are  selected in a list
     *
     * @param locator         the locator of the list
     * @param expectedOptions the expected options in the specified list
     */
    public void selectedListOptions(String locator, List<String> expectedOptions) {
        try {
            Assert.assertEquals(controller().getSelectedOptions(locator), expectedOptions);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_SELECTED_OPTIONS + expectedOptions + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_SELECTED_OPTIONS + expectedOptions + "'!");
            throw e;
        }
    }

    /**
     * Selected list option. Checks if an option is selected in a list
     *
     * @param locator        the locator of the list
     * @param expectedOption the expected option selected in the list
     */
    public void selectedListOption(String locator, String expectedOption) {
        try {
            Assert.assertEquals(controller().getSelectedOption(locator), expectedOption);
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + FOUND_WITH_SELECTED_OPTION + expectedOption + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + NOT_FOUND_WITH_SELECTED_OPTION + expectedOption + "'!");
            throw e;
        }
    }

    /**
     * Equal. Check two objects for equality
     *
     * @param obj1 the obj1 you want to check
     * @param obj2 the obj2 you want to check
     */
    public void equal(Object obj1, Object obj2) {
        try {
            Assert.assertEquals(obj1, obj2);
            info("The '" + obj1 + IS_EQUAL + obj2 + "'!");
        } catch (AssertionError e) {
            error("The '" + obj1 + IS_NOT_EQUAL + obj2 + "'!");
            throw e;
        }
    }

    /**
     * Non Equality . Check two objects for non equality
     *
     * @param obj1 the obj1 you want to check
     * @param obj2 the obj2 you want to check
     */
    public void notEqual(Object obj1, Object obj2) {
        try {
            Assert.assertNotEquals(obj1, obj2);
            info("The '" + obj1 + IS_NOT_EQUAL + obj2 + "'!");
        } catch (AssertionError e) {
            error("The '" + obj1 + IS_EQUAL + obj2 + "'!");
            throw e;
        }
    }

    /**
     * Verify that the element has the given attribute
     *
     * @param locator
     * @param attribute
     */
    public void hasAttribute(String locator, String attribute) {
        try {
            Assert.assertNotNull(controller().getAttributeValue(locator, attribute));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + HAS_ATTRIBUTE + attribute);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + HAS_NOT_ATTRIBUTE + attribute);
            throw e;
        }
    }

    /**
     * Verify that the element has the given attribute
     *
     * @param locator
     * @param attribute
     */
    public void hasNotAttribute(String locator, String attribute) {
        try {
            Assert.assertNull(controller().getAttributeValue(locator, attribute));
            highlightPass(locator);
            info(ELEMENT_LOCATOR + locator + HAS_NOT_ATTRIBUTE + attribute);
        } catch (AssertionError e) {
            highlightFail(locator);
            error(ELEMENT_LOCATOR + locator + HAS_ATTRIBUTE + attribute);
            throw e;
        }
    }


    /**
     * Attribute value.
     *
     * @param locator      the locator of the element
     * @param attribute    the attribute of the element under examination
     * @param desiredValue the desired value that the attribute must have
     */
    public void attributeValue(String locator, String attribute, String desiredValue) {
        try {
            Assert.assertEquals(controller().getAttributeValue(locator, attribute), desiredValue);
            highlightPass(locator);
            info("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + FOUND_WITH_VALUE + desiredValue + "'!");
        } catch (AssertionError e) {
            highlightFail(locator);
            error("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + NOT_FOUND_WITH_VALUE + desiredValue + "'!");
            throw e;
        }
    }


    /**
     * Attribute value.
     *
     * @param element      the desired element
     * @param attribute    the attribute of the element under examination
     * @param desiredValue the desired value that the attribute must have
     */
    public void attributeValue(WebElement element, String attribute, String desiredValue) {
        try {
            Assert.assertEquals(element.getAttribute(attribute), desiredValue);
            info(DESIRED_ELEMENT + attribute + FOUND_WITH_VALUE + desiredValue + "'!");
        } catch (AssertionError e) {
            info(DESIRED_ELEMENT + attribute + NOT_FOUND_WITH_VALUE + desiredValue + "'!");
            throw e;
        }
    }


    /**
     * Attribute contains value.
     *
     * @param locator      the locator of the element
     * @param attribute    the attribute of the element under examination
     * @param desiredValue the desired value to be contained in attribute
     */
    public void attributeContainsValue(String locator, String attribute, String desiredValue) {
        if (controller().getAttributeValue(locator, attribute).contains(desiredValue)) {
            highlightPass(locator);
            info("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + FOUND_CONTAINING_VALUE + desiredValue + "'!");
        } else {
            highlightFail(locator);
            error("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + NOT_FOUND_CONTAINING_VALUE + desiredValue + "'!");
            throw new AssertionError("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + NOT_FOUND_CONTAINING_VALUE + desiredValue + "'!");
        }
    }

    /**
     * Attribute does not contain value.
     *
     * @param locator      the locator of the element
     * @param attribute    the attribute of the element under examination
     * @param desiredValue the desired value not to be contained in attribute
     */
    public void attributeDoesNotContainValue(String locator, String attribute, String desiredValue) {
        if (!controller().getAttributeValue(locator, attribute).contains(desiredValue)) {
            highlightPass(locator);
            info("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + NOT_FOUND_CONTAINING_VALUE + desiredValue + "'!");
        } else {
            highlightFail(locator);
            error("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + FOUND_CONTAINING_VALUE + desiredValue + "'!");
            throw new AssertionError("The '" + attribute + ELEMENT_LOCATOR_ATTRIBUTE + locator + FOUND_CONTAINING_VALUE + desiredValue + "'!");
        }
    }


    /**
     * Element contained in list.
     *
     * @param list    the list you want to search into
     * @param element the element you want to be present in the list
     */
    public void elementContainedInList(List<?> list, Object element) {
        if (list.contains(element)) {
            info("The '" + element + CONTAINED_IN_LIST);
        } else {
            error("The '" + element + NOT_CONTAINED_IN_LIST);
            throw new AssertionError("The '" + element + NOT_CONTAINED_IN_LIST);
        }
    }

    /**
     * Element is not contained in a list.
     *
     * @param list    the list you want to search into
     * @param element the element you want to be not present in the list
     */
    public void elementNotContainedInList(List<?> list, Object element) {
        if (!list.contains(element)) {
            info("The '" + element + NOT_CONTAINED_IN_LIST);
        } else {
            error("The '" + element + CONTAINED_IN_LIST);
            throw new AssertionError("The '" + element + CONTAINED_IN_LIST);
        }
    }

    /**
     * Alert text.
     *
     * @param alertText the alert text
     */
    public void alertText(String alertText) {
        try {
            Assert.assertEquals(controller().getAlertText(), alertText);
            info(FOUND_ALERT_WITH_MESSAGE + alertText + "'!");
        } catch (AssertionError e) {
            error(NOT_FOUND_ALERT_WITH_MESSAGE + alertText + "'!");
            throw e;
        }
    }


    private void highlightPass(String locator) {
        if (SteviaContext.getParam("highlight").equals("true")) {
            controller().highlight(locator, PASS_COLOR);
        }
    }


    private void highlightFail(String locator) {
        if (SteviaContext.getParam("highlight").equals("true")) {
            controller().highlight(locator, FAIL_COLOR);
        }
    }
}
