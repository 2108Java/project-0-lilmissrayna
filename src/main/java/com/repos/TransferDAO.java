package com.repos;

import com.models.Transfer;

public interface TransferDAO {
	Transfer selectTransfer(Transfer transfer);
	boolean updateTranfer(Transfer transfer);
	boolean insertTransfer(Transfer transfer);
	boolean deleteTransfer(int id);
}
