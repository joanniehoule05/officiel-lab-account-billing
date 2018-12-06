package ca.ulaval.glo4002.accountbilling;

import java.util.List;

public class AccountBillingService {

	public void cancelInvoiceAndRedistributeFunds(BillId id) {
		Bill bill = BillDAO.getInstance().findBill(id);
		if (bill == null) {
			throw new BillNotFoundException();
		}

        ClientId cid = bill.getClientId();
        
	    if (bill.isCancelled() != true) { bill.cancel();
	    BillDAO.getInstance().persist(bill);

		List<Allocation> allocations = bill.getAllocations();

		for (Allocation al : allocations) {
		  List<Bill> bills = BillDAO.getInstance().findAllByClient(cid); 
		  int amountToDedistribute = al.getAmount();

		  for (Bill currentBill : bills) {
		    if (bill != currentBill) {
			  int remainingAmount = currentBill.getRemainingAmount();						
			  Allocation newAllocation;
			  if (remainingAmount <= amountToDedistribute) { 
			    newAllocation = new Allocation(remainingAmount);
				amountToDedistribute -= remainingAmount;
			   } else {
			    newAllocation = new Allocation(amountToDedistribute);
				amountToDedistribute = 0;
			   }
	
			    currentBill.addAllocation(newAllocation);					
				BillDAO.getInstance().persist(currentBill);
			  }
	
			  if (amountToDedistribute == 0) {
			    break;
			  }
			}
		}
	}
}