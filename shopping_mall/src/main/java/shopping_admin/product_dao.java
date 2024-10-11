package shopping_admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class product_dao {
	private int pidx, pcateidx, original_price, discount_rate, discount_price, product_stock, pamidx;
	private String pcode, pname, add_pexplain, sell_ck, early_sold_ck, pimages, detail_pexplain, pindate;
	private String clgmenu_name;
}
