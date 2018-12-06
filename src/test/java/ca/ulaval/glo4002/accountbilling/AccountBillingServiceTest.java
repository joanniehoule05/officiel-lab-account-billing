package ca.ulaval.glo4002.accountbilling;

import org.junit.Test;

public class AccountBillingServiceTest {

	
  @Test
  public void asd(){
    TestableAccountBillingService service = new TestableAccountBillingService();
	  
  }
	
	
  class TestableAccountBillingService extends BillDAO{
	  
    public TestableAccountBillingService() {
		
	}
  }
}
