package ca.ulaval.glo4002.accountbilling;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AccountBillingServiceTest {
	
  private static final BillId BILL_ID = new BillId(12L);
  private static final ClientId CLIENT_ID = new ClientId(18L);
  private Bill bill;
  
  TestableAccountBillingService service;
  List<Bill> persistedBills;

  public void setUp() {
    service = new TestableAccountBillingService();	 
    persistedBills = new ArrayList<>();   
  }
	
  @Test
  public void givenBillCancelled_thenBillShouldNotBeCancelled(){
    bill = new Bill(CLIENT_ID, 10);
    
    bill.cancel();
    
    //service.cancelInvoiceAndRedistributeFunds(BILL_ID);
  }
  
  //@Test
	
	
  class TestableAccountBillingService extends  AccountBillingService {
   
	//@Override
    protected Bill getBillById (BillId billId){
      return bill;
    }
    
    //@Override
    protected void persistBill(Bill bill) {
      persistedBills.add(bill);
    }
  }
}
