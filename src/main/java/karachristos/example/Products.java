package karachristos.example;

public class Products {
    public Products( String codeOfProduct, String titleOfProduct, String timestamp, String price, String description, String category, String previousRecord) {
        this.codeOfProduct = codeOfProduct;
        this.titleOfProduct = titleOfProduct;
        this.timestamp = timestamp;
        this.price = price;
        this.description = description;
        this.category = category;
        this.previousRecord = previousRecord;
    }

    public String getCodeOfProduct() {
        return codeOfProduct;
    }

    public void setCodeOfProduct(String codeOfProduct) {
        this.codeOfProduct = codeOfProduct;
    }

    public String getTitleOfProduct() {
        return titleOfProduct;
    }

    public void setTitleOfProduct(String titleOfProduct) {
        this.titleOfProduct = titleOfProduct;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPreviousRecord() {
        return previousRecord;
    }

    public void setPreviousRecord(String previousRecord) {
        this.previousRecord = previousRecord;
    }

    private String id;
    private String codeOfProduct;
    private String titleOfProduct;
    private String timestamp;
    private String price;
    private String description;
    private String category;
    private String previousRecord;

    public String[] toArray()
    {
        return new String[]{this.codeOfProduct,this.titleOfProduct,this.timestamp,this.price,this.description,this.category,this.previousRecord};
    }

}
