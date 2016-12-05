package uiController;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import UserView.CommentView;
import VO.HotelVO;
import VO.OrderVO;
import hotelBLService.HotelBLService;
import hotelBLService.HotelBLServiceController;
import orderBLService.OrderBLService;
import orderBLService.OrderBLServiceController;
import uiService.CommentViewService;

public class CommentViewControllerServiceImpl implements CommentViewService{
    private CommentView view;
    private String UserID;
    private String OrderID;
    private OrderBLService order;
    private HotelBLService hotel;
    public CommentViewControllerServiceImpl(String UserID,String OrderID) throws RemoteException{
    	this.UserID=UserID;
    	this.OrderID=OrderID;
    	order=new OrderBLServiceController();
    	hotel=new HotelBLServiceController();
    }
	@Override
	public void setView(CommentView view) {
		this.view=view;
	}

	@Override
	public void exit() {
		try {
			view.exit();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getUserID() {
		return UserID;
	}
	@Override
	public boolean judgeScore(String input) {
		if(input.equals("")){
			return false;
		}
		char[] digit=input.toCharArray();
		boolean flag=true;
		for(char a:digit){
			if(!Character.isDigit(a)){
				flag=false;
				break;
			}
		}
		int temp;
		if(flag){
			temp=Integer.parseInt(input);
			if(temp>=1&&temp<=5){
				flag=true;
			}
			else{
				flag=false;
			}
		}
		return flag;
	}
	@Override
	public boolean judgeComment(String comment) {
		int cout=0;
		for(char a:comment.toCharArray()){
			if(a!=' '){
				cout++;
			}
		}
		if(cout>=5){
			return true;
		}
		else{
			return false;	
		}
	}
	@Override
	public void comment(String score, String comment) {
		order.updatecomment(comment, Integer.parseInt(score), OrderID);
		try {
			view.exit();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  List<String> getDetail(){
		OrderVO vo=order.showDetail(UserID,OrderID);
		//HotelVO vo1=hotel.findByHotelID(vo.hotelID);
		List<String> list=new ArrayList<String>();
		//list.add(vo1.hotelName);
		list.add("lalala");
		list.add(vo.roomType);
		list.add(vo.orderNumber);
		list.add(""+vo.orderValue);
		list.add(""+vo.numOfPerson);
		list.add(""+vo.roomNumber);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		list.add(sdf.format(vo.expectedCheckIn.getTime()));
		list.add(sdf.format(vo.expectedCheckOut.getTime()));
		return list;
	}
}
