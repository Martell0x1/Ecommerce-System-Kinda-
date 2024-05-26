public class BookProduct extends Product{
    private String Author;
    private String Publisher;


    public BookProduct(int ProductId, String Name, float Price, String author, String publisher) {
        super(ProductId, Name, Price);
        Author = author;
        Publisher = publisher;
    }
    public BookProduct(){}

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public BookProduct(String author, String publisher) {
        Author = author;
        Publisher = publisher;
    }

    @Override
    protected void PrintAdditionalInfo(){
        System.out.println("Author : "+this.getAuthor());
        System.out.println("Publisher: "+this.getPublisher());
    }


}
