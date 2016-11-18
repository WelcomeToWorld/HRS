package uiService;

import WebPromotionView.CreditManagementView;

public interface CreditManagementUiService {

	public void setView(CreditManagementView view);

	public void toWebPromotionUserView();

	public void updateCredit(String acc, int value);

}