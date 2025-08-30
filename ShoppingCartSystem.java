import java.util.*;

class Product {
    String id, name, category;
    double price;
    int stock;

    static int totalProducts = 0;
    static String[] categories = {"Electronics","Clothing","Books","Grocery"};

    Product(String id, String name, double price, String category, int stock) {
        this.id=id; this.name=name; this.price=price; this.category=category; this.stock=stock;
        totalProducts++;
    }

    static Product findById(Product[] products, String id) {
        for (Product p:products) if(p!=null && p.id.equals(id)) return p;
        return null;
    }
    static List<Product> getByCategory(Product[] products, String cat) {
        List<Product> list=new ArrayList<>();
        for(Product p:products) if(p!=null && p.category.equalsIgnoreCase(cat)) list.add(p);
        return list;
    }
}

class ShoppingCart {
    String customer;
    List<Product> items=new ArrayList<>();
    List<Integer> qtys=new ArrayList<>();

    ShoppingCart(String customer){ this.customer=customer; }

    void add(Product p,int q){
        if(p==null||p.stock<q){System.out.println(" Not available");return;}
        items.add(p); qtys.add(q); p.stock-=q;
        System.out.println("Added "+q+" x "+p.name);
    }
    void remove(String id){
        for(int i=0;i<items.size();i++)
            if(items.get(i).id.equals(id)){items.get(i).stock+=qtys.get(i);items.remove(i);qtys.remove(i);System.out.println("Removed");return;}
        System.out.println(" Not in cart");
    }
    double total(){ double sum=0; for(int i=0;i<items.size();i++) sum+=items.get(i).price*qtys.get(i); return sum; }
    void show(){ System.out.println("\n---Cart---"); for(int i=0;i<items.size();i++) System.out.println(items.get(i).name+" x"+qtys.get(i)); System.out.println("Total: ₹"+total()); }
    void checkout(){ if(items.isEmpty())System.out.println(" Empty cart"); else {show();System.out.println("Checkout done");items.clear();qtys.clear();}}
}

public class ShoppingCartSystem {
    public static void main(String[] a){
        Scanner sc=new Scanner(System.in);
        Product[] products={
            new Product("P01","Phone",20000,"Electronics",5),
            new Product("P02","Laptop",50000,"Electronics",3),
            new Product("P03","Shirt",800,"Clothing",10),
            new Product("P04","Book",400,"Books",15),
            new Product("P05","Rice",600,"Grocery",20)
        };

        System.out.print("Enter your name: ");
        ShoppingCart cart=new ShoppingCart(sc.nextLine());

        int ch;
        do{
            System.out.println("\n1.View Products 2.Add 3.Remove 4.Show Cart 5.Checkout 6.Exit");
            ch=sc.nextInt();sc.nextLine();
            switch(ch){
                case 1: for(Product p:products) System.out.println(p.id+" | "+p.name+" | ₹"+p.price+" | Stock:"+p.stock); break;
                case 2: System.out.print("Enter ID: "); String id=sc.nextLine(); Product p=Product.findById(products,id);
                        if(p!=null){System.out.print("Qty: "); int q=sc.nextInt();sc.nextLine(); cart.add(p,q);} else System.out.println(" Not found"); break;
                case 3: System.out.print("Enter ID: "); cart.remove(sc.nextLine()); break;
                case 4: cart.show(); break;
                case 5: cart.checkout(); break;
            }
        }while(ch!=6);
        sc.close();
    }
}