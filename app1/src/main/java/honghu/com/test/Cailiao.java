package honghu.com.test;

/**
 * Created by Administrator on 2017/4/26.
 */

public class Cailiao {
    String ItemNo;
    String ItemName;
    String ItemModel;
    String Unit;
    int Price;
    String Remark;

    public Cailiao(String itemNo, String itemName, String itemModel, String unit, int price, String remark) {
        ItemNo = itemNo;
        ItemName = itemName;
        ItemModel = itemModel;
        Unit = unit;
        Price = price;
        Remark = remark;
    }

    @Override
    public String toString() {
        return "Cailiao{" +
                "ItemNo='" + ItemNo + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemModel='" + ItemModel + '\'' +
                ", Unit='" + Unit + '\'' +
                ", Price=" + Price +
                ", Remark='" + Remark + '\'' +
                '}';
    }

    public String getItemNo() {
        return ItemNo;
    }

    public void setItemNo(String itemNo) {
        ItemNo = itemNo;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemModel() {
        return ItemModel;
    }

    public void setItemModel(String itemModel) {
        ItemModel = itemModel;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
