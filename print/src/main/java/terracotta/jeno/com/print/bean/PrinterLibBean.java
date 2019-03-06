package terracotta.jeno.com.print.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/02/28
 * desc   : 打印内容封装的实体
 * version: 1.0.0
 */

public class PrinterLibBean implements Serializable {


    private boolean isTwoColumn = false;
    private boolean isFourColumn = false;
    private String title;
    private String contentTop;
    private String contentBottom;
    private String contentMiddle;
    private String twoCode;
    private List<PrientList> prientLists;


    public boolean isFourColumn() {
        return isFourColumn;
    }

    public void setFourColumn(boolean fourColumn) {
        isFourColumn = fourColumn;
    }

    public boolean isTwoColumn() {
        return isTwoColumn;
    }

    public void setTwoColumn(boolean twoColumn) {
        isTwoColumn = twoColumn;
    }

    public String getContentMiddle() {
        return contentMiddle;
    }

    public void setContentMiddle(String contentMiddle) {
        this.contentMiddle = contentMiddle;
    }

    public List<PrientList> getPrientLists() {
        return prientLists;
    }

    public void setPrientLists(List<PrientList> prientLists) {
        this.prientLists = prientLists;
    }

    public String getContentTop() {
        return contentTop;
    }

    public void setContentTop(String contentTop) {
        this.contentTop = contentTop;
    }

    public String getContentBottom() {
        return contentBottom;
    }

    public void setContentBottom(String contentBottom) {
        this.contentBottom = contentBottom;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getTwoCode() {
        return twoCode;
    }

    public void setTwoCode(String twoCode) {
        this.twoCode = twoCode;
    }


    public static class PrientList implements Serializable {
        private String listName;//列表的名字
        private List<String> listCode;
        private String name;
        private String left;
        private String subLeft;
        private String middle;
        private String right;


        public String getSubLeft() {
            return subLeft;
        }

        public void setSubLeft(String subLeft) {
            this.subLeft = subLeft;
        }

        private String leftColumnName;
        private String secondColumName;
        private String thirdColumName;
        private String rightColumnName;


        public String getRightColumnName() {
            return rightColumnName;
        }

        public void setRightColumnName(String rightColumnName) {
            this.rightColumnName = rightColumnName;
        }

        public String getLeftColumnName() {
            return leftColumnName;
        }

        public void setLeftColumnName(String leftColumnName) {
            this.leftColumnName = leftColumnName;
        }

        public String getSecondColumName() {
            return secondColumName;
        }

        public void setSecondColumName(String secondColumName) {
            this.secondColumName = secondColumName;
        }

        public String getThirdColumName() {
            return thirdColumName;
        }

        public void setThirdColumName(String thirdColumName) {
            this.thirdColumName = thirdColumName;
        }

        public List<String> getListCode() {
            return listCode;
        }

        public void setListCode(List<String> listCode) {
            this.listCode = listCode;
        }

        public String getListName() {
            return listName;
        }

        public void setListName(String listName) {
            this.listName = listName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getMiddle() {
            return middle;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }
    }


}
