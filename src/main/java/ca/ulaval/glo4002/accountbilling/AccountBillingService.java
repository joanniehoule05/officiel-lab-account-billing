package ca.ulaval.glo4002.accountbilling;

import java.util.List;

public class AccountBillingService {

	public void cancelInvoiceAndRedistributeFunds(BillId id) {
		Bill billToCancel = BillDAO.getInstance().findBill(id);
		if (billToCancel == null) {
			throw new BillNotFoundException();
		}else {		

           ClientId cid = billToCancel.getClientId();
        
		    if (!billToCancel.isCancelled()) {
		    	billToCancel.cancel();
		    }
		    BillDAO.getInstance().persist(billToCancel);
	
			List<Allocation> allocations = billToCancel.getAllocations();

			for (Allocation al : allocations) {
			  List<Bill> bills = BillDAO.getInstance().findAllByClient(cid); 
			  int amountToDedistribute = al.getAmount();
	
			  for (Bill currentBill : bills) {
			    if (billToCancel != currentBill) {
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
}