package com.qa.opencart.tests;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority =1)
    public void accPageTitleTest() {
        String title = accountsPage.getaccountPageTitle();
    }

    @Test(priority =2)
    public void accPageHeaderTest() {
        Assert.assertTrue(accountsPage.getAccountsPageHeader());
    }

    @Test(priority =3)
    public void isLogoutLinkExist() {
        Assert.assertTrue(accountsPage.isLogoutLinkExist());
        System.out.println("Logout link exists");
    }

    @Test(priority =4)
    public void accountPageSectionsTest() {
        List<String> actAccountSecList = accountsPage.getAccountSecList();
        Assert.assertEquals(actAccountSecList, Constants.getExpectedAccSecList());
    }

    @DataProvider
    public Object[][] productData() {
        return new Object[][]{
                {"MacBook"},
                {"Apple"},
                {"SamSung"},
        };
    }
    
    @DataProvider
    public Object[][] getProductData()
    {
    	return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
    }
	 

    @Test(priority =5,dataProvider = "getProductData")
    public void searchTest(String productName) {
        searchResultsPage = accountsPage.doSearch(productName);
     Assert.assertTrue(searchResultsPage.getProductsListCount()>0);
    }

//    @DataProvider
//    public Object[][] productSelectData() { 
//
//    	return new Object[][]{ 
//
//    		{"MacBook","MacBook Pro"},
//    		{"Apple","Apple Cinema 30\""},
//    		{"SamSung","Samsung SyncMaster 941BW"}, 
//
//    	}; 
//    }
    
  
    @DataProvider
    public Object[][] getMainProductData()
    {
    	return ExcelUtil.getTestData(Constants.MAIN_PRODUCT_SHEET_Name);
    }
    
    
    @Test(priority = 6,dataProvider = "getMainProductData")
    public void selectProductTest(String productName, String mainProductName)
    {
        searchResultsPage =accountsPage.doSearch(productName);
        productInfoPage = searchResultsPage.selectProduct(mainProductName);
        Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
    }


}
