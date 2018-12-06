package ca.ulaval.glo4002.accountbilling;

import org.junit.Test;

public class AccountBillingServiceTest {

	
  @Test
  public void asd_thenBillIsCancel(){
    TestableAccountBillingService service = new TestableAccountBillingService();

    //assert();
  }
	
	
  class TestableAccountBillingService extends  AccountBillingService {
    

    
    protected void persistBill(Bill bill) {
    	
    }


  }
}
