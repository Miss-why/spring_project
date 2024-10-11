package shopping_admin;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class homepage_dao {
	private int homekey, signpoint, signlevel, min_pay_points, max_pay_points, delivery_fee_price;
	private String hometitle, adminemail, usepointck, compamy_name, business_regist_number, president_name, president_phone,
			mailorder_report_number, telecom_busi_number, business_zipcode, business_address, infomanager_name,
			infomanager_email, non_account_bank, bank_account_number, ckuse_credit_card, ckuse_mobile_phone, ckuse_book_gift_pay,
			ckuse_cash_receipt, delivery_compamy_name, ckuse_desired_deldate;
	
	public ArrayList<Object> toList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getHomekey());
		al.add(this.getHometitle());
		al.add(this.getAdminemail());
		al.add(this.getUsepointck());
		al.add((this.getSignpoint() != 0) ? this.getSignpoint() : null);
		al.add((this.getSignlevel() != 0) ? this.getSignlevel() : null);
		al.add(this.getCompamy_name());
		al.add(this.getBusiness_regist_number());
		al.add(this.getPresident_name());
		al.add(this.getPresident_phone());
		al.add(this.getMailorder_report_number());
		al.add(this.getTelecom_busi_number());
		al.add(this.getBusiness_zipcode());
		al.add(this.getBusiness_address());
		al.add(this.getInfomanager_name());
		al.add(this.getInfomanager_email());
		al.add(this.getNon_account_bank());
		al.add(this.getBank_account_number());
		al.add(this.getCkuse_credit_card());
		al.add(this.getCkuse_mobile_phone());
		al.add(this.getCkuse_book_gift_pay());
		al.add((this.getMin_pay_points() != 0) ? this.getMin_pay_points() : 1000);
		al.add((this.getMax_pay_points() != 0) ? this.getMax_pay_points() : null);
		al.add(this.getCkuse_cash_receipt());
		al.add(this.getDelivery_compamy_name());
		al.add((this.getDelivery_fee_price() != 0) ? this.getDelivery_fee_price() : null);
		al.add(this.getCkuse_desired_deldate());
		return al;
	}
}
