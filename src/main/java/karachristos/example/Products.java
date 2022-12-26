package karachristos.example;

public class Products {
    String codeOfProduct;
    String titleOfProduct;
    String timestamp;
    String price;
    String description;
    String category;
    String previousRecord;

    public Products(String codeOfProduct, String titleOfProduct, String timestamp, String price, String description, String category, String previousRecord) {
        this.codeOfProduct = codeOfProduct;
        this.titleOfProduct = titleOfProduct;
        this.timestamp = timestamp;
        this.price = price;
        this.description = description;
        this.category = category;
        this.previousRecord = previousRecord;
    }

    public String[] toArray()
    {
        return new String[]{this.codeOfProduct,this.titleOfProduct,this.timestamp,this.price,this.description,this.category,this.previousRecord};
    }

}
