package com.repos;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.models.Transfer;

public interface TransferDAO {
	ArrayList<Transfer> selectAllTransfers(int receiver);
	ArrayList<Transfer> selectTransfer(int sender, int receiver);
	boolean insertTransfer(int sender, int receiver, BigDecimal amount);
	boolean deleteAllTransfers(int receiver);
	boolean deleteTransfer(int sender, int receiver);
}
