package org.globalsqa.tests;

import io.qameta.allure.*;
import org.globalsqa.helpers.LocalStorageHelper;
import org.globalsqa.models.CustomerModel;
import org.globalsqa.pages.CustomersListPage;
import org.globalsqa.pages.ManagerPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebElement;
@Epic("Customer Management")
@Feature("Search")
@DisplayName("Create customer searching tests")
@Execution(ExecutionMode.CONCURRENT)
public class CustomerSearchTest extends BaseTests {

    @Test
    @Epic("Customer Management")
    @Feature("Search")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Search customer: displayed at customers list")
    @Description("Searching customer exist ")
    @Execution(ExecutionMode.CONCURRENT)
    public void testCustomerSearchFullNameMatchCustomerExist() {
        //Arrange

        String expectedCustomerName = "Hermoine";
        ManagerPage managerPage = new ManagerPage(driver).navigate();
        CustomersListPage customerListPage = managerPage.clickCustomers();
        LocalStorageHelper localStorageHelper = new LocalStorageHelper(driver);
        int currentMaxId = localStorageHelper.getMaxUserId();
        CustomerModel expectedCustomer = new CustomerModel("Daniel","Yurtaev","444431",currentMaxId+1);

        // Act
        WebElement customerRow = customerListPage
                .searchCustomer(expectedCustomerName)
                .customersTableComponent
                .findCustomerByName(expectedCustomerName);

        //Assert
        Assertions.assertNotNull(customerRow, "Customer with name: " + expectedCustomerName + " not found"); //is Customer with name exist in customers list
    }
}
