package com.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.entity.Passager;

public class PassagerDAO {

	public Boolean loginConfirm(String name, String password, List<Passager> passagerList) {
		for(Passager passager : passagerList) {
        	if(passager.getPassagerID().equals(name)&&passager.getPassword().equals(password)) {
        		return true;
        	}
        }
		return false;
	}

	public boolean register(String loginUserName, String loginPassword, String identityId, String realName, List<Passager> passagerList) {
		boolean exit = false;
		for(Passager passager : passagerList) {
        	if(passager.getPassagerID().equals(loginUserName)&&passager.getPassword().equals(loginPassword)) {
        		exit = true;
        	}
        }
		if(exit) {
			return false;
		} else {
			Passager passager = new Passager();
			passager.setPassagerID(loginUserName);
			passager.setPassword(loginPassword);
			passager.setIdentityID(identityId);
			passager.setRealName(realName);
			passager.setOrderID("");
			insertToFile(passager);
			return true;
		}
		
	}

	private void insertToFile(Passager passager) {
		try {
			File fileTxt = new File("passager.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileTxt,true));
			String passagerStr = "\n"+passager.toString();
			bw.write(passagerStr);
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}