package PromotionBLServiceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import PO.PromotionPO;
import VO.MemberLevelSystemVO;
import VO.WebPromotionVO;
import dataService.DataFactoryService;
import dataService.PromotionDataService;
import promotionMemberGrade.PromotionMemberGradeController;
import rmi.RemoteHelper;
import userBLServiceImpl.Credit;

public class SearchWebPromotion {
	private DataFactoryService df;
	private PromotionDataService pds;
	private List<WebPromotionVO> voList;
	private static SearchWebPromotion searchWebPromotion;
	
	private SearchWebPromotion() {
		voList=new ArrayList<WebPromotionVO>();
		df=RemoteHelper.getInstance().getDataFactoryService();
		try {
			pds = (PromotionDataService)df.getDataService("Promotion");
			List<PromotionPO> poList = pds.getAllWebPromotion();
			for(PromotionPO po : poList) {
				WebPromotionVO tempvo = new WebPromotionVO(po);
				voList.add(tempvo);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public static SearchWebPromotion getSearchWebPromotionInstance() {
		if(searchWebPromotion== null) {
			searchWebPromotion = new SearchWebPromotion();
		}
		return searchWebPromotion;
	}

	public WebPromotionVO getWebPromotionByPromotionNumber(String promotionnumber) {
		WebPromotionVO  wpvo=new WebPromotionVO();
		for(WebPromotionVO vo : voList) {
		if(searchWebPromotion.judgePromotionNumber(vo,promotionnumber)) {				
			    wpvo=vo;
				break;
			}
		}
		return wpvo;
	}
	private boolean judgePromotionNumber(WebPromotionVO vo,String promotionnumber) {
		boolean outcome = false;
		if(vo.promotionNumber==promotionnumber) {
			outcome = true;
		}
		return outcome;
	}
	public Vector<Vector<String>> getAllWebPromotion(){
		Vector< Vector<String>> list=new Vector<Vector<String>>();
		for(int i=0;i<voList.size();i++){
			Vector<String> v=voList.get(i).getVector();
			list.add(v);
		}
		return list;
	}
}
