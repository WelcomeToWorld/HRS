package uiController;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import UserView.CreditView;
import VO.CreditRecordVO;
import common.Operate;
import uiService.CreditViewControllerService;
import userBLService.UserBLService;
import userBLService.UserBLServiceController;
import userBLServiceImpl.DES;
import userBLServiceImpl.Log;

public class CreditViewControllerImpl implements CreditViewControllerService {
	private CreditView view;
	private String id;
	private UserBLService user;
	String key;
	public CreditViewControllerImpl(String id){
		
		try {
			user=new UserBLServiceController();
			key=Log.getLogInstance().getKey(id);
			id=DES.encryptDES(id, key);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.id=id;
	}
	public void setView(CreditView view) {
        this.view=view;
	}
	@Override
	public long getcredit(String id) {
		return user.showCredit(id);
	}
	public void exit() {
		view.exit();		
	}
	@Override
	public String getUserID() {
		return id;
	}
	@Override
	public Vector<Vector<String>> getCreditRecord(String id) {
		Vector<Vector<String>> volist=new Vector<Vector<String>>();
		Iterator<Map.Entry<String, CreditRecordVO>> it=user.showCreditRecord(id).entrySet().iterator();
		while(it.hasNext()){
			CreditRecordVO vo=it.next().getValue();
			volist.add(vo.getVector());
		}
		return volist;
	}
}
